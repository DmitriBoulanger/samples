package de.dbo.samples.maven.propertiesproject.filtering.profile;

import de.dbo.samples.maven.propertiesproject.filtering.PropertiesReader;

import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *  
 */
public class PropertiesReaderWithProfileTest {
	private static final Logger log = LoggerFactory.getLogger(PropertiesReaderWithProfileTest.class);
	
	@Test
	public void test() throws Exception {
		final String resource = "database.properties";
		final PropertiesReader propertiesReader = new PropertiesReader(resource);
		final Properties properties = propertiesReader.read();
		
		assertNotNull("Properties is NULL", properties);
		assertTrue("Properties are empty", !properties.isEmpty());
		
		final String profile = (String) properties.get("profile");
		assertNotNull("Profile is NULL", profile);
		log.info("profile=" + profile); // ${database.profile} means no profile!
		
		
		assertTrue(properties.get("driverClassName").equals("com.mysql.jdbc.Driver"));
		
		// check values defined in the POM wrt profile
		
		if (profile.equals("ga")) {
			assertTrue("profile="+profile + ": user name icorret"
					, properties.get("username").equals("qaduser"));
			assertTrue("profile="+profile + ": url is incorrect"
					, properties.get("url").equals("jdbc:mysql://localhost:3306/gadb01?autoReconnect=true"));
		} else if  (profile.equals("production")) {
			assertTrue("profile="+profile + ": user name icorret"
					,properties.get("username").equals("produser"));
			assertTrue("profile="+profile + ": url is incorrect"
					,properties.get("url").equals("jdbc:mysql://localhost:3306/pdb01?autoReconnect=true"));
		} else if  (profile.equals("${database.profile}"))  {
			assertTrue("profile="+profile + ": user name icorret"
					,properties.get("username").equals("myusername"));
			assertTrue("profile="+profile + ": url is incorrect"
					,properties.get("url").equals("jdbc:mysql://localhost:3306/database?autoReconnect=true"));
		} else {
			fail("profile="+profile + "is unknown");
		}
		
	}
	
	
}
