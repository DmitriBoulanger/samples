package de.dbo.samples.maven.mojo;

import static de.dbo.samples.util.print.Print.lines;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesMojoUsageTest {
	private static final Logger log = LoggerFactory.getLogger(PropertiesMojoUsageTest.class);
	
	@Test
	public void properties() {
		log.info(lines(System.getProperties()).toString());
	}
}