package de.dbo.samples.resource0;
 
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassPathProperties extends Properties {
	private static final long serialVersionUID = -8751260544114537907L;
	private static final Logger log = LoggerFactory.getLogger(ClassPathProperties.class);

	public ClassPathProperties(final String name) throws Exception {
		final ClassLoader classloader = ClassLoader.getSystemClassLoader();
		if (null == classloader) {
			throw new Exception("Can't get system classloader!");
		}

		URL url = classloader.getResource(name);
		log.trace("Resource " +name+ ". URL="+url);
		if (null == url) {
			url = classloader.getResource("/" + name);
			log.trace("Resource " +name+ " not found. URL="+url);
		}
		if (null != url) {
			log.trace("Loading from URL="+url + "...");
			try {
				final InputStream inputStream = url.openStream();
				load(inputStream);
				inputStream.close();
			} catch (Exception e) {
				throw new Exception("Can't load contents from resouce " + name + ": ",e);
			} 
			finally {
				
			}
		} else {
			throw new Exception("Resouce " + name + " doesn't exist anywhere in classpath?");
		}
	}
}
