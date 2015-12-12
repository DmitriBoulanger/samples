package de.dbo.samples.spring.environments.annotation;

import static org.junit.Assert.*;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJUnit {
    private static final Logger log = LoggerFactory.getLogger(TestJUnit.class);

    @Test
    public void configuration() throws Throwable {
	logTest("Test for Configuration (AnnotationConfigApplicationContext)");
	
	final AnnotationConfigApplicationContext ctx;
	try {
	    ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
	} catch (Throwable e) {
	    SpringExceptionHandler.process(e);
	    return;
	}
	assertNotNull("AnnotationConfigApplicationContext is null!",ctx);
	ctx.registerShutdownHook();
	log.info(Print.print(ctx).toString());

	assertTestBean(ctx.getBean(TestBean.class));
	
	ctx.close(); // destroys the above Spring-context!
    }

    @Test
    public void application() throws Throwable {
	logTest("Test for complete Application from the application.xml (ClassPathXmlApplicationContext)");
	
	final ClassPathXmlApplicationContext ctx;
	try {
	    ctx = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
	} catch (Throwable e) {
	    SpringExceptionHandler.process(e);
	    return;
	}
	
	assertNotNull("ClassPathXmlApplicationContext is null!",ctx);
	
	final ApplicationConfiguration configuration = ctx.getBean(ApplicationConfiguration.class);
	assertNotNull("ApplicationConfiguration is null!",configuration);
	
	final TestBean testBean = configuration.testBean();
	assertNotNull("Test bean in the first call is null!",testBean);
	
	final TestBean testBean2 = configuration.testBean();
	assertNotNull("Test bean in the second call is null!",testBean2);
	
	assertTrue("Test-Beans from the first and second calls are not identical", testBean==testBean2);
	
	assertTestBean(ctx.getBean(TestBean.class));
	
	final Name name =  ctx.getBean(Name.class);
	assertNotNull("Name-wrapper is null!",name);
	
	final String nameValue = name.getName();
	log.info("Name from Name (wrapper): "  + nameValue);
	assertEquals("Name in wrapper ["+nameValue+"] is not as expected", testBean.getName(), nameValue);
	
	ctx.close();
    }
    
    // Special asserts
    
    private static final void assertTestBean(final TestBean testBean) {
	assertNotNull("Test-Bean is null!",testBean);
	log.info(testBean.getEnv());
	log.info(testBean.print().toString());
	
	final String env = testBean.getEnv();
	final String name = testBean.getName();
	final String value = testBean.getValue();
	final String value2 = testBean.getValue2();
	
	assertTrue("", null!=env && -1!=env.indexOf("StandardEnvironment"));
	assertEquals("Bean name is incorrect", "XXXXX from app", name); // from application properties
	assertEquals("Default value is incorrect", "Xa-Xa-Xa from defaults", value); // from default properties
	assertEquals("Default annotation value2 is incorrect", "NULL from @Value", value2); // default in @Value-annotation
    }
    
    // Helpers
    
    private static final StringBuilder logTest(String name) {
	final StringBuilder sb = new StringBuilder();
	sb.append("Starting ");
	sb.append("\n" + LINE);
	sb.append("\n            " + name );
	sb.append("\n" + LINE);
	log.info(sb.toString());
	return sb;
    }
    
    private static final String LINE = 
	    "========================================================================================";
}
