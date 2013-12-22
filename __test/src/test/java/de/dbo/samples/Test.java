package de.dbo.samples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All samples-tests
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
          de.dbo.samples.logger0.junit.Alles.class
        , de.dbo.samples.jpa0.junit.Alles.class
        , de.dbo.samples.util0.junit.Alles.class
        , de.dbo.samples.xml0.junit.Alles.class
        , de.dbo.samples.html0.junit.Alles.class
        , de.dbo.samples.tuprolog0.junit.Alles.class})
public class Test {}
