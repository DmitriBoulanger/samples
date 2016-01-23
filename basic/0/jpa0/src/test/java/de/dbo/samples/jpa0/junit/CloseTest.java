package de.dbo.samples.jpa0.junit;

import de.dbo.samples.jpa0.junit.impl.TransactionTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseTest extends TransactionTest {
    protected static final Logger log = LoggerFactory.getLogger(CloseTest.class);

    @Test
    public void test_010() {
	PERSISTENCE_MANAGER.close();
	PERSISTENCE_MANAGER.getEntityManager();
	PERSISTENCE_MANAGER.close();
    }

    @Test
    public void test_011() {
	try {
	    PERSISTENCE_MANAGER.close();
	    PERSISTENCE_MANAGER.getEntityManager();
	    PERSISTENCE_MANAGER.shutdown();
	    PERSISTENCE_MANAGER.getEntityManager();
	} catch (RuntimeException e) {
	    log.info(e.toString());
	    throw e;
	}
    }
}
