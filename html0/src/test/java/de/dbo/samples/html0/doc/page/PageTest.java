package de.dbo.samples.html0.doc.page;

import static org.w3c.tidy.Configuration.UTF8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

public class PageTest {
	private static final Logger log = LoggerFactory.getLogger(PageTest.class);

	@Before
	public void init() throws IOException {

	}

	@Test
	public void test() throws IOException {
		final PageInstance page = new PageInstance(true, true);
		page.save();

		final Tidy tidy = new Tidy();
		tidy.setCharEncoding(UTF8);
		tidy.setQuiet(false);
		tidy.setOnlyErrors(false);
		tidy.setShowWarnings(true);
		
		final InputStream doc = page.getInputStream();
		final ByteArrayOutputStream parserOutput = new ByteArrayOutputStream();
//		final Document document = 
		tidy.parseDOM(doc, parserOutput);
		doc.close();
		parserOutput.flush();
		parserOutput.close();
		final int errCnt = tidy.getParseErrors() ;
		final int warnCnt = tidy.getParseWarnings();
		final boolean ok = 0==errCnt && 0==warnCnt;
//		
//		final String parserMessages = new String(parserOutput.toByteArray());
//		if (!ok && 0 != parserMessages.trim().length()) {
//			log.debug("Parser messages:\n" + parserMessages);
//		}
		
		if (!ok) {
			final String badMessage = 
					"There were parsing problems: "
							+ (errCnt>0?	errCnt + " error(s)":"")
							+ (errCnt>0 && warnCnt<0? " and " : "")
							+ (warnCnt>0?   warnCnt + " warning(s)":"")
							+ ".";
			log.error(badMessage);
			fail(badMessage);
		}
		
	}

}
