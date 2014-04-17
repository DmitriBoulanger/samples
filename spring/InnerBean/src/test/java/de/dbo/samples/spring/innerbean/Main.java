package de.dbo.samples.spring.innerbean;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.spring.api.TextEditor;

public class Main {
	
	@Test
	public void test() {
		
		final ApplicationContext context = new ClassPathXmlApplicationContext("InnerBean.xml"); 
		assertNotNull(context);
		
		final TextEditor textEditor = (TextEditor) context.getBean("textEditor"); 
		assertNotNull(textEditor);
		textEditor.spellCheck(); 
	}
}
