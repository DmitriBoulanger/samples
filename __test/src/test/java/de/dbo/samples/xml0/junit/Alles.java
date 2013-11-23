package de.dbo.samples.xml0.junit;

import de.dbo.samples.xml0.junit.XmlUnitInstanceTest;

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
	XmlUnitInstanceTest.class})

public class Alles {}