package de.dbo.samples.spring.dependencyinjection;


import de.dbo.samples.spring.api.TextEditor;
import de.dbo.samples.spring.dependencyinjection.impl.SpellCheckerImpl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Main {
	
	private static final Logger log = LoggerFactory.getLogger( Main.class);

	@Test
	public void test() { 
		log.info("running ... ");
		final ApplicationContext ctx = new ClassPathXmlApplicationContext("DependencyInjection.xml");
		assertNotNull(ctx);
		
		final TextEditor textEditor = (TextEditor) ctx.getBean("textEditor"); 
		assertNotNull(textEditor);
		textEditor.spellCheck();
		
		final SpellCheckerImpl spellChecker = ctx.getBean( SpellCheckerImpl.class ) ;
		assertNotNull( spellChecker );
		assertTrue("Spellcheker is not a singelton" 
				,spellChecker == textEditor.getSpellChecker());
		
		final TextEditor textEditor2 = (TextEditor) ctx.getBean("textEditor2"); 
		assertNotNull(textEditor2);
		textEditor2.spellCheck();
		
		final SpellCheckerImpl spellChecker2 = ctx.getBean( SpellCheckerImpl.class ) ;
		assertNotNull( spellChecker );
		assertTrue("Spellcheker is not a singelton" 
				,spellChecker2 == textEditor2.getSpellChecker());
	}

}
