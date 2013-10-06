package de.dbo.samples.logger.logger0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Using SLF4J implies that an actual logger-binding resources are
 * supplied by the actual run-time system. Therefore, no logging
 * resources should be included in the component-jar. 
 * Therefore, this class has no logger but the corresponding JUnit-test shows 
 * how it should work since the test-classpath has some logger resources
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class Main {
	protected static final Logger log = LoggerFactory.getLogger(Main.class);

	public void log() {
		 log.debug(msg());
	}
	
	public String msg() {
		 return "Using logger " + log.getClass().getName() + " ...";
	}
	
	public Class<?> logger() {
		 return log.getClass();
	}
	
	public static final void main(final String[] args) {
	/*
	this method throws warning:
	SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
	SLF4J: Defaulting to no-operation (NOP) logger implementation
	SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder
	===========================================================================
	Reason: there no logger resources at the class-path
	*/
		final Main main = new Main();
		main.log(); // does nothing!
		System.out.println("SYSTEM.OUT: " + main.msg());
	}
}
