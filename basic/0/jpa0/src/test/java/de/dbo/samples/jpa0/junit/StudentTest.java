package de.dbo.samples.jpa0.junit;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import de.dbo.samples.jpa0.entities.Group;
import de.dbo.samples.jpa0.entities.Student;
import de.dbo.samples.jpa0.junit.impl.TransactionTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * Test uses XStrem-serialization of persistent objects
 * 
 * @author Dmitri Boulanger, Hombach
 *
 *         Programs are meant to be read by humans and only incidentally for
 *         computers to execute (D. Knuth)
 *
 */
public class StudentTest extends TransactionTest {
    private static final Logger log = LoggerFactory.getLogger(StudentTest.class);

    private static XStream xstream = new XStream();

    @Test
    public void test_010() {
	Group group = null;
	Student student = null;
	EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	try {
	    group = new Group();
	    group.setGroupName("Clever Guys 2");
	    student = new Student();
	    student.setGroup(group);
	    student.setFirstname("James");
	    student.setLastname("Bond");
	    student.setBirthdate(new Date());
	    log.info("ORIGIN -- " + student);
	    // Start the transaction
	    em.getTransaction().begin();
	    // Persist the object in the DB
	    em.persist(group);
	    em.persist(student);
	    // Commit and end the transaction
	    em.getTransaction().commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    PERSISTENCE_MANAGER.rollbackTransaction(e);
	} finally {
	    // Close the manager
	    PERSISTENCE_MANAGER.close();
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
	} catch (Exception e) {
	    throw new RuntimeException("Failure serializing emtity-instance student=[" + student + "]", e);
	}

	em = PERSISTENCE_MANAGER.getEntityManager();
	Student student2 = null;
	try {
	    student2 = (Student) xstream.fromXML(new FileInputStream(tmp));
	    log.info("XMLSER -- " + student2);
	} catch (FileNotFoundException e) {
	    throw new RuntimeException(e);
	}

	student2.setSudentId(null);
	student2.setFirstname(student2.getFirstname() + " " + student2.getFirstname());
	log.info("XMLSER -- " + student2);

	try {
	    // Get a new transaction
	    em = PERSISTENCE_MANAGER.getEntityManager();
	    em.getTransaction().begin();
	    // Persist the object in the DB
	    em.persist(student2);

	    // Commit and end the transaction
	    em.getTransaction().commit();

	    final Query q = em.createQuery("select t from Student t");
	    final List<?> studentList = q.getResultList();
	    assertTrue("Student-list is null",  null!=studentList);
	    assertTrue("Student-list is empty", !studentList.isEmpty());
	    for (final Object studentFromList : studentList) {
		log.info("LIST   -- " + studentFromList);
		assertSame("Unexpected entiy class: " + studentFromList.getClass() + " Expected: " + Student.class,
			studentFromList.getClass(), Student.class);
	    }
	} catch (Exception e) {
	    if (em.getTransaction() != null && em.getTransaction().isActive()) {
		em.getTransaction().rollback();
	    }
	    throw new RuntimeException(e);
	} finally {
	    // Close the manager
	    PERSISTENCE_MANAGER.close();

	}
    }

    /**
     * Bad entities: student without last-name Data: group and two students
     * 
     * @throws Exception
     */
    @Test
    public void test_020() throws Exception {
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
	//	student2.setLastname("Sky Fall");
	student2.setBirthdate(new Date());
	log.info("Bad Student: " + student2);

	// Start bad-transaction (student2 is bad-defined)
	try {
	    final EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	    em.getTransaction().begin();
	    log.info("Transaction is active: " + em.getTransaction().isActive());
	    em.persist(group);
	    em.persist(student);
	    em.persist(student2);
	    em.getTransaction().commit();
	} catch (Exception e) {
	    PERSISTENCE_MANAGER.rollbackTransaction(e);
	} finally {
	    PERSISTENCE_MANAGER.close();
	}

	try {
	    final EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	    Query query = em.createQuery("SELECT c.firstname FROM Student AS c");
	    @SuppressWarnings("unchecked")
	    List<String> results = (List<String>) query.getResultList();
	    log.info("cnt=" + results.size());
	} catch (Exception e) {
	    PERSISTENCE_MANAGER.rollbackTransaction(e);
	} finally {
	    PERSISTENCE_MANAGER.close();
	}

    }

    /**
     * Bad entities: student without group
     * 
     * @throws Exception
     */
    @Test
    public void test_021() throws Exception {
	Student student = null;
	EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	try {
	    student = new Student();
	    student.setFirstname("James");
	    student.setLastname("Bond");
	    student.setBirthdate(new Date());
	    log.info("Bad Student 2: " + student);
	    em.getTransaction().begin();
	    em.persist(student);
	    em.getTransaction().commit();
	} catch (Exception e) {
	    PERSISTENCE_MANAGER.rollbackTransaction(e);
	} finally {
	    PERSISTENCE_MANAGER.close();
	}
    }
}
