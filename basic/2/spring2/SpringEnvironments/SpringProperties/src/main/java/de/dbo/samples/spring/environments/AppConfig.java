package de.dbo.samples.spring.environments;

import de.dbo.samples.spring.environments.env.GenericEnv;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;
import de.dbo.samples.spring.environments.jcg.prop.JmsProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration("classpath:spring/xml-config-context.xml")
public class AppConfig {
    
    @Autowired
    private GenericEnv env;

    @Autowired
    private DatabaseProperties dbProp;

    @Autowired
    private JmsProperties jmsProp;

    public final void print() {
	System.out.println("Environment        : " + env.toString());
	System.out.println("Database Properties: " + dbProp.toString());
	System.out.println("JMS Properties     : " + jmsProp.toString());
    }

}
