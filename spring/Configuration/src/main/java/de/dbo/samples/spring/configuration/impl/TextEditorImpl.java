package de.dbo.samples.spring.configuration.impl;
 
import de.dbo.samples.spring.api.SpellChecker;
import de.dbo.samples.spring.api.TextEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class TextEditorImpl implements TextEditor {
	private static final Logger log = LoggerFactory.getLogger(TextEditorImpl.class);
	
	protected SpellChecker spellChecker = null;

	@Autowired
	public TextEditorImpl( SpellCheckerImpl spellChecker) {
		this.spellChecker = spellChecker;
		log.info("Inside TextEditor constructor.");
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
