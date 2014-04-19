package de.dbo.samples.spring.configuration.java.impl;

import de.dbo.samples.spring.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TextEditorImpl implements TextEditor {
	private static final Logger log = LoggerFactory.getLogger(TextEditorImpl.class);

	protected SpellChecker spellChecker = null;
	
	public TextEditorImpl( SpellChecker spellChecker ) {
		log.info("constructor with spell-cheker " + spellChecker );
		this.spellChecker = spellChecker;
	}
	
	@Override
	public void setSpellChecker(SpellChecker spellChecker) {
	 log.info( "setSpellChecker ... " ); 
	 this.spellChecker = spellChecker; 
	} 
	
	@Override
	public SpellChecker getSpellChecker() { 
		log.info( "getSpellChecker ... " ); 
		return spellChecker; 
	}
	
	@Override
	public final void spellCheck() {
		log.info( "spelling ... " ); 
	}

}
