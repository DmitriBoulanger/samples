package de.dbo.samples.spring.environments.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import static de.dbo.tools.utils.print.Print.line;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;

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
    // Order below in very important: the later overwrites previous values

    /* define directory with properties, e.g. configuration profile */
    @PropertySource(value="classpath:location.properties", ignoreResourceNotFound=false)

    // Using properties from the defined above profile ....
    
    /*
     * Default values just below are overwritten if actual found 
     */
    , @PropertySource(value="classpath:${properties.location}/default.properties", ignoreResourceNotFound=false)
    
    /*
     * Possible (not obligatory) properties
     */
    , @PropertySource(value="classpath:${properties.location}/app.properties", ignoreResourceNotFound=true)
    , @PropertySource(value="classpath:${properties.location}/non-existing.properties", ignoreResourceNotFound=true)
})
public class ApplicationConfiguration {

    /*
     * The Environment object is @Autowired into this configuration
     * and only then it can be used to populate the TestBean object below.
     * This environment contains values for all placeholders that have been found in the properties-files
     */

    @Autowired
    Environment env;

    /*
     * Configuration beans initialized from the environment.
     * Each call of the methods below returns the same bean-instance
     */

    @Bean
    public TestBean testBean() {
	return new TestBean(env);
    }

    /*
     * In order to resolve ${...} placeholders in <bean> definitions
     * or @Value annotations using properties from a PropertySource,
     * one must register a PropertySourcesPlaceholderConfigurer.
     * This happens automatically in XML with <context:property-placeholder>
     * but must be explicitly registered using a static @Bean method
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	return new PropertySourcesPlaceholderConfigurer();
    }
    
    /*
     * Special things ...
     */
    
    public final Environment getEnvironment() {
	return env;
    }
    
    public final StringBuilder print() {
  	if (null==env) {
  	    return new StringBuilder("Environment is null!");
  	}
  	final StringBuilder sb = new StringBuilder("Environment:");
  	sb.append("\n\t - object:            " + env.toString());
  	sb.append("\n\t - class:             " + env.getClass().getName());
  	sb.append("\n\t - profiles default:  " + line(env.getDefaultProfiles()));
  	sb.append("\n\t - profiles active:   " + line(env.getActiveProfiles()));
  	
  	return sb;
    }
    
}
