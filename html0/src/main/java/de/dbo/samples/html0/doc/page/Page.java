package de.dbo.samples.html0.doc.page;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.ecs.Doctype;
import org.apache.ecs.Doctype.XHtml10Transitional;
import org.apache.ecs.XhtmlDocument;
import org.apache.ecs.xhtml.*;

public class Page {
	
	private static final String ENCODING = "UTF-8";
	private static final Doctype DOCTYPE = new XHtml10Transitional();

	private final XhtmlDocument document;
	private final html html;
	private final body body;
	
	private final boolean isCompleteXhtml;
	private final boolean isPrettyPrint;
	
	public Page(final String title) {
	   this(title,false,false);
    }
	 
	public Page(final String documentTitle
			, final boolean isCompleteXhtml, final boolean isPrettyPrint) {
		this.isCompleteXhtml = isCompleteXhtml;
		this.isPrettyPrint = isPrettyPrint;
		if (isCompleteXhtml) {
			
			document = new XhtmlDocument();
			document.setDoctype(DOCTYPE);
			document.setCodeset(ENCODING);
			
			final title title = new title(documentTitle);
			title.setPrettyPrint(isPrettyPrint);
			document.setTitle(title);
			
			html = document.getHtml();
			html.setPrettyPrint(isPrettyPrint);
			
			body  = document.getBody();
			body.setPrettyPrint(isPrettyPrint);
			
			final meta meta = new meta();
			meta.setPrettyPrint(isPrettyPrint);
			meta.setHttpEquiv("content-type");
			meta.setContent("application/xhtml+xml; charset="+ENCODING);
			
			final head head = document.getHead();
			head.setPrettyPrint(isPrettyPrint);
			head.addElement(meta);
			
		} else {
			html = new html("");
			body = new body();
			html.addElement(body);
			document = null;
		}
		body.addElement(new h1(documentTitle));
		body.addElement(new hr());
		
	}
	
	public final  void summary(final String text) {
		final h3 summary = new h3();
		if (isPrettyPrint) {
			summary.setPrettyPrint(true);
		}
		summary.addElement(text);
		body.addElement(summary);
	}
	
	public final  void section(final String title) {
		final h2 section = new h2();
		section.addElement(title);
		if (isPrettyPrint) {
			section.setPrettyPrint(true);
		}
		body.addElement(section);
	}
	
	public final  void p(final String text) {
		final p p = new p();
		if (isPrettyPrint) {
			p.setPrettyPrint(true);
		}
		p.addElement(text);
		body.addElement(p);
	}
	
	public final  void nl() {
		final br nl = new br();
		if (isPrettyPrint) {
			nl.setPrettyPrint(true);
		}
		body.addElement(nl);
	}
	
	public final  void text(final String text) {
		body.addElement(text);
	}
	
	public final  void textem(final String text) {
		final em em = new em();
		if (isPrettyPrint) {
			em.setPrettyPrint(true);
		}
		em.addElement(text);
		body.addElement(em);
	}
	
	public final  List list() {
		final List list = new List();
		if (isPrettyPrint) {
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
	
	public final InputStream getInputStream() {
		return new ByteArrayInputStream(toString().getBytes());
	}
	 
}
