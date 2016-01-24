package de.dbo.samples.jpa0.junit;

import static org.junit.Assert.*;

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

    /**
     * Serialization and deserialization tests
     */
    @Test
    public void test_010() {
	logTestTitle("Student Test 010",log);
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
	    @SuppressWarnings("unchecked")
	    final List<Student> studentList = (List<Student>) q.getResultList();
	    assertTrue("Student-list is null",  null!=studentList);
	    assertTrue("Student-list is empty", !studentList.isEmpty());
	    assertTrue("Student-list should contain 2 students butt found " + studentList.size(), 2==studentList.size());
	    for (final Student studentFromList : studentList) {
		log.info("LIST   -- " + studentFromList);
		assertSame("Unexpected entiy class: " + studentFromList.getClass() + " Expected: " + Student.class,
			studentFromList.getClass(), Student.class);
	    }
	    assertTrue("ID of the student in the database is not as expected"
		    ,studentList.get(0).getSudentId().longValue()==student.getSudentId().longValue());
	    assertTrue("ID of the student 2 in the database is not as expected",
		    studentList.get(1).getSudentId().longValue()==student2.getSudentId().longValue());
	} finally {
	    // Close the manager
	    PERSISTENCE_MANAGER.close();
	}
    }

    /**
     * Bad entities: a student without last-name
     */
    @Test
    public void test_020() {
	logTestTitle("Student Test 020 (bad transaction)", log);
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

	try {
	    // Start bad-transaction (student2 is bad-defined)
	    try {
	        final EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	        em.getTransaction().begin();
	        log.info("Transaction is active: " + em.getTransaction().isActive());
	        em.persist(group);
	        em.persist(student);
	        em.persist(student2);
	        em.getTransaction().commit();
	    } finally {
	        PERSISTENCE_MANAGER.shutdown();
	    }
	    fail("No exception in bad transaction!");
	} catch (javax.persistence.RollbackException e) {
	    log.warn("Bad transaction: " + cause(e).getMessage());
	}

	try {
	    final EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	    final int resultCnt = findSudents(em);
	    assertTrue("Should be no students in the database but found " + resultCnt, resultCnt==0);
	} finally {
	    PERSISTENCE_MANAGER.close();
	}
    }

    /**
     * Bad entities: a student without group
     */
    @Test
    public void test_021()  {
	logTestTitle("Student Test 021 (bad transaction 2)",log);
	Student student = null;
	EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	try {
	    try {
		student = new Student();
		student.setFirstname("James");
		student.setLastname("Bond");
		student.setBirthdate(new Date());
		log.info("Bad Student 2: " + student);
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
	    } finally {
		PERSISTENCE_MANAGER.close();
	    }
	    fail("No exception in bad transaction!");
	} catch (javax.persistence.RollbackException e) {
	    log.warn("Bad transaction: " + cause(e).getMessage());
	}

	try {
	    final int resultCnt = findSudents(em);
	    assertTrue("Should be no students in the database but found " + resultCnt, resultCnt==0);
	} finally {
	    PERSISTENCE_MANAGER.close();
	}
    }
    
    @Test
    public void test_022()  {
	logTestTitle("Student Test 022 (bad transaction 3)",log);
	Student student = null;
	Group group = new Group();
	group.setGroupId(new Long(222222222));
	group.setGroupName("Non existing group");
	EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	try {
	    try {
		student = new Student();
		student.setFirstname("James");
		student.setLastname("Bond");
		student.setBirthdate(new Date());
		student.setGroup(group);
		log.info("Bad Student 2: " + student);
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
	    } finally {
		PERSISTENCE_MANAGER.close();
	    }
	    fail("No exception in bad transaction!");
	} catch (javax.persistence.RollbackException e) {
	    log.warn("Bad transaction: " + cause(e).getMessage());
	}

	try {
	    final int resultCnt = findSudents(em);
	    assertTrue("Should be no students in the database but found " + resultCnt, resultCnt==0);
	} finally {
	    PERSISTENCE_MANAGER.close();
	}
    }
    
    // HELPERS
    
    private static final Throwable cause(final Throwable e) {
	if (null==e.getCause()) {
	    return e;
	} else {
	    return cause(e.getCause());
	}
	
    }
    
    private static final int findSudents(final EntityManager em) {
	    final Query query = em.createQuery("select t from Student t");
	    @SuppressWarnings("unchecked")
	    final List<Student> results = (List<Student>) query.getResultList();
	    final int resultCnt = results.size();
	    log.info("Student-names in the database: cnt = " + resultCnt);
	    return resultCnt;
    }
}
