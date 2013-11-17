package de.dbo.samples.html0.junit;

import de.dbo.samples.html0.doc.page.PageTest;

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
	    PageTest.class})

public class Alles {}

