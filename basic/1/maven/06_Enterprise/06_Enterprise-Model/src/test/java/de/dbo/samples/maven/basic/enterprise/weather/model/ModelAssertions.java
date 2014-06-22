package de.dbo.samples.maven.basic.enterprise.weather.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ModelAssertions {
	private static final Logger log = LoggerFactory.getLogger(ModelAssertions.class);
	
	
	public final void test(final EntityManagerFactory entityManagerFactory) {
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
		final List<Location> locations = (List<Location>) locationQuery.getResultList();
		final Location location = locations.get(0);
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
	

}
