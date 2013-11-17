package de.dbo.samples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All repository/workspace tests
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

@RunWith(Suite.class)
@SuiteClasses({
          de.dbo.samples.logger.logger0.junit.Alles.class
        , de.dbo.samples.jpa.jpa0.junit.Alles.class
        , de.dbo.samples.util0.junit.Alles.class
        , de.dbo.samples.xml0.junit.Alles.class
        , de.dbo.samples.html0.junit.Alles.class
        , de.dbo.samples.tuprolog.tuprolog0.junit.Alles.class})
public class Test {}
