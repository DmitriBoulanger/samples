package de.dbo.samples.jpa.jpa0.config;

import de.dbo.samples.jpa.jpa0.config.connections.ConnectionPropertiesFactory;
import de.dbo.samples.jpa.jpa0.config.connections.Connections;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to generate properties of available persistence units
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class PersistenceConfigurationFactory {
	
	private PersistenceConfigurationFactory() {
		// should be never initialized as an instance
	}
	
	public static Map<String, String> persistence(PersistenceConfigurations config) {
		final Map<String, String> ret = new HashMap<String, String>();
		switch(config) {
		
		case PRODUCTION:
			ret.put("eclipselink.target-database","MYSQL");
			ret.put("eclipselink.ddl-generation","none");
			ret.put("eclipselink.logging.level","INFO");
			ret.putAll(ConnectionPropertiesFactory.connection(Connections.MY_SQL));
			return ret;
			
		case TEST:
			ret.put("eclipselink.target-database","DERBY");
			ret.put("eclipselink.ddl-generation","create-tables");
			ret.put("eclipselink.logging.level","FINE");
			ret.put("eclipselink.logging.file", "target/logs/eclipselink.log");
		    ret.putAll(ConnectionPropertiesFactory.connection(Connections.DERBY_IN_MEMORY));
		    return ret;
			
			default: throw new RuntimeException(
					"SYSTEM ERROR: Unknown persistence configutaion");
		}
	}
	
}
