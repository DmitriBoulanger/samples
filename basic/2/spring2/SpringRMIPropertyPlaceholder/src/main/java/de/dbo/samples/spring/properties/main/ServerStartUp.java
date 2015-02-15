package de.dbo.samples.spring.properties.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStartUp {

	public static void main(String[] args) throws Exception { 
		Thread.currentThread().setName("RMI-Server start-up");
		System.setProperty("product.conf","conf");
		System.setProperty("service.conf.name","From the system properties");
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("server.xml");
		ctx.registerShutdownHook();
		/* Don't do that: it unbinds the RMI-server (service)!*/
		/* ctx.close(); */
		ctx = null;
		
	}

}
