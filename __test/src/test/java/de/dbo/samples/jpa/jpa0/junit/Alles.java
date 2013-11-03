package de.dbo.samples.jpa.jpa0.junit;

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
	    StudentTest.class
	    ,CloseTest.class
	    ,CloseTestNoConnection.class})

public class Alles {}
