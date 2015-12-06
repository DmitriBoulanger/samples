package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;
import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBeanEnv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

@Configuration
@PropertySources
({
    @PropertySource(value="classpath:properties/app.properties"),
    @PropertySource(value="classpath:properties/default.properties",ignoreResourceNotFound=true)
})
public class AppConfigEnv {
    
    @Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
        return new TestBeanEnv(env);
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	return new PropertySourcesPlaceholderConfigurer();
    }
}
