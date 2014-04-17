package com.tutorialspoint.AnnotatedConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tutorialspoint.TextEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger( Main.class );
	
	/**
	 * @param args not used
	 */
	public static final void main(final String[] args) {
		new Main().test();
	}
	
	@Test
	public final void test(){
		log.info( "running ... " );
			
		final ApplicationContext ctx = 
					new ClassPathXmlApplicationContext("beans-AnnotatedConfiguration.xml"); 
		assertNotNull(ctx);
		final TextEditor textEditor = 
					(TextEditor) ctx.getBean("textEditor"); 
		assertNotNull(textEditor);
		textEditor.spellCheck(); 
		assertTrue("Spellcheker is not a singelton" 
			,ctx.getBean( SpellCheckerImpl.class ) == textEditor.getSpellChecker() );
	}
}
