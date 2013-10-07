package de.dbo.samples.jpa.jpa0.junit;

import static org.junit.Assert.*;
import de.dbo.util0.Print;
import de.dbo.samples.jpa.jpa0.config.DerbyProperties;
import de.dbo.samples.jpa.jpa0.config.PersistenceConfigurationFactory;
import de.dbo.samples.jpa.jpa0.config.PersistenceConfigurations;
import de.dbo.samples.jpa.jpa0.entities.StudentGroup;
import de.dbo.samples.jpa.jpa0.entities.Student;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StudentTest {
	protected static final Logger log = LoggerFactory.getLogger(StudentTest.class);
	
	/** The factory that produces entity managers */
	private static EntityManagerFactory EMF;
	
	/** The entity manager that persists and queries the DB. */
	private static EntityManager EM;
	
	private static XStream xstream = new XStream();

	@BeforeClass
	public static void initTestFixture() throws Exception {
		
		// configure Derby database 
		System.getProperties().putAll(new DerbyProperties());
		
		// create entity-manager for Derby (test-configuration)
		final Map<String,String> config 
			=  PersistenceConfigurationFactory.persistence(PersistenceConfigurations.TEST);
		log.debug("Configuration properties:" +  Print.lines(config));
		
		EMF = Persistence.createEntityManagerFactory("JEE6-Persistence", config);
		EM = EMF.createEntityManager();
	}
	
	@Test
	public void test() {
		StudentGroup group = null;
		Student student = null;
		EntityTransaction trx = null;
		try {
		   //Get a new transaction
		   trx = EM.getTransaction();
		   group = new StudentGroup();
		   group.setGroupName("CleverGuys");
           student = new Student();
           student.setGroup(group);
           student.setFirstname("John");
           student.setBirthdate(new Date());
           log.info("ORIGIN -- " + student);
		   //Start the transaction
		   trx.begin();
		   //Persist the object in the DB
		   EM.persist(group);
		   EM.persist(student);
		   //Commit and end the transaction
		   trx.commit();
		} catch (Exception e) {
		   if (trx != null && trx.isActive()) {
		      trx.rollback();
		   }
		   throw new RuntimeException("Transaction rolled back", e);
		} finally {
//		   // Close the manager and its factory
//		   EM.close();
//		   EMF.close();
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
		Student student2 = null;
		try {
			student2 = (Student) xstream.fromXML( new FileInputStream(tmp) );
			log.info("XMLSER -- " + student2);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		student2.setSudentId(null);
		student2.setFirstname( student2.getFirstname() 
					+ " " + student2.getFirstname());
		log.info("XMLSER -- " + student2);
		
		try {
			   //Get a new transaction
			   trx = EM.getTransaction();
			   trx.begin();
			   //Persist the object in the DB
			   EM.persist(student2);
			   //Commit and end the transaction
			   trx.commit();
			   
			   final Query q = EM.createQuery("select t from Student t");
			   final List<?> studentList = q.getResultList();
			   for (final Object studentFromList: studentList) {
				   log.info("LIST   -- " +studentFromList);
				   assertSame("Unexpected entiy class: " + studentFromList.getClass()
						   + " Expected: " +  Student.class
						   , studentFromList.getClass(), Student.class);
			   }
			   
			} catch (Exception e) {
			   if (trx != null && trx.isActive()) {
			      trx.rollback();
			   }
			   throw new RuntimeException(e);
			} finally {
			   //Close the manager and its factory
			   EM.close();
			   EMF.close();
			}
	}

	 /**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		if ( null!=EM && EM.isOpen()) {
			EM.close();
		}
		if ( null!=EMF && EMF.isOpen()) {
			EMF.close();
		}
	}


}
