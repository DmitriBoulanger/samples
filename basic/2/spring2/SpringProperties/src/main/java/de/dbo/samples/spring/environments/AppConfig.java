package de.dbo.samples.spring.environments;

import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration("classpath:spring/xml-config-context.xml")
public class AppConfig {
    
    @Autowired
    Environment env;

    @Bean
    public DatabaseProperties dbProp() {
	DatabaseProperties ret = new DatabaseProperties();
        return ret;
    }

}
