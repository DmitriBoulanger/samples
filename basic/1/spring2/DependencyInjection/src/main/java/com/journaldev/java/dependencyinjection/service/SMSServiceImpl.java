package com.journaldev.java.dependencyinjection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSServiceImpl implements MessageService {
	private static final Logger log = LoggerFactory.getLogger(SMSServiceImpl.class);

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send SMS
		log.info("SMS sent to "+rec+ " with Message="+msg);
	}

}
