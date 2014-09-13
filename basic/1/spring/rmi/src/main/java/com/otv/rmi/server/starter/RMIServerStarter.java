package com.otv.rmi.server.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RMI Server Starter
 * 
 * @author  onlinetechvision.com
 * @since   27 Feb 2012
 * @version 1.0.0
 *
 */
public class RMIServerStarter {

	public static void main(String[] args) {
		
		//RMI Server Application Context is started... 
		new ClassPathXmlApplicationContext("rmiServerAppContext.xml");
	}
}
