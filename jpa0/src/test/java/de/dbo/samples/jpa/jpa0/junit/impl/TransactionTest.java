package de.dbo.samples.jpa.jpa0.junit.impl;

import static de.dbo.samples.jpa.jpa0.config.PersistenceConfigurationFactory.persistence;
import static de.dbo.samples.jpa.jpa0.config.PersistenceConfigurations.TEST;
import static de.dbo.samples.util0.Print.lines;

import de.dbo.samples.jpa.jpa0.TransactionRunner;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionTest {
	protected static final Logger log = LoggerFactory.getLogger(TransactionTest.class);

	/** Wrapper of the entity manager that persists and queries the database */
	protected static TransactionRunner TRANSACTION_RUNNER;

	@BeforeClass
	public static final  void init() throws Exception {
		final Map<String, String> config = persistence(TEST);
		TRANSACTION_RUNNER = new TransactionRunner(config, "JEE6-Persistence");
		log.debug("Configuration properties:" + lines(config));
	}
	
	/**
	 * cleans up the test-session
	 */
	@AfterClass
	public static final void close() {
		if (null != TRANSACTION_RUNNER) {
			TRANSACTION_RUNNER.shutdown();
		}
	}
}
