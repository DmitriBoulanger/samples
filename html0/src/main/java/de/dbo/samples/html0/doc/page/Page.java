package de.dbo.samples.html0.doc.page;

import org.apache.ecs.xhtml.*;

public class Page {

	private final html page = new html("");
	 
	public Page(final String title) {
	   page.addElement(new h1(title));
	   page.addElement(new hr());
	}
	
	public static final void main(String[] args) {
		
		System.out.println(
		new Page("gagaga").page.toString()
		);
	}
	
	
}
