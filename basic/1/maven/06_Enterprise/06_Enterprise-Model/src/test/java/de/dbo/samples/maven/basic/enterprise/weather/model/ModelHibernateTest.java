package de.dbo.samples.maven.basic.enterprise.weather.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.fail;

/**
 * Testing the data-model as JPA with eclipse-link and HSQL as an implementation.
 * 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ModelHibernateTest {
	private static final Logger log = LoggerFactory.getLogger(ModelHibernateTest.class);
	 
	private static String PERSISTENCE_UNIT = "Enterprise-Model";
	private final ModelAssertions modelAssertions = new ModelAssertions();
	
	/**
	 * in-memory test. It runs always
	 */
	@Test
	public void inMemory_DERBY() {
		final EntityManagerFactory entityManagerFactory = entityManagerFactoryImMemoryDerby();
		if (null!=entityManagerFactory && entityManagerFactory.isOpen()) {
		log.info("EntityManage factory for InMemory Derby created and it is ready");
		} else {
			fail("Failure creating EntityManage factory for InMemory Derby");
		}
		modelAssertions.test(entityManagerFactory);
	}
	
	/**
	 * in-memory test. It runs always
	 */
	@Test
	public void inMemory_HSQL() {
		final EntityManagerFactory entityManagerFactory = entityManagerFactoryImMemoryHsql();
		if (null!=entityManagerFactory && entityManagerFactory.isOpen()) {
		log.info("EntityManage factory for InMemory HSQL created and it is ready");
		} else {
			fail("Failure creating EntityManage factory for InMemory HSQL");
		}
		modelAssertions.test(entityManagerFactory);
	}
	
	/**
	 * test with a MySql server.
	 * The test runs only once since because the locations use zip-attribute as PK
	 * but the test-data are always the same. To re-run test, drop all tables from the test-schema.
	 * The schema name is enterprise
	 */
//	@org.junit.Ignore
	@Test
	public void server_MySQL() {
		final EntityManagerFactory entityManagerFactory = entityManagerFactoryServerMySql();
		if (null!=entityManagerFactory && entityManagerFactory.isOpen()) {
		log.info("Entity-Manager factory for InMemory MySql created and it is ready");
		} else {
			fail("Failure creating Entity-Manager factory for InMemory MySql");
		}
		modelAssertions.test(entityManagerFactory);
	}
	
	//
	// EntityManager factories
	// 
	
	private static final EntityManagerFactory entityManagerFactoryImMemoryDerby() {
		final Map<String, String> config = new HashMap<String, String>();
		config.put("javax.persistence.jdbc.driver","org.apache.derby.jdbc.EmbeddedDriver");
		config.put("javax.persistence.jdbc.url","jdbc:derby:memory:enterpise;create=true");
		config.put("javax.persistence.jdbc.user", "");
		config.put("javax.persistence.jdbc.password", "");

		targetDatabase("org.hibernate.dialect.DerbyDialect",config);
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
	}
	
	private static final EntityManagerFactory entityManagerFactoryImMemoryHsql() {
		final Map<String, String> config = new HashMap<String, String>();
		config.put("javax.persistence.jdbc.driver","org.hsqldb.jdbcDriver");
		config.put("javax.persistence.jdbc.url","jdbc:hsqldb:mem:enterpise;create=true");
		config.put("javax.persistence.jdbc.user", "sa");
		config.put("javax.persistence.jdbc.password", "");
		
		targetDatabase("org.hibernate.dialect.HSQLDialect",config);
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
	}
	
	private static final EntityManagerFactory entityManagerFactoryServerMySql() {
		final Map<String, String> config = new HashMap<String, String>();
		config.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
		config.put("javax.persistence.jdbc.url","jdbc:mysql://localhost:3306/enterprise");
		config.put("javax.persistence.jdbc.user", "root");
		config.put("javax.persistence.jdbc.password", "root");

		targetDatabase("org.hibernate.dialect.MySQLDialect",config);
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
	}
	
	private static final void targetDatabase(final String key, final Map<String, String> config) {
//		config.put("provider", "org.hibernate.ejb.HibernatePersistence");
		config.put("hibernate.dialect",key);
		config.put("hbm2ddl.auto","create");
		config.put("hibernate.connection.pool_size","1");
		config.put("show_sql","true");
		config.put("hibernate.temp.use_jdbc_metadata_defaults","false");
	}
}
