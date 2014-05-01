package de.dbo.samples.resource0.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All project tests
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	 ClassPathPropertiesTest.class})
public class Alles {}
