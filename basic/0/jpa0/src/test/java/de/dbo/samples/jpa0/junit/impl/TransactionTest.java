package de.dbo.samples.jpa0.junit.impl;

import static de.dbo.samples.jpa0.config.PersistenceConfigurationFactory.persistence;
import static de.dbo.tools.utils.print.Print.lines;

import de.dbo.samples.jpa0.PersistenceManager;
import de.dbo.samples.jpa0.config.PersistenceConfigurations;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public abstract class TransactionTest extends TransactionTestAbstraction {
    protected static final Logger log = LoggerFactory.getLogger(TransactionTest.class);

    /* Wrapper of the entity manager that persists and queries the database */
    protected static PersistenceManager PERSISTENCE_MANAGER;

    @BeforeClass
    public static final void initPersistenceConfiguration() throws Exception {
	logTestTitle("Test Initialization (before class)", log);
	final Map<String, String> config = persistence(PersistenceConfigurations.PERSISTENT);
	PERSISTENCE_MANAGER = new PersistenceManager(config, "JEE6-Persistence");
	log.debug("Configuration properties:" + lines(config));
    }

    /**
     * cleans up the test-session
     */
    @AfterClass
    public static final void closePersistence() {
	logTestTitle("Test Clean-up (after class)", log);
	if (null != PERSISTENCE_MANAGER) {
	    PERSISTENCE_MANAGER.shutdown();
	}
	logTestEnd(log);
    }
    
    @Before
    public void shutdownPersistence() {
	 logTestTitle("Test Clean-up (before test)", log);
	 PERSISTENCE_MANAGER.shutdown();
    }
}
