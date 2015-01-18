package com.journaldev.java.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {
	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	public void sendEmail(String message, String receiver){
		//logic to send email
		log.info("Email sent to "+receiver+ " with Message="+message);
	}
}
