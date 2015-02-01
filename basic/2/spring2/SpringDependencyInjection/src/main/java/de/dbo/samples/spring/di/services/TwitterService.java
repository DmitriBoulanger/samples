package de.dbo.samples.spring.di.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterService implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(TwitterService.class);

    public boolean sendMessage(String msg, String rec) {
	log.info("Twitter message Sent to " + rec + " with Message=" + msg);
	return true;
    }

}
