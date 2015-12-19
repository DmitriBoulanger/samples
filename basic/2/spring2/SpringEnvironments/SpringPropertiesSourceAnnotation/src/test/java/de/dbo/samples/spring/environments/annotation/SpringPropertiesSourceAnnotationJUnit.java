package de.dbo.samples.spring.environments.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static de.dbo.samples.spring.environments.annotation.cfgutil.SpringPrint.print;

import de.dbo.samples.spring.environments.annotation.cfg.Config;
import de.dbo.samples.spring.environments.annotation.cfg.cfgbeans.TestBean;
import de.dbo.samples.spring.environments.annotation.cfgutil.SpringExceptionHandler;
import de.dbo.samples.spring.environments.annotation.cfgutil.SpringPrint;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Detailed tests for the Spring Property Source annotated classes
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class SpringPropertiesSourceAnnotationJUnit {
    private static final Logger log = LoggerFactory.getLogger(SpringPropertiesSourceAnnotationJUnit.class);

    /**
     * Test for the Config class
     * @throws Throwable if something really bad
     */
    @Test
    public void configuration() throws Throwable {
	logTest("Test for Configuration (AnnotationConfigApplicationContext)");
	
	final AnnotationConfigApplicationContext ctx;
	try {
	    ctx = new AnnotationConfigApplicationContext(Config.class);
	    ctx.registerShutdownHook();
	} catch (Throwable e) {
	    SpringExceptionHandler.process(e);
	    return;
	}
	assertNotNull("AnnotationConfigApplicationContext is null!",ctx);
	log.info(SpringPrint.print(ctx).toString());

	assertEnvironment(ctx.getEnvironment());
	assertTestBean(ctx.getBean(TestBean.class));
	
	ctx.close(); // destroys the above Spring-context!
    }

    /**
     * Test for the Spring-context application-test.xml 
     * @throws Throwable if something really bad
     */
    @Test
    public void application() throws Throwable {
	logTest("Test for complete Application from the application.xml (ClassPathXmlApplicationContext)");
	
	final ClassPathXmlApplicationContext ctx;
	try {
	    ctx = new ClassPathXmlApplicationContext("classpath:spring/application-test.xml");
	    ctx.registerShutdownHook();
	} catch (Throwable e) {
	    SpringExceptionHandler.process(e);
	    return;
	}
	
	assertNotNull("ClassPathXmlApplicationContext is null!",ctx);
	log.debug("ClassPathXmlApplicationContext environments: " + print(ctx.getEnvironment()));
	
	final Config configuration = ctx.getBean(Config.class);
	assertNotNull("Application Configuration is null!",configuration);
	assertEnvironment(configuration.getEnvironment());
	
	final Environment configurationEnvironment = configuration.getEnvironment();
	assertNotNull("Configuration environment is null!",configurationEnvironment);
	assertTrue("Environment in configuration is not as expected", -1!=print(configurationEnvironment).indexOf("StandardEnvironment"));

	final TestBean testBean = configuration.testBean();
	assertNotNull("Test bean in the first call is null!",testBean);
	
	final TestBean testBean2 = configuration.testBean();
	assertNotNull("Test bean in the second call is null!",testBean2);
	
	assertTrue("Test-Beans from the first and second calls are not identical", testBean==testBean2);
	
	assertTestBean(ctx.getBean(TestBean.class));
	
	final NameWrapper name =  ctx.getBean(NameWrapper.class);
	assertNotNull("NameWrapper-wrapper is null!",name);
	
	final String nameValue = name.getName();
	assertEquals("NameWrapper in wrapper ["+nameValue+"] is not as expected  ["+testBean.getName()+"]"
		, testBean.getName(), nameValue);
	
	ctx.close();
    }
    
    // Special assertions
    
    /* values in the tets-bean instance variables should be as expected */
    private static final void assertTestBean(final TestBean testBean) {
	assertNotNull("Test-Bean is null!",testBean);
	log.info(testBean.print().toString());
	
	final String name = testBean.getName();
	final String value = testBean.getValue();
	final String value2 = testBean.getValue2();

	assertEquals("Bean name is incorrect", "XXXXX from app", name); // from application properties
	assertEquals("Default value is incorrect", "Xa-Xa-Xa from defaults", value); // from default properties
	assertEquals("Default annotation value2 is incorrect", "NULL from @Value", value2); // default in @Value-annotation
    }
    
    /* values for all place-holders should be in the environment */
    private static final void assertEnvironment(final Environment environment) {
	assertNotNull("Environment is null!",environment);
	assertNotNull("properties.location is null in the environment!",environment.getProperty("properties.location"));
	assertNotNull("testbean.name is null in the environment!",environment.getProperty("testbean.name"));
	assertNotNull("testbean.value is null in the environment!",environment.getProperty("testbean.value"));
    }
    
    // Helpers
    
    private static final StringBuilder logTest(String name) {
	final StringBuilder sb = new StringBuilder();
	sb.append("\n\nStarting ... ");
	sb.append("\n" + LINE);
	sb.append("\n            " + name );
	sb.append("\n" + LINE);
	log.info(sb.toString());
	return sb;
    }
    
    private static final String LINE = 
	    "=======================================================================================================";
}
