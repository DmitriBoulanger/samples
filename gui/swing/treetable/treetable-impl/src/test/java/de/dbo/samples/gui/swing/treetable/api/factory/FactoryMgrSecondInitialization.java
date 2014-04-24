package de.dbo.samples.gui.swing.treetable.api.factory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Profiling initialization of the factory: second initialization
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class FactoryMgrSecondInitialization {
	private static final Logger log = LoggerFactory.getLogger(FactoryMgrSecondInitialization.class);
	
	
	/*
	 * takes more time compared with the factory that uses properties
	 */
    @Test
	public void testCtxSecondInitialization() {
		FactoryManager.clear();
		FactoryManager.instance("ReferenceImplementation.xml");
		FactoryManager.clear();
		final long start = System.currentTimeMillis();
		final Factory factory = FactoryManager.instance("ReferenceImplementation.xml");
		factory.getNodeClass();
	    factory.getTreetableModelClass();
		log.info("Elapsed " + (System.currentTimeMillis()-start) 
				+ " ms. creating factory from context (second initialization)");
	}
}
