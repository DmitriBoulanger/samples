package de.dbo.samples.spring.javadependencyinjection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailServiceImpl implements Service {
	private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send email
		log.info("Email sent to "+rec+ " with Message="+msg);
	}

}
