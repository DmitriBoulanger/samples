package de.dbo.samples.spring.environments.jcg.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ashraf
 *
 */
@Component
public final class DatabaseProperties {

    @Value("${db.driverClass}")
    private String driverClass;

    @Value("${db.connectionURL}")
    private String connectionURL;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    public String getDriverClass() {
	return driverClass;
    }

    public String getConnectionURL() {
	return connectionURL;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public StringBuilder print() {
	final StringBuilder  sb = new StringBuilder("Databas eProperties:");
	sb.append("\n\t - driverClass           = " + driverClass);
	sb.append("\n\t - passwordconnectionURL = " + connectionURL);
	sb.append("\n\t - username              = " + username);
	sb.append("\n\t - password              = " + password);
	return sb;
    }

}
