package de.dbo.samples.spring.configuration.java; 

import de.dbo.samples.spring.configuration.java.impl.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger( Main.class);

	@Test
	public void test() { 
		log.info("running ... ");
		final ApplicationContext ctx = new AnnotationConfigApplicationContext( Config.class ); 
		assertNotNull(ctx);
		final TextEditorImpl textEditor = ctx.getBean( TextEditorImpl.class ); 
		assertNotNull(textEditor);
		
		textEditor.spellCheck(); 
		
		final SpellCheckerImpl spellChecker = ctx.getBean( SpellCheckerImpl.class ) ;
		assertNotNull( spellChecker );
		assertTrue("Spellcheker is not a singelton" 
				,spellChecker == textEditor.getSpellChecker());
	}
}
