package de.dbo.samples.jpa.jpa0.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All project tests
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	    StudentTest.class
	    ,CloseTest.class})

public class Alles {}
