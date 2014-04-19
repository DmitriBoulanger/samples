package de.dbo.samples.spring.dependencyinjection.impl;
 
import de.dbo.samples.spring.api.SpellChecker;
import de.dbo.samples.spring.api.TextEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TextEditorImpl implements TextEditor {
	private static final Logger log = LoggerFactory.getLogger(TextEditorImpl.class);
	
	protected SpellChecker spellChecker = null;
	
	public TextEditorImpl() {
		log.info("TextEditor constructor: spellChecker=" + spellChecker);
	}

	public TextEditorImpl( SpellCheckerImpl spellChecker) {
		this.spellChecker = spellChecker;
		log.info("TextEditor constructor: spellChecker=" + spellChecker);
	}
	
	public void setSpellChecker(SpellChecker spellChecker) {
	 this.spellChecker = spellChecker; 
	 log.info( "setSpellChecker spellChecker=" + spellChecker);
	} 
	
	public SpellChecker getSpellChecker() { 
		log.info( "getSpellChecker ... " ); 
		return spellChecker; 
	}
	
	public final void spellCheck() {
		log.info( "spelling ... " ); 
	}
	
}
