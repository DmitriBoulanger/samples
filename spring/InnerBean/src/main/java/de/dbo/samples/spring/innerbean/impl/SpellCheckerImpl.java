package de.dbo.samples.spring.innerbean.impl;

import de.dbo.samples.spring.api.SpellChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


final class SpellCheckerImpl implements SpellChecker  {
	private static final Logger log = LoggerFactory.getLogger( SpellCheckerImpl.class );

	
	public SpellCheckerImpl() {
		log.info( "default constructor" );
	}
	
	@Override
	public final void checkSpelling() {
		log.info( "checkSpelling ...");
	}

}