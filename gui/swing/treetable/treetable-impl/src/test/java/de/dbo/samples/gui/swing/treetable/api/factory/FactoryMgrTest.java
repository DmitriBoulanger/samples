package de.dbo.samples.gui.swing.treetable.api.factory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class FactoryMgrTest {
	private static final Logger log = LoggerFactory.getLogger(FactoryMgrTest.class);
	
	@Test
	public void testCtx() {
		FactoryMgr.clear();
		final long start = System.currentTimeMillis();
		final Factory factory = FactoryMgr
				.instance("ReferenceImplementation.xml");
		final Class<?> nodeClass = factory.getNodeClass();
		final Class<?> modelClass = factory.getTreetableModelClass();
		log.info("Elapsed " + (System.currentTimeMillis() - start)
				+ " ms. creating factory from context");
		assertClassNames(nodeClass, modelClass);

		final Factory factory2 = FactoryMgr.instance("ReferenceImplementation.xml");
		assertTrue("Factory from context is not a singelton",
				factory == factory2);
		
		FactoryMgr.clear();
		final Factory factory3 = FactoryMgr.instance("ReferenceImplementation.xml");
	    assertFalse("No new factory from properties after clear"
	    		,factory==factory3);
	}
	

	private static final void assertClassNames(final Class<?> nodeClass,final Class<?> modelClass) {
		assertTrue("Incorrect Node class-name "+nodeClass.getSimpleName()
				,nodeClass.getSimpleName().equals("NodeImpl"));
		assertTrue("Incorrect Model class-name "+ modelClass.getSimpleName()
				,modelClass.getSimpleName().equals("TreetableModelImpl"));
	}
	
	@Test(expected=FactoryException.class)
	public void testErr() {
		FactoryMgr.clear();
	    FactoryMgr.instance("bla.bla");
	}
	
	@Test(expected=FactoryException.class)
	public void testErrProperties() {
		FactoryMgr.clear();
	    FactoryMgr.instance("x.properties");
	}
	
	@Test(expected=FactoryException.class)
	public void testErrCtx() {
		FactoryMgr.clear();
	    FactoryMgr.instance("x.xml");
	}
}
