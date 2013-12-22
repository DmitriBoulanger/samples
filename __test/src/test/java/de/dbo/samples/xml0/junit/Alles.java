package de.dbo.samples.xml0.junit;

import de.dbo.samples.xml0.junit.XmlUnitInstanceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests in samples.xml0-project
 * 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	XmlUnitInstanceTest.class})

public class Alles {}