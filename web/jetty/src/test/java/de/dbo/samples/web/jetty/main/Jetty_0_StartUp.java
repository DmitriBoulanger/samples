package de.dbo.samples.web.jetty.main;

import static de.dbo.samples.web.jetty.JettyPrint.print;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Start-up of the standalone Jetty server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class Jetty_0_StartUp  {
    private static final Logger log = LoggerFactory.getLogger(Jetty_0_StartUp.class);
    
    public static final void main(final String[] args) throws Throwable {

	
	// server will be available for specified number of milliseconds ...
	Thread.sleep(100000);
    }
}
