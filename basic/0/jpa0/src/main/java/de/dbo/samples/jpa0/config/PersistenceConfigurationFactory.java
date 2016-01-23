package de.dbo.samples.jpa0.config;

import static de.dbo.samples.jpa0.config.connections.ConnectionPropertiesFactory.connection;
import static de.dbo.samples.jpa0.config.connections.Connections.DERBY_IN_MEMORY;
import static de.dbo.samples.jpa0.config.connections.Connections.MY_SQL;

import java.util.HashMap;
import java.util.Map;

//import org.eclipse.persistence.config.PersistenceUnitProperties;

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
        
        ret.put("eclipselink.ddl-generation", "drop-and-create-tables");
        
        switch (config) {

            case PERSISTENT:
            	ret.putAll(connection(MY_SQL));
                return ret;

            case IN_MEMORY:
            	ret.putAll(connection(DERBY_IN_MEMORY));
//                ret.put("eclipselink.ddl-generation.output-mode", "both");
//                ret.put("eclipselink.logging.file", "target/logs/eclipselink.log");
//                ret.put("eclipselink.create-ddl-jdbc-file-name", "target/logs/createDDL.sql"); 
                return ret;

            default:
                throw new RuntimeException(
                        "SYSTEM ERROR: Unknown persistence configutaion");
        }
    }

}
