package de.dbo.samples.spring.configuration.java.impl;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.dbo.samples.spring.api.*;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class Config {
	private static final Logger log = LoggerFactory.getLogger(Config.class);
	
	@Bean
	public TextEditor textEditor() {
		log.info("creating instance of TextEditor ...");
		return new TextEditorImpl( spellChecker() );
	}

	@Bean
	public SpellChecker spellChecker() {
		log.info("creating instance of SpellChecker ...");
		return new SpellCheckerImpl();
	}
}
