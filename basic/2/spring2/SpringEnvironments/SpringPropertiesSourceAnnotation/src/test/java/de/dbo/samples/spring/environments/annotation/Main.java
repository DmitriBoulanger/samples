package de.dbo.samples.spring.environments.annotation;

import static org.junit.Assert.*;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
	final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	assertNotNull("AnnotationConfigApplicationContext is null!",ctx);
	ctx.registerShutdownHook();
	log.info(Tool.print(ctx).toString());
	
	assertTestBean(ctx.getBean(TestBean.class));
	
	ctx.close(); // destroys the above Spring-context!
    }
    
    @Test
    public void test() {
	
	final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	assertNotNull("AnnotationConfigApplicationContext is null!",ctx);
	ctx.registerShutdownHook();
	log.info(Tool.print(ctx).toString());

	assertTestBean(ctx.getBean(TestBean.class));
	
	ctx.close(); // destroys the above Spring-context!
    }
    
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
    
    
    @Test
    public void testConfiguration() {
	final ClassPathXmlApplicationContext ctx = 
		new ClassPathXmlApplicationContext("classpath:spring/application.xml");
	final AppConfig configuration = ctx.getBean(AppConfig.class);
	final TestBean testBean = configuration.testBean();
	final TestBean testBean2 = configuration.testBean();
	
	assertTrue("Test-Beans instances are not identical", testBean==testBean2);
	assertTestBean(ctx.getBean(TestBean.class));
	
	log.info("Name from TestBeanName: "  + ctx.getBean(TestBeanName.class).getName());
	
	ctx.close();
    }
}
