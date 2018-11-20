package com.cleartrip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementSystemApplication {
	
	private static final Logger log = LoggerFactory.getLogger(InventoryManagementSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
		
		log.info("******Service Stared Successfully*****");
		
	}
}
