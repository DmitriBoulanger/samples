package de.dbo.samples.spring.ioc;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.spring.api.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	@Test
	public void test() {
		log.info( "running ..." );
		
		final ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml"); 
		assertNotNull(context);
		
		final Hello obj = (Hello) context.getBean("hello"); 
		assertNotNull(obj);
		
		final String message = obj.getMessage();
		log.info("Message: " + message );
		assertNotNull(message);
	}
}
