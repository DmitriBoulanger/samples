package de.dbo.samples.jpa0.junit.impl;

import static de.dbo.samples.jpa0.config.PersistenceConfigurationFactory.persistence;
import static de.dbo.samples.jpa0.config.PersistenceConfigurations.PRODUCTION;
import static de.dbo.tools.utils.print.Print.lines;

import de.dbo.samples.jpa0.TransactionRunner;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class TransactionTestNoConnection {
	protected static final Logger log = LoggerFactory.getLogger(TransactionTestNoConnection.class);

	/** Wrapper of the entity manager that persists and queries the database */
	protected static TransactionRunner TRANSACTION_RUNNER;

	@BeforeClass
	public static final void initPersistenceConfiguration() throws Exception {
		final Map<String, String> config = persistence( PRODUCTION);
		TRANSACTION_RUNNER = new TransactionRunner(config, "JEE6-Persistence");
		log.debug("Configuration properties:" + lines(config));
	}
	
	@Test
	public void dummy() {
		
	}
	
	/**
	 * cleans up the test-session
	 */
	@AfterClass
	public static final void closePersistence() {
		if (null != TRANSACTION_RUNNER) {
			TRANSACTION_RUNNER.shutdown();
		}
	}
}
