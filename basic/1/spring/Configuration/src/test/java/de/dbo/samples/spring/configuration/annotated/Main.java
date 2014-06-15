package de.dbo.samples.spring.configuration.annotated;

import de.dbo.samples.spring.api.SpellChecker;
import de.dbo.samples.spring.api.TextEditor;
import de.dbo.samples.spring.configuration.java.impl.SpellCheckerImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
		log.info( "running Spring Anntotated-Configuration assertions ... " );
			
		final ApplicationContext ctx = new ClassPathXmlApplicationContext("Configuration.xml"); 
		assertNotNull(ctx);
		
		final TextEditor textEditor = (TextEditor) ctx.getBean("textEditor"); 
		assertNotNull(textEditor);
		
		final SpellChecker spellChecker = (SpellChecker) ctx.getBean( "spellChecker" );
		assertNotNull(spellChecker);
		
		final SpellChecker spellChecker2 = textEditor.getSpellChecker();
		assertNotNull(spellChecker2);
		
		textEditor.spellCheck(); 
		assertTrue("Spellcheker is not a singelton" 
			, spellChecker == spellChecker2 );
	}
}
