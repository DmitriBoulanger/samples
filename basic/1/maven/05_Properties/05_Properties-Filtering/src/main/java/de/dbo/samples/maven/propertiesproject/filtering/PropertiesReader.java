package de.dbo.samples.maven.propertiesproject.filtering;

import static de.dbo.samples.util.print.Print.lines;

import java.util.Properties;

/**
 * Hello world!
 * 
 */
public class PropertiesReader {
	
	private final String resource;
	
	public PropertiesReader(final String resource) {
		this.resource = resource;
	}
	
	public Properties read() throws Exception {
		System.out.println("reading " + resource + " ...");
		final Properties properties = new Properties();
		final ClassLoader classloader = ClassLoader.getSystemClassLoader();
		if (null == classloader) {
			throw new Exception("Can't get system classloader!");
		}
		properties.load(classloader.getResourceAsStream(resource));
		if (properties.isEmpty()) {
			System.err.println(resource + " is empty");
	
		}
		System.out.println(resource + " contents: " + lines(properties));
		return properties;
	}
}
