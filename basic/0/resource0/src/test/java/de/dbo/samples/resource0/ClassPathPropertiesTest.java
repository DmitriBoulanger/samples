package de.dbo.samples.resource0;

import de.dbo.samples.util0.Print;

import java.util.Properties;




import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathPropertiesTest {
	private static final Logger log = LoggerFactory.getLogger(ClassPathPropertiesTest.class);

	@Test 
	public void loading() throws Exception {
		final Properties properties = new ClassPathProperties("log4j.properties");
		log.info(Print.lines(properties).toString());
		final Properties properties2 = new ClassPathProperties("data.properties");
		log.info(Print.lines(properties2).toString());
	}
}
