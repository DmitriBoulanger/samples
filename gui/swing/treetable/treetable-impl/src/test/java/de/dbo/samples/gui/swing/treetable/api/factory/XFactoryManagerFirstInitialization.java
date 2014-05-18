package de.dbo.samples.gui.swing.treetable.api.factory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Profiling initialization of the factory: first initialization
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

public class XFactoryManagerFirstInitialization {
	private static final Logger log = LoggerFactory.getLogger(XFactoryManagerFirstInitialization.class);
	
	
	/*
	 * very slow compared with the factory that uses properties
	 */
	@Test
	public void testCtx() {
		FactoryManager.clear();
		final long start = System.currentTimeMillis();
		final Factory factory = FactoryManager.getFactory("XReferenceImplementation.xml");
		factory.getNodeClass();
	    factory.getTreetableModelClass();
		log.info("elapsed " + (System.currentTimeMillis()-start) + " ms. creating factory");
	}
	


}
