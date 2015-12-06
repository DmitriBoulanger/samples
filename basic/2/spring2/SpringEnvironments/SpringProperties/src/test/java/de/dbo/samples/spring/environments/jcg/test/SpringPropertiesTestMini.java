package de.dbo.samples.spring.environments.jcg.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
//Change it to your desired profile
//@ActiveProfiles(profiles = "test") do: mvn clean install -Dspring.profiles.active="test"
@ContextConfiguration("classpath:spring/xml-config-context-mini.xml")
public class SpringPropertiesTestMini extends TestAbstraction {
    
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