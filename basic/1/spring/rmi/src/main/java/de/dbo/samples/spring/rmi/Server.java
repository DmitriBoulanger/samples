package de.dbo.samples.spring.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {
	private static final Logger log = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args){
		new ClassPathXmlApplicationContext("server.xml");
		log.info("Waiting for requests");
	}
}
