package de.dbo.samples.jpa.jpa0.junit;

import static de.dbo.samples.jpa.jpa0.config.PersistenceConfigurationFactory.persistence;
import static de.dbo.samples.util0.Print.lines;

import de.dbo.samples.jpa.jpa0.TransactionRunner;
import de.dbo.samples.jpa.jpa0.config.PersistenceConfigurations;
import de.dbo.samples.jpa.jpa0.entities.Student;
import de.dbo.samples.jpa.jpa0.entities.Group;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import static org.junit.Assert.assertSame;

public class StudentTest {
	protected static final Logger log = LoggerFactory
			.getLogger(StudentTest.class);

	/** Wrapper of the entity manager that persists and queries the database */
	private static TransactionRunner TRANSACTION_RUNNER;

	private static XStream xstream = new XStream();

	@BeforeClass
	public static void init() throws Exception {
		final Map<String, String> config = persistence(PersistenceConfigurations.PRODUCTION);
		TRANSACTION_RUNNER = new TransactionRunner(config, "JEE6-Persistence");
		log.debug("Configuration properties:" + lines(config));
	}
	
	/**
	 * cleans up the test-session.
	 */
	@AfterClass
	public static void close() {
		if (null != TRANSACTION_RUNNER) {
			TRANSACTION_RUNNER.shutdown();
		}
	}


	@Test
	public void test_010() {
		Group group = null;
		Student student = null;
		EntityManager em = TRANSACTION_RUNNER.getEntityManager();
		EntityTransaction trx = null;
		try {
			// Get a new transaction
			trx = TRANSACTION_RUNNER.getTransaction();
			group = new Group();
			group.setGroupName("CleverGuys 2");
			student = new Student();
			student.setGroup(group);
			student.setFirstname("James");
			student.setLastname("Bond");
			student.setBirthdate(new Date());
			log.info("ORIGIN -- " + student);
			// Start the transaction
			trx.begin();
			// Persist the object in the DB
			em.persist(group);
			em.persist(student);
			// Commit and end the transaction
			trx.commit();
		} catch (Exception e) {
			TRANSACTION_RUNNER.rollbackTransaction(e);
		} finally {
			// Close the manager
			TRANSACTION_RUNNER.close();
		}

		File tmp = null;
		try {
			tmp = File.createTempFile("student", "_xser");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			xstream.toXML(student, new FileOutputStream(tmp));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		em = TRANSACTION_RUNNER.getEntityManager();
		Student student2 = null;
		try {
			student2 = (Student) xstream.fromXML(new FileInputStream(tmp));
			log.info("XMLSER -- " + student2);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		student2.setSudentId(null);
		student2.setFirstname(student2.getFirstname() + " "+ student2.getFirstname());
		log.info("XMLSER -- " + student2);

		try {
			// Get a new transaction
			em = TRANSACTION_RUNNER.getEntityManager();
			trx = TRANSACTION_RUNNER.getTransaction();
			trx.begin();
			// Persist the object in the DB
			em.persist(student2);
			// Commit and end the transaction
			trx.commit();

			final Query q = em.createQuery("select t from Student t");
			final List<?> studentList = q.getResultList();
			for (final Object studentFromList : studentList) {
				log.info("LIST   -- " + studentFromList);
				assertSame(
						"Unexpected entiy class: " + studentFromList.getClass()
								+ " Expected: " + Student.class,
						studentFromList.getClass(), Student.class);
			}

		} catch (Exception e) {
			if (trx != null && trx.isActive()) {
				trx.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			// Close the manager
			TRANSACTION_RUNNER.close();

		}
	}
	
	/**
	 * Bad entities: student without last-name
	 * 
	 * @throws Exception
	 */
	@Test(expected = RollbackException.class)
	public void test_020() throws Exception {
		// Data: group and two students
		final Group group = new Group();
		group.setGroupName("Bad Guys " + UUID.randomUUID().toString());
		 
		final Student student = new Student();
		student.setGroup(group);
		student.setFirstname("James");
		student.setLastname("Bond");
		student.setBirthdate(new Date());
		log.info("Student: " + student);

		final Student student2 = new Student();
		student2.setGroup(group);
		student2.setFirstname("James");
		student2.setBirthdate(new Date());
		log.info("Bad Student: " + student2);

		// Start the transaction (student2 is bad-defined)
		try {
			final EntityManager em = TRANSACTION_RUNNER.getEntityManager();
			final EntityTransaction trx = TRANSACTION_RUNNER.getTransaction();
			trx.begin();
			em.persist(group);
			em.persist(student);
			em.persist(student2);
			trx.commit();
		} catch (Exception e) {
			TRANSACTION_RUNNER.rollbackTransaction(e);
			throw e;
		} finally {
			TRANSACTION_RUNNER.close();
		}
	}

	/**
	 * Bad entities: student without group
	 * 
	 * @throws Exception
	 */
	@Test(expected = RollbackException.class)
	public void test_021() throws Exception {
		Student student = null;
		EntityManager em = TRANSACTION_RUNNER.getEntityManager();
		EntityTransaction trx = null;
		try {
			trx = TRANSACTION_RUNNER.getTransaction();
			student = new Student();
			student.setFirstname("James");
			student.setLastname("Bond");
			student.setBirthdate(new Date());
			log.info("Bad Student 2: " + student);
			trx.begin();
			em.persist(student);
			trx.commit();
		} catch (Exception e) {
			TRANSACTION_RUNNER.rollbackTransaction(e);
			throw e;
		} finally {
			TRANSACTION_RUNNER.close();
		}
	}



}
