package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;

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

@Configuration()
@PropertySources
({
    /* Order in important: the later overwrites previous values */
    @PropertySource(value="classpath:properties/non-existing.properties", ignoreResourceNotFound=true)
    
    /* use default values only if actual not found */
    , @PropertySource(value="classpath:properties/default.properties")
    , @PropertySource(value="classpath:properties/app.properties", ignoreResourceNotFound=true)
    
    , @PropertySource(value="classpath:properties/non-existing2.properties", ignoreResourceNotFound=true)
})
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
	return new TestBean(env);
    }
    
    /*
       In order to resolve ${...} placeholders in <bean> definitions 
       or @Value annotations using properties from a PropertySource, 
       one must register a PropertySourcesPlaceholderConfigurer. 
       This happens automatically in XML with <context:property-placeholder>  
       but must be explicitly registered using a static @Bean method
       
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	return new PropertySourcesPlaceholderConfigurer();
    }
}
