package de.dbo.samples.gui.swing.treetable.api.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Manager for creating tree-table factories
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class FactoryMgr {
	protected static final Logger log = LoggerFactory.getLogger(Factory.class);
	
	private static final Map<String,Factory> FACTORIES = new HashMap<String,Factory>();
	
	static final void clear() {
		FACTORIES.clear();
	}

	public static final Factory instance(final String config) {
		final Factory exsistingInstance = FACTORIES.get(config);
		if (null != exsistingInstance) {
			return exsistingInstance;
		}

		final ApplicationContext ctx;
		try {
			ctx = new ClassPathXmlApplicationContext(config);
		} catch (BeansException e) {
			throw new FactoryException("Can't create context." + " Config="+ config, e);
		}
		
		final Factory newInstance = (Factory) ctx.getBean("factory");
		if (null==newInstance) {
			throw new FactoryException("New factory instance is NULL. Config="+ config);
		}
		
		FACTORIES.put(config, newInstance);
		log.trace("created from " + config);
		return newInstance;
	}

}
