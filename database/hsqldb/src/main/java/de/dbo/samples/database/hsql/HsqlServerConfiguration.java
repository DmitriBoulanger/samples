package de.dbo.samples.database.hsql;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hsqldb.persist.HsqlProperties;
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 * @see http://dev.cs.uni-magdeburg.de/java/hsqldb-1.7.2/hsqlAdvancedGuide.html
 * @see http://hsqldb.org/doc/src/org/hsqldb/server/Server.html
 * @see http://www.dis.uniroma1.it/~poggi/didattica/progettoas09/esercitazioni/esercitazione4/hibernate.properties
 */

public class HsqlServerConfiguration {
    
    /**
     * server inet-address to bind HSQLDB on.
     */
    @Value("${hsqldb.address}")
    public String address;

    /**
     * port to start HSQLDB on.
     */
    @Value("${hsqldb.port}")
    public int port;

    /**
     * name of the database to use in the server
     */
    @Value("${hsqldb.databaseName}")
    public String databaseName;

    /**
     * path to use for the database.
     */
    @Value("${hsqldb.databasePath}")
    public String databasePath;

    /**
     * username to use when authenticating.
     */
    @Value("${hsqldb.username:SA}")
    public String username;

    /**
     * password to use when authenticating.
     */
    @Value("${hsqldb.password}")
    public String password;

    /**
     * class name of the driver.
     */
    @Value("${hsqldb.driver:org.hsqldb.jdbcDriver}")
    public String driver;

    /**
     * The HSQLDB validation query.
     */
    @Value("${hsqldb.validationQuery:SELECT * FROM INFORMATION_SCHEMA.SYSTEM_USERS}")
    public String validationQuery;
    
    @Value("${hsqldb.options}")
    public String options;
    
    
    /**
     * URL to use when connecting.
     */
    private String connectionURL;
    
    
    public void init() {
	this.options = new String(options.replaceAll("'", ""));
    }
    
    public HsqlProperties hsqlProperties() {
	return hsqlProperties(0);
    }
    
    public HsqlProperties hsqlProperties(final int databaseIdx) {
	final HsqlProperties properties = new HsqlProperties();
	
	properties.setProperty("server.database."+databaseIdx, databasePath+";username="+username+"&password="+password);
	properties.setProperty("server.dbname."+databaseIdx, databaseName);
	properties.setProperty("server.remote_open", true); /* can open databases remotely */
	
	
	properties.setProperty("server.silent", false); /* false => display all queries */
	properties.setProperty("server.trace", true); /* display JDBC trace messages */
	

	return properties;
    }
    
    public String getConnectionURI() {
        if (connectionURL != null) {
            return connectionURL;
        }
        final StringBuilder sb = new StringBuilder("jdbc:hsqldb:hsql://").append(address);
        if (port > 0) {
            sb.append(":").append(port);
        }
        sb.append("/").append(databaseName);
        return sb.toString();
    }
    
    public Properties getInfo() {
	final Properties ret = new Properties();
	final String[] items = options.split(";");
	for (final String item:items) {
	    if (null!=item && 0!=item.trim().length()) {
		final String[] keyValue = item.split("=");
		ret.put(keyValue[0], keyValue[1]);
	    }
	}
	if (!nn(username)) {
	    return ret;
	}
	
	// username and password?
	ret.put("username", username);
	if (nn(password)) {
	    ret.put("password", password.trim());
	} else {
	    ret.put("password", "");
	}
	return ret;
    }
    
    private static final boolean nn(final String x) {
	return null!=x && 0!=x.trim().length();
    }
    
    
    // Getters and Setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabasePath() {
        return databasePath;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

}
