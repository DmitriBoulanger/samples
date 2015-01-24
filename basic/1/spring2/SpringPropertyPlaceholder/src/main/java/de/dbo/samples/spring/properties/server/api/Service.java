package de.dbo.samples.spring.properties.server.api;

public interface Service {
	
	public String ping() throws Exception;
	
	public ServiceConfig config() throws Exception;

}
