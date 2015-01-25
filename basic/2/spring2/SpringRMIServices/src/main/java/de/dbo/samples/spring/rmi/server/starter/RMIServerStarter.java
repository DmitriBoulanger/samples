package de.dbo.samples.spring.rmi.server.starter;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * RMI Server Starter
 *
 */
public class RMIServerStarter {
	private static final Logger log = LoggerFactory.getLogger(RMIServerStarter.class);

	public static void main(String[] args) throws RemoteException {

		log.info("RMI Server Application Context is being started ...");
		new ClassPathXmlApplicationContext("rmiServerAppContext.xml");
		log.info("Waiting for requests ...");
	}
	
}
