package de.dbo.samples.jpa0;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper of the JPA-Persistence 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public class PersistenceManager {
    private static final Logger log = LoggerFactory.getLogger(PersistenceManager.class);

    /** Factory to generate instances of the entity manager */
    private  EntityManagerFactory entityManagerFactory;

    /** Entity manager that persists and queries the database */
    private  EntityManager entityManager = null;

    private final Map<String, String> config;
    private final String persistenceUnit;

    /**
     * 
     * @param config properties to extent the given persistence unit
     * @param persistenceUnit name of the persistence unit
     */
    public PersistenceManager(final Map<String, String> config, final String persistenceUnit) {
	this.config = config;
	this.persistenceUnit = persistenceUnit;
	newEntityManagerFactory();
	log.trace("created");
    }

    private final void newEntityManagerFactory() {
	this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit, config);
	log.info("EntityManager Factory created");
    }

    /**
     * Disable all persistence-related objects
     */
    public final void close() {
	if ( null!=entityManager && entityManager.isOpen()) {
	    entityManager.close();
	}
	entityManager = null;
	log.info("EntityManager closed");
    }

    /**
     * Closes this persistence completely.
     * After this method is called, this instance is not usable anymore
     */
    public final void shutdown() {
	close();
	if ( null!=entityManagerFactory && entityManagerFactory.isOpen()) {
	    entityManagerFactory.close();
	}
	try {
	    this.finalize();
	} catch (Throwable e) {
	    log.error("Cannot finalize transaction runner: " + e.toString());
	}
	log.info("shutdown");
    }

    public final EntityManager getEntityManager() {
	if (null == entityManager) {
	    if (entityManagerFactory.isOpen()) {
		return entityManagerFactory.createEntityManager();
	    } else {
		newEntityManagerFactory();
		if (entityManagerFactory.isOpen()) {
		    return entityManagerFactory.createEntityManager();
		}
		throw new RuntimeException("EntityManager Factory is closed");
	    }
	}
	return entityManager;
    }

    public final void rollbackTransaction(final Throwable e) {
	if (entityManager != null && entityManager.isOpen()) {
	    entityManager.getTransaction().rollback();
	    final String msg = "Transaction rolled back";
	    if (null==e) {
		log.warn(msg);
	    } else {
		log.warn(msg + ":", e);
	    }
	} else {
	    final String msg = "Transaction was not rolled back (NULL or not active)";
	    log.error(msg+ ":",e);
	}
    }
}
