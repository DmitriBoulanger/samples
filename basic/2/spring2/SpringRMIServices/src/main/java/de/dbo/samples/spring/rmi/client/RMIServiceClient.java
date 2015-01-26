package de.dbo.samples.spring.rmi.client;

import de.dbo.samples.spring.rmi.server.RMIManagerService;
import de.dbo.samples.spring.rmi.server.RMIUserService;
import de.dbo.samples.spring.rmi.server.RMIUserServiceImpl;
import de.dbo.samples.spring.rmi.user.User;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

/**
 * RMI Service Client
 * 
 * @author  onlinetechvision.com
 * @since   24 Feb 2012
 * @version 1.0.0
 *
 */
public class RMIServiceClient {
	private static Logger log = LoggerFactory.getLogger(RMIUserServiceImpl.class);
	
	/**
	 * Main method of the RMI Service Client
	 */
	public static void main(String[] args) {
		
		log.info("RMI Service Client is starting...");
		
		// RMI Client Application Context is started... 
		ApplicationContext context = new ClassPathXmlApplicationContext("rmiClient.xml");
		
		// Remote User Service is called via RMI Client Application Context...
		RMIUserService rmiClient = (RMIUserService) context.getBean("RMIUserService");
		RMIManagerService rmiManager = (RMIManagerService) context.getBean("RMIManagerService");

		// New User is created...
		User user = new User();
		user.setId(1);
		user.setName("Bruce");
		user.setSurname("Willis");
		
		// The user is added to the remote cache...
		rmiClient.addUser(user);
		
		// The users are gotten via remote cache...
		rmiClient.getUserList();
		
		// The user is deleted from remote cache...
		rmiClient.deleteUser(user);
		
		log.info("RMI Service Client is stopped...");
		

	}
}
