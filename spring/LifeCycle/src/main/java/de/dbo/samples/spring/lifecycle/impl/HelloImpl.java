package de.dbo.samples.spring.lifecycle.impl; 

import de.dbo.samples.spring.api.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
final class HelloImpl implements Hello {
	private static final Logger log = LoggerFactory.getLogger(HelloImpl.class);
	
	private String message;

	public void init() {
		log.info( "Bean is going through init." );
	}

	public void destroy() {
		log.info( "Bean will destroy now." );
	}
	
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