package de.dbo.samples.spring.properties.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStartUp {

	public static void main(String[] args) throws Exception { 
		Thread.currentThread().setName("RMI-Server start-up");
		System.setProperty("product.conf","conf");
		System.setProperty("service.conf.name","From system properties");
		
		new ClassPathXmlApplicationContext("server.xml").registerShutdownHook();;
		
	}

}
