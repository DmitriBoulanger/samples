package de.dbo.samples.database.hsql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HsqlServerJUnit {
    private static final Logger log = LoggerFactory.getLogger(HsqlServerJUnit.class);

    @Test
    public void sever() throws Exception {
	final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("hsql-server.xml");
	final HsqlServer server = ctx.getBean(HsqlServer.class);
	final HsqlServerConfiguration cfg = ctx.getBean(HsqlServerConfiguration.class);
	
	
	final String url = cfg.getConnectionURI();
	log.info(url);
	Class.forName(cfg.getDriver());
	Connection conn = DriverManager.getConnection(url, 
		cfg.getUsername(), cfg.getPassword());
	conn.prepareStatement(cfg.getValidationQuery()).execute();
	
//	ctx.close();
    }

    @Test
    public void configuration() {
	final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("hsql-server-configuration.xml");
	final HsqlServerConfiguration cfg = ctx.getBean(HsqlServerConfiguration.class);
	ctx.close();
    }

}
