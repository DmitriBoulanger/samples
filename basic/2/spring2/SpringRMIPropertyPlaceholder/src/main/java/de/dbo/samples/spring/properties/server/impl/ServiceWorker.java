package de.dbo.samples.spring.properties.server.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Server-side internal worker used in the service implementation
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ServiceWorker {
	
	@Value("${service.worker.name}")
	private String name;
	
	@Value("${service.worker.value}")
	private String value;
	
	public String ping()  {
		return name + ":" + value;
	}
	
	//
	// Getters and Setters
	//
	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getValue() {
//		return value;
//	}
//
//	public void setValue(String value) {
//		this.value = value;
//	}
//	
}
