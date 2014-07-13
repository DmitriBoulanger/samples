package de.dbo.samples.concordion.simple1;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

/* Run it as a JUnit test. */

@RunWith(ConcordionRunner.class)
public class HelloWorldTest {
    
    private HelloWorld helloWorld;

    @Before
    public void setUp() {
        helloWorld = new HelloWorld();
    }
    
    public String getGreeting() {
        return helloWorld.getMessage();
    }
}
