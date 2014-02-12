package de.dbo.samples.jpa0.junit;

import de.dbo.samples.jpa0.junit.impl.TransactionTestNoConnection;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseTestNoConnection extends TransactionTestNoConnection {
	protected static final Logger log = LoggerFactory.getLogger(CloseTestNoConnection.class);

	@Test(expected = PersistenceException.class)
	public void test_010() {
		TRANSACTION_RUNNER.getEntityManager();
	}
}