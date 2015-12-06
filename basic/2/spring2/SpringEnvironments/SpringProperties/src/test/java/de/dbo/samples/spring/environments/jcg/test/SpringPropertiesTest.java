package de.dbo.samples.spring.environments.jcg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles(profiles = "test")
@ContextConfiguration("classpath:spring/xml-config-context.xml")
public class SpringPropertiesTest extends TestAbstraction {

    @Test
    public void testAppProperties() {
	System.out.println("RunningProperties-Test with complete configuration ...");
	print();
    }
}
