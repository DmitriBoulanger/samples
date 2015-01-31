package de.dbo.samples.spring.properties.main;

import de.dbo.samples.spring.properties.server.api.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ClientTest {
	private static final Logger log = LoggerFactory.getLogger(ClientTest.class);
	
	public static void main(String[] args) throws Exception {
		Thread.currentThread().setName("Client Test");
		new Client().run();
	}
	
	private final ApplicationContext ctx;
	
	public ClientTest() {
		 ctx = new ClassPathXmlApplicationContext("client-test.xml");
	}
	
	public void run() throws Exception {
		final Service service = (Service) ctx.getBean("serviceTest");
		log.info(service.ping());
		log.info(service.config().print().toString());
	}
}
