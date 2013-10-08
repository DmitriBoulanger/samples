package de.dbo.samples.jpa.jpa0.config.connections;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to generate properties of available database connections
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class ConnectionPropertiesFactory {

    private ConnectionPropertiesFactory() {
        // should be never initialized as an instance
    }

    public static Map<String, String> connection(final Connections connection) {
        final Map<String, String> ret = new HashMap<String, String>();
        switch (connection) {

            case MY_SQL:
                ret.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
                ret.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/Students_DB");
                ret.put("javax.persistence.jdbc.user", "root");
                ret.put("javax.persistence.jdbc.password", "");

                return ret;

            case DERBY_IN_MEMORY:
                ret.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
                ret.put("javax.persistence.jdbc.url", "jdbc:derby:memory:StudentsDB;create=true");
                ret.put("javax.persistence.jdbc.user", "");
                ret.put("javax.persistence.jdbc.password", "");

                return ret;

            default:
                throw new RuntimeException("SYSTEM ERROR: Unknown connection");
        }
    }

}
