package de.dbo.samples.spring.environments.jcg.test;

import junit.framework.TestCase;

import de.dbo.samples.spring.environments.env.GenericEnv;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;
import de.dbo.samples.spring.environments.jcg.prop.JmsProperties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ashraf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//Change it to your desired profile
//@ActiveProfiles(profiles = "test") do: mvn clean install -Dspring.profiles.active="test"
@ContextConfiguration("classpath:spring/mini-xml-config-context.xml")
public class MiniConfigSpringPropertiesTest extends SpringPropertiesTestAbstraction {
    
	@BeforeClass 
	public static final void setProfileNameUsingSystemProperties() {
	    System.setProperty("spring.profiles.active", "prod");
	}

	@Test
	public void testAppProperties() {
		System.out.println("Running Properties-Test with minimal configuration ...");
		print();
	}
	
}