package com.cleartrip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cleartrip.entity.Items;
import com.cleartrip.repository.GenericRepo;
import com.cleartrip.repository.ItemsRepo;

public class ReportGenerator {

	@Autowired
	static GenericRepo<Items> itemsRepo;

	public static void generateItemReport() {

		// get all the data from items table
		List<Items> items = itemsRepo.getAll();

		saveDataIntoExcel();

	}
	
	/**
	 * Thid method will save the record into excel file
	 */
	private static void saveDataIntoExcel() {
		//file operation
	}

}
