package de.dbo.samples.spring.rmi.server.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RMI Server Starter
 *
 */
public class RMIServerStarter {
    private static final Logger log = LoggerFactory.getLogger(RMIServerStarter.class);

    public static final void run() {
	log.info("RMI Server Application Context is being started ...");
	new ClassPathXmlApplicationContext("rmiServer.xml");
	log.info("Waiting for requests ...");
    }
}
