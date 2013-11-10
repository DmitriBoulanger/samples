package de.dbo.samples.html0.doc.page;

import org.apache.ecs.xhtml.li;
import org.apache.ecs.xhtml.ul;

public final class List extends ul {
	
	/**
	 * Vertical list of items
	 */
	List() {
		
	}
	
	/**
	 * adds item to this list
	 * @param text
	 */
	public void item(final String text) {
		final li item = new li(text);
		addElement(item);
	}
}
