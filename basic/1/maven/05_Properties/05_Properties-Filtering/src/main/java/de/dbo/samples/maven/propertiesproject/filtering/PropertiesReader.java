package de.dbo.samples.maven.propertiesproject.filtering;

import static de.dbo.samples.util.print.Print.lines;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class PropertiesReader {
	private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);
	
	private final String resource;
	
	public PropertiesReader(final String resource) {
		this.resource = resource;
	}
	
	public Properties read() throws Exception {
		log.info("reading " + resource + " ...");
		final Properties properties = new Properties();
		final ClassLoader classloader = ClassLoader.getSystemClassLoader();
		if (null == classloader) {
			throw new Exception("Can't get system classloader!");
		}
		properties.load(classloader.getResourceAsStream(resource));
		if (properties.isEmpty()) {
			log.error(resource + " is empty");
	
		} else {
			log.info(resource + " contents: " + lines(properties));
		}
		return properties;
	}
}
