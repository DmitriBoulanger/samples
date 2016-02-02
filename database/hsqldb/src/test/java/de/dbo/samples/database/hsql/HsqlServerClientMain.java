package de.dbo.samples.database.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HsqlServerClientMain {
    private static final Logger log = LoggerFactory.getLogger(HsqlServerClientMain.class);

    public static final void main(final String[] args) throws Exception {
	final ClassPathXmlApplicationContext ctx = 
		new ClassPathXmlApplicationContext("hsql-server-configuration.xml");
	final HsqlServerConfiguration cfg = ctx.getBean(HsqlServerConfiguration.class);
	final String url = cfg.getConnectionURI();
	final Properties info = cfg.getInfo();
	log.info("Connecting: "
		+ "\n\t - " + url
		+ "\n\t - username: [" + cfg.getUsername() +"]"
		+ "\n\t - password: [" + cfg.getPassword()  +"]"
		);
	log.info(info.toString());
	Class.forName(cfg.getDriver());
	Connection conn = DriverManager.getConnection(url, info);
	PreparedStatement preparedStatement = conn.prepareStatement(cfg.getValidationQuery());
	final ResultSet resultSet =  preparedStatement.executeQuery();
	final int columnCnt = resultSet.getMetaData().getColumnCount()+1;
	final String[] columnNames = new String[columnCnt];
	for(int i =1; i<columnCnt; i++) {
	    columnNames[i] = resultSet.getMetaData().getColumnName(i);
	}
	
	final StringBuilder sb = new StringBuilder();
	while(resultSet.next()) {
	    sb.append("\n\t - ");
	    for (int i=1; i<columnCnt; i++) {
		sb.append(columnNames[i] + "=");
		sb.append(resultSet.getObject(i) + " ");
	    }
	}
	resultSet.close();
	conn.close();
	log.info("Result set:" + sb.toString());
	ctx.close();
    }


}
