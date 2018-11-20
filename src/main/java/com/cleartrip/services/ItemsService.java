package com.cleartrip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.cleartrip.entity.Items;
import com.cleartrip.exception.ApiException;
import com.cleartrip.repository.GenericRepo;

@Service
public class ItemsService {

	@Autowired
	GenericRepo<Items> genericRepo;
	

	public List<Items> findAllItems(Pageable pageable) {

		return genericRepo.findAll(pageable);
	}

	public Items findItemById(Long id) throws ApiException {
		//we can implement cache and get the data much faster. sudo code
		
		//if(id.existsincache()){
			//return data;
		//}
		
		Items e = genericRepo.findOne(id);
		if (e == null)
			throw new ApiException("Item not found ", "Item was not found with id " + id);
		return e;
	}

	
	public Long saveItemToDB(Items i) {
		genericRepo.save(i);
		return genericRepo.findByName(i.getName()).getId();

	}
		
	/**
	 * checkout Items
	 * 
	 * @param e
	 * @return
	 */
	
	public Items checkoutItems(Items i, int count) {
		Items p = genericRepo.findByName(i.getName());
		int stock = p.getStock();
		if(stock>=count){
			p.setCost(stock-count);
		}
		
		//to avoid multiple threading updates same resourses
		synchronized(this){
			genericRepo.update(i);
		}
		
		return genericRepo.findByName(i.getName());

	}
	
	
	/**
	 * update by id and items
	 * @param id
	 * @param e
	 * @throws ApiException
	 */
	public void updateItems(Long id, Items e) throws ApiException {
		Items entry = genericRepo.findOne(id);
		if (entry == null)
			throw new ApiException("Items not found ", "Item was not found with id " + id);

		//copy and update // to avoid multiple threads can't access same resources
		synchronized(this){
			genericRepo.update(entry);
		}
	}

	
	/**
	 * delete by id
	 * @param id
	 * @throws ApiException
	 */
	public void deleteItemById(Long id) throws ApiException {

		Items i = genericRepo.findOne(id);

		if (i == null)
			throw new ApiException("Items not found!", "Items was not found with id " + id);

		genericRepo.delete(i);

	}
}
