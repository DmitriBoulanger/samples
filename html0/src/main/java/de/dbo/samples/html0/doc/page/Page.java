package de.dbo.samples.html0.doc.page;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.h2;
import org.apache.ecs.xhtml.h3;
import org.apache.ecs.xhtml.hr;
import org.apache.ecs.xhtml.html;
import org.apache.ecs.xhtml.p;

public final class Page {

	private final html page = new html("");
	private final h3 summary = new h3();
	 
	public Page(final String title) {
	   page.addElement(new h1(title));
	   page.addElement(new hr());
	   page.addElement(summary);
	}
	
	public void summary(final String text) {
		final h3 summary = new h3();
		summary.addElement(text);
		page.addElement(summary);
	}
	
	public void section(final String title) {
		final h2 section = new h2();
		section.addElement(title);
		page.addElement(section);
	}
	
	public void p(final String text) {
		final p p = new p();
		p.addElement(text);
		page.addElement(p);
	}
	
	
	
	@Override
	public String toString() {
		page.setPrettyPrint(true);
		return page.toString();
	}



	private static final Path target = Paths.get(new File(".").getAbsolutePath()+"/src/main/java/"
    		+Page.class.getName().replace(".", "/")+".html");
	public static final void main(String[] args) throws IOException {
		
		 Files.copy(
				 new ByteArrayInputStream(new Page("Documentation page").page.toString().getBytes())
		    , target, StandardCopyOption.REPLACE_EXISTING);
		
		 
	}
	
	
}
