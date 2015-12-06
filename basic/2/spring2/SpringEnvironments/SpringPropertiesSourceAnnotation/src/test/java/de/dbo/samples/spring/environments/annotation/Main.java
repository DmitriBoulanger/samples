package de.dbo.samples.spring.environments.annotation;

import static org.junit.Assert.*;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
	
	final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	assertNotNull("AnnotationConfigApplicationContext is null!",ctx);
	ctx.registerShutdownHook();
	System.out.print(Tool.print(ctx).toString());
	
	assertTestBean(ctx.getBean(TestBean.class));
	
	ctx.close(); // destroys the above Spring-context!
    }
    
    @Test
    public void test() {
	
	final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	assertNotNull("AnnotationConfigApplicationContext is null!",ctx);
	ctx.registerShutdownHook();
	System.out.print(Tool.print(ctx).toString());

	assertTestBean(ctx.getBean(TestBean.class));
	
	ctx.close(); // destroys the above Spring-context!
    }
    
    private static final void assertTestBean(final TestBean testBean) {
	assertNotNull("Test-Bean is null!",testBean);
	System.out.println(testBean.getEnv());
	System.out.print(testBean.print().toString());
	
	final String env = testBean.getEnv();
	final String name = testBean.getName();
	final String value = testBean.getValue();
	final String value2 = testBean.getValue2();
	
	assertTrue("", null!=env && -1!=env.indexOf("StandardEnvironment"));
	assertEquals("Bean name is incorrect", "XXXXX from app", name); // from application properties
	assertEquals("Default value is incorrect", "Xa-Xa-Xa from defaults", value); // from default properties
	assertEquals("Default annotation value2 is incorrect", "NULL from @Value", value2); // default in @Value-annotation
    }
}
