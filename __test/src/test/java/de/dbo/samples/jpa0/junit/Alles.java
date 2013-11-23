package de.dbo.samples.jpa0.junit;

import de.dbo.samples.jpa0.junit.CloseTest;
import de.dbo.samples.jpa0.junit.CloseTestNoConnection;
import de.dbo.samples.jpa0.junit.StudentTest;

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
