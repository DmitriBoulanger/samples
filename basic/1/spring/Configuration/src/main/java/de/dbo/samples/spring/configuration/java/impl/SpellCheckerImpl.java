package de.dbo.samples.spring.configuration.java.impl;

import de.dbo.samples.spring.api.SpellChecker;

import org.springframework.beans.factory.annotation.Required;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SpellCheckerImpl implements SpellChecker  {
	private static final Logger log = LoggerFactory.getLogger( SpellCheckerImpl.class );

	private String lang;
	
	@Required
	public void setLang(String lang){
		this.lang = lang;
		log.info("lang=" + this.lang);
	}
	
	@Override
	public final void checkSpelling() {
		log.info( "checkSpelling ...");
	}
	
}