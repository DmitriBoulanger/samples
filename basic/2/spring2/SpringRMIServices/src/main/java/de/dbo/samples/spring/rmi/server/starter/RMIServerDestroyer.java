package de.dbo.samples.spring.rmi.server.starter;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * RMI Server Destroyer
 *
 */
public class RMIServerDestroyer {
	private static final Logger log = LoggerFactory.getLogger(RMIServerDestroyer.class);
	
	/**
	 * destroys all RMI-services
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("rmiServerAppContext.xml");
		destroy(ctx,"RMIUserServiceExporter");
		destroy(ctx,"RMIManagerServiceExporter");
	}
	
	private static final void destroy(final AbstractXmlApplicationContext ctx , final String rmiService) {
		log.debug(rmiService + " is being destroyed ...");
		final RmiServiceExporter rmiServiceExporter;
		try {
			rmiServiceExporter =  (RmiServiceExporter) ctx.getBean(rmiService);
		} catch (Exception e) {
			log.error("Can't find RMI-Service " + rmiService,e);
			return;
		}
		
		try {
			rmiServiceExporter.destroy();
			log.info(rmiServiceExporter.getServiceInterface().getSimpleName() + " destroyed");
		} catch (Exception e) {
			log.error("Can't destory RMI-Service " + rmiService,e);
		} 
	}
}
