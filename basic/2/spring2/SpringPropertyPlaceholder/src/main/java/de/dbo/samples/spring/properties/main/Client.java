package de.dbo.samples.spring.properties.main;

import de.dbo.samples.spring.properties.server.api.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class Client {
	private static final Logger log = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) throws Exception {
		final ApplicationContext ctx = new ClassPathXmlApplicationContext("client.xml");
		final Service service = (Service) ctx.getBean("service");
		log.info(service.ping());
		log.info(service.config().print().toString());
	}
}
