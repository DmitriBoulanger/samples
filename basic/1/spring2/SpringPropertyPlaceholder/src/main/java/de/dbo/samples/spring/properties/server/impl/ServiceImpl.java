package de.dbo.samples.spring.properties.server.impl;

import de.dbo.samples.spring.properties.server.api.Service;
import de.dbo.samples.spring.properties.server.api.ServiceConfig;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Sever-side service implementation
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class ServiceImpl implements Service {
	
	@Autowired(required=true)
	private ServiceWorker worker;
	
	@Autowired(required=true)
	private ServiceConfig config;

	@Override
	public String ping() throws Exception {
		return "Hi! Service worker: "
	    + "\n\t - worker hash-code " + worker.hashCode()
	    + "\n\t - worker ping      " + worker.ping()
	    ;
	}
	
	@Override
	public ServiceConfig config()  {
		return getConfig();
	}
	
	// Getters and Setters

	public ServiceWorker getWorker() {
		return worker;
	}

	public void setWorker(ServiceWorker worker) {
		this.worker = worker;
	}

	public ServiceConfig getConfig() {
		return config;
	}

	public void setConfig(ServiceConfig config) {
		this.config = config;
	}
	
}
