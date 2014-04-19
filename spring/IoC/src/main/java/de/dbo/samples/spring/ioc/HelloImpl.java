package de.dbo.samples.spring.ioc;

import de.dbo.samples.spring.api.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HelloImpl implements Hello { 
	private static final Logger log = LoggerFactory.getLogger( Hello.class );

	protected String message; 

	@Override
	public final void setMessage(final String message){ 
		this.message = message; 
	} 

	 
	@Override
	public final String getMessage(){ 
		log.info("Message : " + message); 
		return message;
	} 
}
