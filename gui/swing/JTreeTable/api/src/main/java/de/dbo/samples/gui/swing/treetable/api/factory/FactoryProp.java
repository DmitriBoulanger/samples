package de.dbo.samples.gui.swing.treetable.api.factory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

final class FactoryProp extends FactoryAbstraction {
	
	private static final Map<String,Factory> FACTORIES = new HashMap<String,Factory>();
	
	public static final Factory instance(final String config) {
		final Factory exsistingInstance =  FACTORIES.get(config);
		if (null!=exsistingInstance) {
			return exsistingInstance;
		} 
		
		final Properties properties = loadPropertiesFromResource(config);
		final Factory newInstance = new FactoryProp();
		try {
			newInstance.setNodeClass(Class.forName(properties.getProperty("node")));
		} catch (Exception e) {
			throw new FactoryException("Can't set Node.class: ",e);
		}
		try {
			newInstance.setTreetableModelClass(Class.forName(properties.getProperty("treetableModel")));
		} catch (Exception e) {
			throw new FactoryException("Can't set TreetableModel.class: ",e);
		}
		
		FACTORIES.put(config,newInstance);
		log.info("instance created for config=" + config);
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
