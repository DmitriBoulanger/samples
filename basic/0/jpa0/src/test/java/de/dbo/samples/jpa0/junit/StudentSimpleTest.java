package de.dbo.samples.jpa0.junit;

import static org.junit.Assert.assertTrue;

import de.dbo.samples.jpa0.entities.Group;
import de.dbo.samples.jpa0.entities.Student;
import de.dbo.samples.jpa0.junit.impl.TransactionTest;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Test uses XStrem-serialization of persistent objects
 * 
 * @author Dmitri Boulanger, Hombach
 *
 *         Programs are meant to be read by humans and only incidentally for
 *         computers to execute (D. Knuth)
 *
 */
public class StudentSimpleTest extends TransactionTest {
    private static final Logger log = LoggerFactory.getLogger(StudentSimpleTest.class);

    @Test
    public void test() {
	logTestTitle("StudentSimpleTest", log);
	Group group = null;
	Student student = null;
	EntityManager em = PERSISTENCE_MANAGER.getEntityManager();
	assertTrue("EntityManager is not open", em.isOpen());
	log.info("EntityManager opened");
	try {
	    // Start the transaction
	    em.getTransaction().begin();
	    assertTrue("Transaction is not active", em.getTransaction().isActive());
	    log.info("Transaction is active");

	    group = new Group();
	    group.setGroupName("Clever Guys 2");
	    log.info("ORIGIN -- " + group);
	    student = new Student();
	    student.setGroup(group);
	    student.setFirstname("James");
	    student.setLastname("Bond");
	    student.setBirthdate(new Date());
	    log.info("ORIGIN -- " + student);

	    // Persist the object in the DB
	    em.persist(group);
	    em.persist(student);

	    assertTrue("EntityManager doesn't have group", em.contains(group));
	    assertTrue("EntityManager doesn't have student" , em.contains(student));

	    // Commit and end the transaction
	    em.getTransaction().commit();

	    log.info("COMMIT -- " + group);
	    log.info("COMMIT -- " + student);

	    final Query query = em.createQuery("SELECT record from Group record");
	    final List<?> groups = query.getResultList();
	    assertTrue("Group-list is null",  null!=groups);
	    assertTrue("Group-list is empty", !groups.isEmpty());

	    final Query query2 = em.createQuery("SELECT record from Student record");
	    final List<?> students = query2.getResultList();
	    assertTrue("Student-list is null",  null!=students);
	    assertTrue("Student-list is empty", !students.isEmpty());

	} catch (Exception e) {
	    log.error("Transcation failure: ",e);
	}  finally {
	    PERSISTENCE_MANAGER.shutdown();
	}
    }  
}
