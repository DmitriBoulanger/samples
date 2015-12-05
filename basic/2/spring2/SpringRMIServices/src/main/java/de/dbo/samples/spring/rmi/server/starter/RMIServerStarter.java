package de.dbo.samples.spring.rmi.server.starter;


import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RMI Server Starter
 *
 */
public class RMIServerStarter implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(RMIServerStarter.class);
    
    public static final void main(final String args[]) throws RemoteException {
	new Thread(new RMIServerStarter()).start();
    }
    
    private ClassPathXmlApplicationContext ctx;
    
    public final void closeCtx() {
	if (null!=ctx) {
	    ctx.close();
	}
	ctx = null;
    }

    public final void run() {
	log.info("RMI Server Application Context is being started ...");
	ctx = new ClassPathXmlApplicationContext("rmiServer.xml");
	ctx.registerShutdownHook();
	log.info("Waiting for requests ...");
    }
}
