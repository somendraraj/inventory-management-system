package com.cleartrip.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import com.cleartrip.entity.Items;

public interface GenericRepo<T extends Items> {

	T findOne(long id);

	T findByName(String name);

	List<T> findAll(Pageable pageable);

	void save(T entity);

	void batchSave(Collection<T> entities);

	void batchDelete();

	void update(T entity);

	void delete(T entity);

	void deleteById(long entityId);
	
	List<T> getAll();
}
