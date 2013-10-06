package de.dbo.tuprolog.junit;

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
	    de.dbo.tuprolog.junit.Main.class
	  , de.dbo.tuprolog.junit.MainMock.class})

public class Alles {}
