package com.cleartrip.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleartrip.entity.Items;
import com.cleartrip.exception.ApiException;
import com.cleartrip.services.ItemsService;

@RestController
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	ItemsService itemsService;
	
	
	
	@GetMapping("/get/all")
	public List<Items> getAll(@PageableDefault(page=0 , size=10) Pageable pageable)  {
		
		return itemsService.findAllItems(pageable);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) throws ApiException {

		return ResponseEntity.ok(itemsService.findItemById(id));
	}

	@PostMapping(path = "/add", produces = "application/json")
	public ResponseEntity<?> addPen(@Valid @RequestBody Items item)   {

		return ResponseEntity.ok("{ \"id\" : " + itemsService.saveItemToDB(item) + "}");
	}
	
	@PostMapping(path = "/checkout/{count}", produces = "application/json")
	public ResponseEntity<?> checkoutPen(@Valid @RequestBody Items item, int count)   {

		return ResponseEntity.ok("{"+ itemsService.checkoutItems(item, count)+ "}");
	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> updatePen(@PathVariable Long id, @RequestBody Items i)
			throws ApiException  {
		itemsService.updateItems(id, i);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deletePenById(@PathVariable Long id) throws ApiException {
		itemsService.deleteItemById(id);
		return ResponseEntity.ok().build();
	}

}
