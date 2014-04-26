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
public class XFactoryManagerTest {
	private static final Logger log = LoggerFactory.getLogger(XFactoryManagerTest.class);
	
	@Test
	public void testCtx() {
		XFactoryManager.clear();
		final long start = System.currentTimeMillis();
		final XFactory factory = XFactoryManager.getFactory("XReferenceImplementation.xml");
		final Class<?> nodeClass = factory.getNodeClass();
		final Class<?> modelClass = factory.getTreetableModelClass();
		log.info("elapsed " + (System.currentTimeMillis() - start)+ " ms. creating factory from context");
		assertClassNames(nodeClass, modelClass);

		final XFactory factory2 = XFactoryManager.getFactory("XReferenceImplementation.xml");
		assertTrue("Factory from context is not a singelton",
				factory == factory2);
		
		XFactoryManager.clear();
		final XFactory factory3 = XFactoryManager.getFactory("XReferenceImplementation.xml");
	    assertFalse("No new factory from properties after clear",factory==factory3);
	}
	

	private static final void assertClassNames(final Class<?> nodeClass,final Class<?> modelClass) {
		assertTrue("Incorrect Node class-name "+nodeClass.getSimpleName()
				,nodeClass.getSimpleName().equals("NodeImpl"));
		assertTrue("Incorrect Model class-name "+ modelClass.getSimpleName()
				,modelClass.getSimpleName().equals("XTreetableModelImpl"));
	}
	
	@Test(expected=FactoryException.class)
	public void testErr() {
		XFactoryManager.clear();
	    XFactoryManager.getFactory("bla.bla");
	}
	
	@Test
	public void treetableUI() {
		final XFactory factory = XFactoryManager.getFactory("XReferenceImplementation.xml");
		assertTrue("TreetableUI should be a singelton"
				,factory.getTreetableUI()==factory.getTreetableUI());
	}
	
	@Test
	public void treetableColumns() {
		final XFactory factory = XFactoryManager.getFactory("XReferenceImplementation.xml");
		assertFalse("TreetableColumns should not be a singelton"
				,factory.newTreetableColumns()==factory.newTreetableColumns());
	}
	
	@Test
	public void recordProvider() {
		final XFactory factory = XFactoryManager.getFactory("XReferenceImplementation.xml");
		assertFalse("TreetableColumns should not be a singelton"
				,factory.newRecordProvider()==factory.newRecordProvider());
	}
}
