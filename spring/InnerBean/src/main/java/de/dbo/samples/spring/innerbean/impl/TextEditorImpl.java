package de.dbo.samples.spring.innerbean.impl;

import de.dbo.samples.spring.api.TextEditor;
import de.dbo.samples.spring.api.SpellChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


final class TextEditorImpl implements TextEditor {
	private static final Logger log = LoggerFactory.getLogger( SpellCheckerImpl.class );
	
	protected SpellChecker spellChecker = null;

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
