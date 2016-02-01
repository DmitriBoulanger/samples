package de.dbo.samples.database.hsql;

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
 */

public class HsqlServerConfiguration {
    
    /**
     * server inet-address to bind HSQLDB on.
     */
    @Value("${hsqldb.address:localhost}")
    public String address;

    /**
     * port to start HSQLDB on.
     */
    @Value("${hsqldb.port:9001}")
    public int port;

    /**
     * name of the database to use in the server
     */
    @Value("${hsqldb.databaseName:xdb}")
    public String databaseName;

    /**
     * path to use for the database.
     */
    @Value("${hsqldb.databasePath:mem:test}")
    public String databasePath;

    /**
     * username to use when authenticating.
     */
    @Value("${hsqldb.username:sa}")
    public String username;

    /**
     * password to use when authenticating.
     */
    @Value("${hsqldb.password:sa}")
    public String password;

    /**
     * class name of the driver.
     */
    @Value("${hsqldb.driver:org.hsqldb.jdbcDriver}")
    public String driver;

    /**
     * The HSQLDB validation query.
     */
    @Value("${hsqldb.validationQuery:SELECT 1 FROM INFORMATION_SCHEMA.SYSTEM_USERS}")
    public String validationQuery;

    /**
     * Whether to bypass running HSQLDB.
     */
    @Value("${hsqldb.skip:false}")
    public boolean skip;
    
    /**
     * URL to use when connecting.
     */
    private String connectionURL;
    
    
    public void init() {
	
    }
    
    public HsqlProperties hsqlProperties() {
	return hsqlProperties(0);
    }
    
    public HsqlProperties hsqlProperties(final int databaseIdx) {
	final HsqlProperties properties = new HsqlProperties();
	
	properties.setProperty("server.database."+databaseIdx, databasePath);
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
        return "";
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    
    
    
}
