package de.dbo.samples.logger0.junit;

import de.dbo.samples.logger0.Main;

import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Using SLF4J implies that an actual logger-binding resources are
 * supplied by the actual run-time system. Therefore, logging
 * resources are only available at the test class-path (see pom.xml).
 * 
 * Below the log4j-logger is used. It is configured in its log4j.xml
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class LoggerTest {
	private static final Logger log = getLogger(LoggerTest.class);
	
	@Test
	public void test() {
		try {
			final String name = org.slf4j.impl.Log4jLoggerAdapter.class.getSimpleName();
			log.debug(name + " found in the class-path");
		} catch (Throwable e) {
			fail("No Log4j-LoggerAdapter found in the class-path");
		}
		
		 final Main main = new Main();
		 final String messageAboutLogger = main.msg();
		 log.info("Logger in the Main-class: " + messageAboutLogger);
		 main.log(); // this does not work in the Main
		 
		 final Class<?> mainLoggerClass = main.logger();
		 final Class<?> expectedLoggerClass = org.slf4j.impl.Log4jLoggerAdapter.class;
		 assertSame("Expected logger is " + expectedLoggerClass.getSimpleName()
				 + " but found " +  mainLoggerClass.getSimpleName()
				 ,mainLoggerClass,   expectedLoggerClass);
		 
	}
}
