package de.dbo.samples.gui.swing.treetable.api.factory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Functionality of the Factory Manager
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class FactoryMgrTest {
	private static final Logger log = LoggerFactory.getLogger(FactoryMgrTest.class);
	
	@Test
	public void testCtx() {
		FactoryManager.clear();
		final long start = System.currentTimeMillis();
		final Factory factory = FactoryManager.instance("ReferenceImplementation.xml");
		final Class<?> nodeClass = factory.getNodeClass();
		final Class<?> modelClass = factory.getTreetableModelClass();
		log.info("Elapsed " + (System.currentTimeMillis() - start)+ " ms. creating factory from context");
		assertClassNames(nodeClass, modelClass);

		final Factory factory2 = FactoryManager.instance("ReferenceImplementation.xml");
		assertTrue("Factory from context is not a singelton",
				factory == factory2);
		
		FactoryManager.clear();
		final Factory factory3 = FactoryManager.instance("ReferenceImplementation.xml");
	    assertFalse("No new factory from properties after clear",factory==factory3);
	}
	

	private static final void assertClassNames(final Class<?> nodeClass,final Class<?> modelClass) {
		assertTrue("Incorrect Node class-name "+nodeClass.getSimpleName()
				,nodeClass.getSimpleName().equals("NodeImpl"));
		assertTrue("Incorrect Model class-name "+ modelClass.getSimpleName()
				,modelClass.getSimpleName().equals("TreetableModelImpl"));
	}
	
	@Test(expected=FactoryException.class)
	public void testErr() {
		FactoryManager.clear();
	    FactoryManager.instance("bla.bla");
	}
	
	@Test(expected=FactoryException.class)
	public void testErrProperties() {
		FactoryManager.clear();
	    FactoryManager.instance("x.properties");
	}
	
	@Test(expected=FactoryException.class)
	public void testErrCtx() {
		FactoryManager.clear();
	    FactoryManager.instance("x.xml");
	}
}
