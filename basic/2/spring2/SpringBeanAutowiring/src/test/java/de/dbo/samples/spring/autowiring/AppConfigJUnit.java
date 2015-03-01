package de.dbo.samples.spring.autowiring;

//import static de.dbo.tools.utils.print.Print.lines;

import static de.dbo.tools.utils.print.Print.line;
import static de.dbo.tools.utils.print.Print.lines;

import de.dbo.samples.spring.autowiring.special.properties.AppConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertTrue;

/*
 * http://blog.jamesdbloom.com/UsingPropertySourceAndEnvironment.html
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/PropertySource.html
 */

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class AppConfigJUnit {
    private static final Logger log = LoggerFactory.getLogger(AppConfigJUnit.class);
    
    private ClassPathXmlApplicationContext ctx;

    @Before
    public void initSpringBasedApplication() {
	ctx = new ClassPathXmlApplicationContext("properties.xml");
    }
    
    @Test
    public void test() {
	final String property = ctx.getBean(AppConfig.class).testBean().getName();
	log.info(property);
	assertTrue("testbean.name=myTestBean".equals(property));
    }
    
    @Test
    public void test2() {
	final Environment environment = ctx.getBean(AppConfig.class).getEnvironment();
	log.info("Environment: " + environment.getProperty("testbean.name") );
    }
    
    @Test
    public void test3() {
	final ConfigurableEnvironment configurableEnvironment = ctx.getEnvironment();
	log.info ("SystemEnvironment: " +  lines(configurableEnvironment.getSystemEnvironment()) );
	log.info ("SystemProperties:  " +  lines(configurableEnvironment.getSystemProperties()) );
	log.info ("DefaultProfiles:   " +  line(configurableEnvironment.getDefaultProfiles()) );
	log.info ("ActiveProfiles:    " +  line(configurableEnvironment.getActiveProfiles()) );
    }
    
    @After
    public void closeSpringBasedApplication() {
	ctx.close();
    }

}
