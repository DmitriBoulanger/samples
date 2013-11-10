package de.dbo.samples.html0.doc.page;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PageTest {
	
	@Before
	public void init() throws IOException {
		 
	}
	
	@Test
	public void test() throws IOException {
		new PageInstance(true /* complete XHTML-document */).save();
	}
	
}
