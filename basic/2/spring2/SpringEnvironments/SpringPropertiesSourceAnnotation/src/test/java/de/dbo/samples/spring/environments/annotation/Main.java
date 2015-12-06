package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;

import  static org.junit.Assert.assertEquals;
import  static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
	final AnnotationConfigApplicationContext ctx = 
		new AnnotationConfigApplicationContext(AppConfig.class);

	ctx.registerShutdownHook();

	System.out.println("application name:           " + ctx.getApplicationName());
	System.out.println("application display name:   " + ctx.getDisplayName());
	System.out.println("application startup-date:   " + ctx.getStartupDate());

	final TestBean testBean =  ctx.getBean(TestBean.class);
	System.out.println("test-bean env:    " + testBean.getEnv());
	System.out.println("test-bean name:   " + testBean.getName());
	System.out.println("test-bean value:  " + testBean.getValue());
	System.out.println("test-bean value2: " + testBean.getValue2());

	ctx.close(); // destroys the above Spring-context!
    }
    
    @Test
    public void test() {
	
	final AnnotationConfigApplicationContext ctx = 
		new AnnotationConfigApplicationContext(AppConfig.class);

	ctx.registerShutdownHook();

	System.out.println("application name:           " + ctx.getApplicationName());
	System.out.println("application display name:   " + ctx.getDisplayName());
	System.out.println("application startup-date:   " + ctx.getStartupDate());

	final TestBean testBean =  ctx.getBean(TestBean.class);
	final String env = testBean.getEnv();
	final String name = testBean.getName();
	final String value = testBean.getValue();
	final String value2 = testBean.getValue2();
	 
	System.out.println("test-bean env:    " + env);
	System.out.println("test-bean name:   " + name);
	System.out.println("test-bean value:  " + value);
	System.out.println("test-bean value2: " + value2);
	
	assertTrue("", null!=env && -1!=env.indexOf("StandardEnvironment"));
	assertEquals("Application value is incorrect", "XXXXX", name); // from application properties
	assertEquals("Default value is incorrect", "Xa-Xa-Xa from defaults", value); // from default properties
	assertEquals("Default annotation value is incorrect", "NULL", value2); // default in @Value-annotation

	ctx.close(); // destroys the above Spring-context!
	
    }
}
