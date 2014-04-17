package com.tutorialspoint.AnnotatedConfiguration;
 
import org.springframework.beans.factory.annotation.Autowired;

import com.tutorialspoint.TextEditorAbstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class TextEditorImpl extends TextEditorAbstraction {
	private static final Logger log = LoggerFactory.getLogger(TextEditorImpl.class);

	@Autowired
	public TextEditorImpl( SpellCheckerImpl spellChecker) {
		this.spellChecker = spellChecker;
		log.info("Inside TextEditor constructor.");
	}
}
