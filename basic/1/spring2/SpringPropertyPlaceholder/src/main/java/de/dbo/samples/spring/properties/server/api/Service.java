package de.dbo.samples.spring.properties.server.api;

/**
 * Service exposed to clients
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Service {
	
	public String ping() throws Exception;
	
	public ServiceConfig config() throws Exception;

}
