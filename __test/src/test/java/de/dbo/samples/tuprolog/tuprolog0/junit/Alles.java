package de.dbo.samples.tuprolog.tuprolog0.junit;

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
        de.dbo.samples.tuprolog.tuprolog0.junit.Main.class
        , de.dbo.samples.tuprolog.tuprolog0.junit.MainMock.class})
public class Alles {}
