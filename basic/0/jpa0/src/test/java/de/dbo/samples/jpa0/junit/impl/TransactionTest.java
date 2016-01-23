package de.dbo.samples.jpa0.junit.impl;

import static de.dbo.samples.jpa0.config.PersistenceConfigurationFactory.persistence;
import de.dbo.samples.jpa0.config.PersistenceConfigurations;
import static de.dbo.tools.utils.print.Print.lines;

import de.dbo.samples.jpa0.PersistenceManager;

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
public abstract class TransactionTest {
	protected static final Logger log = LoggerFactory.getLogger(TransactionTest.class);

	/** 
	 * Wrapper of the entity manager that persists and queries the database 
	 */
	protected static PersistenceManager PERSISTENCE_MANAGER;

	@BeforeClass
	public static final void initPersistenceConfiguration() throws Exception {
		final Map<String, String> config = persistence(PersistenceConfigurations.PERSISTENT);
		PERSISTENCE_MANAGER = new PersistenceManager(config, "JEE6-Persistence");
		log.info("Configuration properties:" + lines(config));
	}
	
	/**
	 * cleans up the test-session
	 */
	@AfterClass
	public static final void closePersistence() {
		if (null != PERSISTENCE_MANAGER) {
			PERSISTENCE_MANAGER.shutdown();
		}
	}
}
