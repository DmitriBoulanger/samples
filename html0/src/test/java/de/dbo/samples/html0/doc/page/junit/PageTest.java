package de.dbo.samples.html0.doc.page.junit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.tidy.Tidy;

import static org.junit.Assert.fail;

public class PageTest {
	private static final Logger log = LoggerFactory.getLogger(PageTest.class);

	@Before
	public void init() throws IOException {

	}

	@Test
	public void test() throws IOException {
		final PageInstance page = new PageInstance("300px", true, true);
		page.save();

		final Tidy tidy = new Tidy();
		tidy.setQuiet(false);
		tidy.setOnlyErrors(false);
		tidy.setShowWarnings(true);
		
		final InputStream doc = page.getInputStream();
		final ByteArrayOutputStream parserOutput = new ByteArrayOutputStream();
		tidy.parseDOM(doc, parserOutput);
		doc.close();
		parserOutput.flush();
		parserOutput.close();
		final int errCnt = tidy.getParseErrors() ;
		final int warnCnt = tidy.getParseWarnings();
		final boolean ok = 0==errCnt && 0==warnCnt;
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
