package de.dbo.samples.concordion.simple;

import org.concordion.integration.junit4.ConcordionRunner;
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
public class HelloWorldFixture {

    public String greetingFor(String firstName) {
        return "Hello " + firstName + "!";
    }
}