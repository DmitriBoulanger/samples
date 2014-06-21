package de.dbo.samples.maven.basic.enterprise.weather.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ModelTest {
	private static final Logger log = LoggerFactory.getLogger(ModelTest.class);
	 
	private static String PERSISTENCE_UNIT = "Enterprise-Model";
	
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
		test(entityManagerFactory);
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
		test(entityManagerFactory);
	}
	
	/**
	 * test with a MySql server.
	 * The test runs only once since because the locations use zip-attribute as PK
	 * but the test-data are always the same. To re-run test, drop all tables from the test-schema.
	 * The schema name is enterprise
	 */
	@org.junit.Ignore
	@Test
	public void server_MySQL() {
		final EntityManagerFactory entityManagerFactory = entityManagerFactoryServerMySql();
		if (null!=entityManagerFactory && entityManagerFactory.isOpen()) {
		log.info("EntityManage factory for InMemory MySql created and it is ready");
		} else {
			fail("Failure creating EntityManage factory for InMemory MySql");
		}
		test(entityManagerFactory);
	}
	
	private final void test(final EntityManagerFactory entityManagerFactory) {
		final EntityManager em = entityManagerFactory.createEntityManager();
		assertDataTransaction(em);
		assertQueries(em);
		em.clear();
		em.close();
	}
	
	private final void assertDataTransaction(final EntityManager em) {
		final Location location = new Location();
		final Weather weather = new Weather();
		final Condition condition = new Condition();
		final Atmosphere atmosphere = new Atmosphere();
		final Wind wind = new Wind();
		location.setZip("53577");
		location.setRegion("Rheinland-Pfalz");
		location.setCity("Neustadt(Wied)");
		location.setCountry("Germany");
		
		condition.setText("Fair");
		condition.setCode("code");
		condition.setDate(new Date().toString());
		condition.setTemp("39");
		condition.setWeather(weather);
		
		wind.setChill("39");
		wind.setWeather(weather);
		
		atmosphere.setWeather(weather);
		atmosphere.setHumidity("67");
	
		weather.setLocation(location);
		weather.setDate(new Date());
		weather.setCondition(condition);
		weather.setAtmosphere(atmosphere);
		weather.setWind(wind);
		
		final EntityTransaction trx;
		try {
			// Get and then start new transaction
			trx = em.getTransaction();
			trx.begin();
			// Persist objects in the DB
			em.persist(location);
			em.persist(atmosphere);
			em.persist(wind);
			em.persist(condition);
			em.persist(weather);
			
			// Commit the transaction
			trx.commit();
			log.info("Transaction commited");
		} catch (Exception e) {
			log.error("test failure:",e);
			fail("Data-insertion failure: " + e.toString());
		} finally {
			log.info("Data:"
					+ "\n\t " + location
					+ "\n\t " + condition
					+ "\n\t " + atmosphere
					+ "\n\t " + wind
					+ "\n\t " + weather
					);
			
		}
	}
	
	private final void assertQueries(final EntityManager em) {
		final String zip = "53577";
		final Query locationQuery = em.createNamedQuery("Location.uniqueByZip");
		locationQuery.setParameter("zip", zip);
		final Location location = (Location) locationQuery.getSingleResult();
		assertNotNull("Location is null",location);
		assertNotNull("Location.zip is null",location.getZip());
		assertNotNull("Location.city is null",location.getCity());
		assertNotNull("Location.region is null",location.getRegion());
		assertNotNull("Location.country is null",location.getCountry());
		assertTrue("Expected zip="+zip+" but found " + location.getZip(), zip.equals(location.getZip()));
		log.info("Location is found and it is correct");
		
		final Query weatherQuery = em.createNamedQuery("Weather.byLocation");
		weatherQuery.setParameter("location", location);
		final Weather weather = (Weather) weatherQuery.getSingleResult();
		assertNotNull("Weater is null", weather);
		assertNotNull("Location is null in weather", weather.getLocation());
		assertNotNull("Atmosphere is null in weather", weather.getAtmosphere());
		assertNotNull("Condition is null in weather", weather.getCondition());
		assertNotNull("Wind is null in weather", weather.getWind());
		log.info("Weater is found and it is correct");
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

		targetDatabase("DERBY",config);
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
	}
	
	private static final EntityManagerFactory entityManagerFactoryImMemoryHsql() {
		final Map<String, String> config = new HashMap<String, String>();
		config.put("javax.persistence.jdbc.driver","org.hsqldb.jdbcDriver");
		config.put("javax.persistence.jdbc.url","jdbc:hsqldb:mem:enterpise;create=true");
		config.put("javax.persistence.jdbc.user", "sa");
		config.put("javax.persistence.jdbc.password", "");
		
		targetDatabase("HSQL",config);
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
	}
	
	private static final EntityManagerFactory entityManagerFactoryServerMySql() {
		final Map<String, String> config = new HashMap<String, String>();
		config.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
		config.put("javax.persistence.jdbc.url","jdbc:mysql://localhost:3306/enterprise");
		config.put("javax.persistence.jdbc.user", "root");
		config.put("javax.persistence.jdbc.password", "root");

		targetDatabase("MYSQL",config);
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
	}
	
	private static final void targetDatabase(final String key, final Map<String, String> config) {
		config.put("eclipselink.target-database", key);
		config.put("eclipselink.ddl-generation", "create-or-extend-tables");
		config.put("eclipselink.logging.level", "WARNING");
//		config.put("eclipselink.ddl-generation.output-mode", "both");
//		config.put("eclipselink.logging.file", "target/logs/eclipselink_"+key+".log");
//		config.put("eclipselink.create-ddl-jdbc-file-name", "target/logs/createDDL_+key+.sql");
	}
}
