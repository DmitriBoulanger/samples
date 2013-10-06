package de.dbo.samples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All repository/workspace tests
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	  de.dbo.samples.logger.logger0.junit.Alles.class
	, de.dbo.samples.jpa.jpa0.junit.Alles.class
	, de.dbo.samples.tuprolog.tuprolog0.junit.Alles.class})

public class Test {}
