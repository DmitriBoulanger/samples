package de.dbo.samples.jpa0.junit;

import de.dbo.samples.jpa0.junit.impl.TransactionTestNoConnection;

import javax.persistence.PersistenceException;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseTestNoConnection extends TransactionTestNoConnection {
	protected static final Logger log = LoggerFactory.getLogger(CloseTestNoConnection.class);

	@Ignore // use it if the server is not running!
	@Test(expected = PersistenceException.class)
	public void test_010() {
		PERSISTENCE_MANAGER.getEntityManager();
	}
}