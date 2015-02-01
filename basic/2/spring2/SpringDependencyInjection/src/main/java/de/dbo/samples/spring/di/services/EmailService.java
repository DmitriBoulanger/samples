package de.dbo.samples.spring.di.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public boolean sendMessage(String msg, String rec) {
	log.info("Email Sent to " + rec + " with Message=" + msg);
	return true;
    }

}
