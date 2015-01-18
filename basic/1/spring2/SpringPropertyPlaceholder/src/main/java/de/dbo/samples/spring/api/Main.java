package de.dbo.samples.spring.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
	 ClassPathXmlApplicationContext ctx = 
			 new ClassPathXmlApplicationContext("spring-context.xml");
	 
	 
	  log.info( System.getProperty("db.user") );
	  log.info( System.getProperty("db.pass") );
	  

	  
	  

	}

}
