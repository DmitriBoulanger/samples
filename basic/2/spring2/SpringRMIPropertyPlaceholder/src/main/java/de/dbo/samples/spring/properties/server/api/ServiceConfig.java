package de.dbo.samples.spring.properties.server.api;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Instances are given to clients ...
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
@Component
public class ServiceConfig implements Serializable  {
	private static final long serialVersionUID = 4825722878591220118L;

	@Value("${service.conf.location}")
	private String location;
	
	@Value("${service.conf.name}")
	private String name;
	
	public final StringBuilder print() {
		final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + ":");
		sb.append("\n\t - location = " + location);
		sb.append("\n\t - name     = " + name);
		return sb;
	}
	
	//
	// Getters and Setters
	//
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
