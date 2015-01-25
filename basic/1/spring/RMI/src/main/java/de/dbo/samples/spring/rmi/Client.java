package de.dbo.samples.spring.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Client's call of the RMI-server (service)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class Client {
	private static final Logger log = LoggerFactory.getLogger(Client.class);
	
	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("client.xml");
		final Service service = (Service) context.getBean("serviceProxy");
		log.info(" cube = " + service.cube(7));
	}
}