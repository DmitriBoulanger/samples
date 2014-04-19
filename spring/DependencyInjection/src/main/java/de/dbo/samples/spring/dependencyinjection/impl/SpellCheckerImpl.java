package de.dbo.samples.spring.dependencyinjection.impl;

import de.dbo.samples.spring.api.SpellChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SpellCheckerImpl implements SpellChecker  {
	private static final Logger log = LoggerFactory.getLogger( SpellCheckerImpl.class );

	private String lang;
	
	public SpellCheckerImpl(){ 
		log.info("SpellChecker constructor" ); 
	}
	

	public void setLang(String lang){
		this.lang = lang;
		log.info("lang=" + this.lang);
	}
	

	public final void checkSpelling() {
		log.info( "checkSpelling ...");
	}
	
}