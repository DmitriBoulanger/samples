package com.otv.rmi.client;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otv.rmi.server.IRMIUserService;
import com.otv.rmi.server.RMIUserService;
import com.otv.user.User;

/**
 * RMI Service Client
 * 
 * @author  onlinetechvision.com
 * @since   24 Feb 2012
 * @version 1.0.0
 *
 */
public class RMIServiceClient {
	
	private static Logger logger = Logger.getLogger(RMIUserService.class);
	
	/**
	 * Main method of the RMI Service Client
	 * 
	 */
	public static void main(String[] args) {
		
		logger.debug("RMI Service Client is starting...");
		
		//RMI Client Application Context is started... 
		ApplicationContext context = new ClassPathXmlApplicationContext("rmiClientAppContext.xml");
		
		//Remote User Service is called via RMI Client Application Context...
		IRMIUserService rmiClient = (IRMIUserService) context.getBean("RMIUserService");

		//New User is created...
		User user = new User();
		user.setId(1);
		user.setName("Bruce");
		user.setSurname("Willis");
		
		//The user is added to the remote cache...
		rmiClient.addUser(user);
		
		//The users are gotten via remote cache...
		rmiClient.getUserList();
		
		//The user is deleted from remote cache...
		rmiClient.deleteUser(user);
		
		logger.debug("RMI Service Client is stopped...");
	}
}
