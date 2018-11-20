package com.cleartrip.services;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cleartrip.entity.Items;
import com.cleartrip.exception.ApiException;
import com.cleartrip.repository.GenericRepo;

public class ExcelToDBReader {

	private static final Logger log = LoggerFactory.getLogger(ExcelToDBReader.class);

	@Autowired
	GenericRepo<Items> genericRepo;
	
	public void bulkUploader(String fileName) {
		try {
			File file = new File(fileName);
			
			//read from excell file and do batch insert into db for 100 size...
			List<Items> list = null;
			list = readFromExcel(file);
			genericRepo.batchSave(list);
			
		} catch (Exception e) {
			log.error("Exception in bulkUploader using excel...", e);
		} finally {

		}
	}
	
	private List<Items> readFromExcel(File file){
		//file reader
		List<Items> items = null;
		return items;
	}

}
