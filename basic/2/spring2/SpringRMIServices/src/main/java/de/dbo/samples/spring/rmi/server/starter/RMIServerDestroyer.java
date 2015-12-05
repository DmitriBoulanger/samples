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
public class RMIServerDestroyer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(RMIServerDestroyer.class);

    
    public static final void main(final String args[]) throws RemoteException {
	new Thread(new RMIServerDestroyer()).start();
    }
    
    private ClassPathXmlApplicationContext ctx;
    
    public final void closeCtx() {
	if (null!=ctx) {
	    ctx.close();
	}
	ctx = null;
    }
    
    /**
     * destroys all RMI-services
     * 
     * @throws RemoteException
     */
    public final void run()   {
	try {
	    ctx = new ClassPathXmlApplicationContext("rmiServer.xml");
	    destroy(ctx, "RMIUserServiceExporter");
	    destroy(ctx, "RMIManagerServiceExporter");
	} catch (Throwable e) {
	    log.error("Failure running RMIServerDestroyer:", e);
	}
    }

    //

    private static final void destroy(final AbstractXmlApplicationContext ctx, final String rmiService) {
	log.debug(rmiService + " is being destroyed ...");
	final RmiServiceExporter rmiServiceExporter;
	try {
	    rmiServiceExporter = (RmiServiceExporter) ctx.getBean(rmiService);
	} catch (Exception e) {
	    log.error("Can't find RMI-Service " + rmiService, e);
	    return;
	}

	try {
	    rmiServiceExporter.destroy();
	    log.info(rmiServiceExporter.getServiceInterface().getSimpleName() + " destroyed");
	} catch (Exception e) {
	    log.error("Can't destory RMI-Service " + rmiService, e);
	}
    }
}
