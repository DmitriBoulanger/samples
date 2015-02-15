package de.dbo.samples.spring.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Start-up of the RMI-server (service)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
	new Server();
	log.info("Waiting for requests ...");
    }
    
    private final ClassPathXmlApplicationContext ctx;
    
    public Server() {
	ctx = new ClassPathXmlApplicationContext("server.xml");
	ctx.registerShutdownHook();
    }
    
    /* 
     *  Don't do that: it unbinds (kills!) the RMI-server (service)! 
     */
    public void shutDown() {
	ctx.close();
    }
}
