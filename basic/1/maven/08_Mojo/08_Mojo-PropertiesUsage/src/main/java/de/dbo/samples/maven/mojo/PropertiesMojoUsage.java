package de.dbo.samples.maven.mojo;

import static de.dbo.samples.util.print.Print.lines;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * Required properties are only set by the maven plug-in
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class PropertiesMojoUsage {
	private static final Logger log = LoggerFactory.getLogger( PropertiesMojoUsage.class);
	
	public PropertiesMojoUsage() {
		
	}
	
	public void logMavenProperties() {
		log.info("Maven-properties: " + lines(System.getProperties(),"maven"));  
	}
	
	public void logVersionProperties() {
		log.info("Version-properties: " + lines(System.getProperties(),"-version"));  
	}
	
	public void logSystemProperties() {
		log.info("System-properties: " + lines(System.getProperties()));  
	}
}