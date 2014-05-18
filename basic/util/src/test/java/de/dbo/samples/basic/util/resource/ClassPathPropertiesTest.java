package de.dbo.samples.basic.util.resource;

import de.dbo.samples.basic.util.resource.ClassPathProperties;
import de.dbo.samples.basic.util.print.Print;

import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathPropertiesTest {
	private static final Logger log = LoggerFactory.getLogger(ClassPathPropertiesTest.class);

	@Test 
	public void loading() throws Exception {
		final Properties properties = new ClassPathProperties("data.properties");
		log.info(Print.lines(properties).toString());
		final Properties properties2 = new ClassPathProperties("de/dbo/samples/basic/util/resource/data.properties");
		log.info(Print.lines(properties2).toString());
	}
}
