package de.dbo.samples.gui.swing.treetable.api.factory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
		final Factory exsistingInstance =  FACTORIES.get(config);
		if (null!=exsistingInstance) {
			return exsistingInstance;
		} 
	
		final Factory newInstance;
		if (config.endsWith(".xml")) {
			
			final ApplicationContext ctx;
			try {
				ctx = new ClassPathXmlApplicationContext(config);
			} catch (BeansException e) {
				throw new FactoryException("Can't create context."
						+ " Config= " + config,e);
			}
			newInstance = (Factory) ctx.getBean("factory");
			
		} else if (config.endsWith(".properties"))  {
			
			newInstance = new FactoryImpl();
			final Properties properties = loadPropertiesFromResource(config);
			final String nodeClassname = properties.getProperty("node");
			try {
				newInstance.setNodeClass(Class.forName(nodeClassname));
			} catch (Exception e) {
				throw new FactoryException("Can't set Node.class: " + nodeClassname,e);
			}
			final String modelClassname = properties.getProperty("treetableModel");
			try {
				newInstance.setTreetableModelClass(Class.forName(modelClassname));
			} catch (Exception e) {
				throw new FactoryException("Can't set TreetableModel.class: " + modelClassname ,e);
			}
			
		} else {
			throw new FactoryException("Can't recognize type for resource: " + config);
		}
		
		FACTORIES.put(config,newInstance);
		log.debug("instance created from " + config);
		return newInstance;
	}
	 

	private static final Properties loadPropertiesFromResource(final String name) {
		final ClassLoader classloader = ClassLoader.getSystemClassLoader();
		if (null == classloader) {
			throw new FactoryException("Can't get system classloader");
		}

		final Properties properrties = new Properties();

		URL url = classloader.getResource(name);
		if (null == url) {
			url = classloader.getResource("/" + name);
		}
		if (null != url) {
			try {
				final InputStream in = url.openStream();
				properrties.load(in);
				return properrties;
			} catch (IOException e) {
				throw new FactoryException(
						"Can't load contents from resouce " + name + ": ",e);
			}
		} else {
			throw new FactoryException(
					"Resouce " + name + " doesn't exist anywhre in classpath?");
		}
	}
}
