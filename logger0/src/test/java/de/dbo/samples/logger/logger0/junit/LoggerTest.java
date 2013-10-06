package de.dbo.samples.logger.logger0.junit;
 
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.logger.logger0.Main;
import static org.junit.Assert.assertSame;;

/**
 * Using SLF4J implies that an actual logger-binding resources are
 * supplied by the actual run-time system. Therefore, logging
 * resources are only available at the test class-path (see pom.xml).
 * 
 * Below the log4j-logger is used. It is configured in log4j.xml
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public class LoggerTest {
	
	@Test
	public void test() {
		 final Main main = new Main();
		 final Logger log = LoggerFactory.getLogger(LoggerTest.class);
		 log.info(main.msg());
		 main.log(); // this does not work in the Main
		 assertSame("Expected logger is " + org.slf4j.impl.Log4jLoggerAdapter.class.getName()
				 + " but found " +  main.logger()
				 ,main.logger(),  org.slf4j.impl.Log4jLoggerAdapter.class);
		 
	}

}
