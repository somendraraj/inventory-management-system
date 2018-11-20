package com.cleartrip.scheduler;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cleartrip.services.ReportGenerator;

public class ReportScheduler extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(ReportScheduler.class);

	@Override
	public void run() {
		
		log.info("Report scheduler stared!!!");
		ReportGenerator.generateItemReport();
		log.info("Report scheduler ended!!!");
		
	}
}
