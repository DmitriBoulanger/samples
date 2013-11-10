package de.dbo.samples.html0.doc.page;

import org.apache.ecs.Doctype.Html401Transitional;
import org.apache.ecs.XhtmlDocument;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.em;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.h2;
import org.apache.ecs.xhtml.h3;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.hr;
import org.apache.ecs.xhtml.html;
import org.apache.ecs.xhtml.meta;
import org.apache.ecs.xhtml.*;

public class Page {
	
	private static final String ENCODING = "UTF-8";

	private final XhtmlDocument document;
	private final html html;
	private final body body;
	private final head head;
	private final boolean isCompleteXhtml;
	
	public Page(final String title) {
	   this(title,false);
    }
	 
	public Page(final String title, final boolean isCompleteXhtml) {
		this.isCompleteXhtml = isCompleteXhtml;
		if (isCompleteXhtml) {
			document = new XhtmlDocument();
			document.setDoctype(new Html401Transitional());
			document.setCodeset(ENCODING);
			document.setTitle(new title(title));
			html = document.getHtml();
			body  = document.getBody();
			head = document.getHead();
			head.setPrettyPrint(true);
			final meta meta = new meta();
			meta.setPrettyPrint(true);
			meta.setHttpEquiv("content-type");
			meta.setContent("application/xhtml+xml; charset="+ENCODING);
			head.addElement(meta);
			html.setPrettyPrint(true);
			body.setPrettyPrint(true);
			meta.setPrettyPrint(true);
		} else {
			html = new html("");
			body = new body();
			html.addElement(body);
			document = null;
			head = null;
		}
		body.addElement(new h1(title));
		body.addElement(new hr());
		
	}
	
	public final  void summary(final String text) {
		final h3 summary = new h3();
		if (isCompleteXhtml) {
			summary.setPrettyPrint(true);
		}
		summary.addElement(text);
		body.addElement(summary);
	}
	
	public final  void section(final String title) {
		final h2 section = new h2();
		section.addElement(title);
		if (isCompleteXhtml) {
			section.setPrettyPrint(true);
		}
		body.addElement(section);
	}
	
	public final  void p(final String text) {
		final p p = new p();
		if (isCompleteXhtml) {
			p.setPrettyPrint(true);
		}
		p.addElement(text);
		body.addElement(p);
	}
	
	public final  void nl() {
		final br nl = new br();
		if (isCompleteXhtml) {
			nl.setPrettyPrint(true);
		}
		body.addElement(nl);
	}
	
	public final  void text(final String text) {
		body.addElement(text);
	}
	
	public final  void textem(final String text) {
		final em em = new em();
		if (isCompleteXhtml) {
			em.setPrettyPrint(true);
		}
		em.addElement(text);
		body.addElement(em);
	}
	
	public final  List list() {
		final List list = new List();
		if (isCompleteXhtml) {
			list.setPrettyPrint(true);
		}
		body.addElement(list);
		return list;
	}
	
	public final List list(final String text) {
		text(text);
		return list();
	}
	
	@Override
	public final String toString() {
		if (isCompleteXhtml) {
			return document.toString(document.getCodeset());
		} else {
			return html.toString(ENCODING);
		}
	}
	
	private static final void head(final String title, head head) {
		
	}
	 
}
