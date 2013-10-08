package de.dbo.samples.util0.junit;

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
        ProfilerTest.class
        , PrintTest.class})
public class Alles {}
