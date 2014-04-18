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

public class FactoryMgrFirstInitialization {
	private static final Logger log = LoggerFactory.getLogger(FactoryMgrFirstInitialization.class);
	
	/*
	 * very fast compared with the factory that uses spring-context
	 */
	@Test
	public void testProperties() {
		FactoryMgr.clear();
		final long start = System.currentTimeMillis();
	    final Factory factory = FactoryMgr.instance("ReferenceImplementation.properties");
	    factory.getNodeClass();
	    factory.getTreetableModelClass();
	    log.info("Elapsed " + (System.currentTimeMillis()-start) 
	    		+ " ms. creating factory from properties");
	   
	}
	
	/*
	 * very slow compared with the factory that uses properties
	 */
	@Test
	public void testCtx() {
		FactoryMgr.clear();
		final long start = System.currentTimeMillis();
		final Factory factory = FactoryMgr.instance("ReferenceImplementation.xml");
		factory.getNodeClass();
	    factory.getTreetableModelClass();
		log.info("Elapsed " + (System.currentTimeMillis()-start) 
				+ " ms. creating factory from context");
		 
	}
	


}
