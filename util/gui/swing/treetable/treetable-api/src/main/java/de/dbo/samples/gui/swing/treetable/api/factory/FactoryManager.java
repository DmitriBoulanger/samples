package de.dbo.samples.gui.swing.treetable.api.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Manager for creating tree-table factories.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class FactoryManager {
	protected static final Logger log = LoggerFactory.getLogger(Factory.class);
	
	private static final Map<String,Factory> FACTORIES = new HashMap<String,Factory>();
	
	static final void clear() {
		FACTORIES.clear();
	}

	public static final Factory getFactory(final String config) {
		final long start = System.currentTimeMillis();
		final Factory exsistingInstance = FACTORIES.get(config);
		if (null != exsistingInstance) {
			log.trace("instance exists. Config="+ config);
			return exsistingInstance;
		}

		final ApplicationContext ctx;
		try {
			ctx = new ClassPathXmlApplicationContext(config);
		} catch (BeansException e) {
			throw new FactoryException("Can't create context." + " Config="+ config, e);
		}
		
		final FactoryImpl newInstance = (FactoryImpl) ctx.getBean("factory");
		if (null==newInstance) {
			throw new FactoryException("New factory instance is NULL. Config="+ config);
		}
		newInstance.setApplicationContext(ctx);
		
		FACTORIES.put(config, newInstance);
		log.trace("elapsed "+(System.currentTimeMillis()-start)+" ms. creating from " + config);
		
		return newInstance;
	}

}
