package de.dbo.samples.maven.propertiesproject.filtering;

import java.util.Properties;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple PropertiesReader.
 */
public class PropertiesReaderTest  {

	@Test
	public void test() throws Exception {
		final String resource = "database.properties";
		final PropertiesReader propertiesReader = new PropertiesReader(resource);
		final Properties properties = propertiesReader.read();
		
		assertNotNull("Properties is NULL", properties);
		assertTrue("Properties are empty", !properties.isEmpty());
		
		// check values defined in the POM
		assertTrue(properties.get("driverClassName").equals("com.mysql.jdbc.Driver"));
		assertTrue(properties.get("username").equals("myusername"));
		assertTrue(properties.get("url").equals("jdbc:mysql://localhost:3306/database?autoReconnect=true"));
	}
}
