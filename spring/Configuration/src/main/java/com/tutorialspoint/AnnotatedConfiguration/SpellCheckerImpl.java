package com.tutorialspoint.AnnotatedConfiguration;


import org.springframework.beans.factory.annotation.Required;

import com.tutorialspoint.SpellCheckerAbstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class SpellCheckerImpl extends  SpellCheckerAbstraction {
	private static final Logger log = LoggerFactory.getLogger( SpellCheckerImpl.class );

	private String lang;
	
	@Required
	public void setLang(String lang){
		this.lang = lang;
		log.info("lang=" + this.lang);
	}
	
}