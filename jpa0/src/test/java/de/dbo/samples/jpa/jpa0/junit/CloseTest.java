package de.dbo.samples.jpa.jpa0.junit;

import de.dbo.samples.jpa.jpa0.junit.impl.TransactionTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseTest extends TransactionTest {
	protected static final Logger log = LoggerFactory.getLogger(CloseTest.class);

	@Test
	public void test_010() {
		TRANSACTION_RUNNER.close();
		TRANSACTION_RUNNER.getEntityManager();
		TRANSACTION_RUNNER.close();
	}
	
	@Test(expected=Exception.class)
	public void test_011() {
		try {
			TRANSACTION_RUNNER.close();
			TRANSACTION_RUNNER.getEntityManager();
			TRANSACTION_RUNNER.shutdown();
			TRANSACTION_RUNNER.getEntityManager();
		} catch (RuntimeException e) {
			log.info(e.toString());
			throw e;
		}
	}
}
