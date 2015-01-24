package de.dbo.samples.spring.properties.server.impl;

import org.springframework.beans.factory.annotation.Value;

public class ServiceWorker {
	
	@Value("${service.worker.name}")
	private String name;
	
	@Value("${service.worker.value}")
	private String value;
	
	public String ping()  {
		return name + ":" + value;
	}
	
	
	
}
