package de.dbo.samples.concordion.simple;

/**
 * 
 * The class to be tested
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class Greeter {
	
	public String greetingFor(String firstName) {
        return String.format("Hello, %s!", firstName);
    }
}
