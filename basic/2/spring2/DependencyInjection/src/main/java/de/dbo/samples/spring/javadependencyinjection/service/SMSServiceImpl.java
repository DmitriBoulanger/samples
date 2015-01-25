package de.dbo.samples.spring.javadependencyinjection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSServiceImpl implements Service {
	private static final Logger log = LoggerFactory.getLogger(SMSServiceImpl.class);

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send SMS
		log.info("SMS sent to "+rec+ " with Message="+msg);
	}

}
