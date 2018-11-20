package com.cleartrip.repository.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import com.cleartrip.entity.Items;
import com.cleartrip.exception.ApiException;
import com.cleartrip.repository.GenericRepo;

@SuppressWarnings("unchecked")
@Transactional
public abstract class GenericRepoImpl<T extends Items> implements GenericRepo<T>  {
	
	private static final Logger log = LoggerFactory.getLogger(GenericRepoImpl.class);
	
	private static final int batchsize = 100;
	
	protected Class<T> clazz;

	@PersistenceContext
	protected EntityManager em;

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	@Override
	public T findOne(long id) {
		T result = null;
		try {
			result = em.find(clazz, id);
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument for findOne by id"+id+" Msg :"+ e);
			
		}
		return result;
	}

	
	public T findByName(String name) {
		T result = null;
		try {
			result = (T) em.createNamedQuery(clazz.getSimpleName() + ".getByName").setParameter("name", name).getSingleResult();
		} catch (Exception ae) {
			
			log.error("No entity was found for  " + clazz.getSimpleName() + " with name : " + name, ae);
		}
	
		return result;

	}

	public List<T> findAll(Pageable pageable) {

		return em.createQuery("from " + clazz.getName()).setFirstResult((int)pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();
	}

	public void save(T entity) {
		if (entity == null)
			return;
		em.persist(entity);
	}

	public void batchSave(Collection<T> entities) {
		log.info("Bulk saving for " + clazz.getSimpleName());

		int i = 1;
		for (T entity : entities) {
			if (entity == null)
				continue;
			if (entity.getId() == 0)
				save(entity);
			else
				update(entity);

			if (i % batchsize == 0) {
				em.flush();
				em.clear();
			}
			i++;
		}
		em.flush();
		em.clear();
		log.info("Batch save finished " + clazz.getSimpleName());
	}

	public void batchDelete() {
		// Delete table in bulk
	}

	public void update(T entity) {
		if (entity == null)
			return;
	
		try {
			em.merge(entity);
		} catch (IllegalArgumentException ex) {
			log.error("updating of entity :" + clazz.getSimpleName() + " failed " + ex);
		}

	}

	public void delete(T entity) {
		if (entity == null)
			return;
		try {
			em.remove(entity);
		} catch (IllegalArgumentException e) {
			log.error("enitity was not deleted " + e);
		}

	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		if (entity != null)
			delete(entity);
	}
	
	public List<T> getAll() {
		return em.createQuery("from " + clazz.getName()).getResultList();
	}
}
