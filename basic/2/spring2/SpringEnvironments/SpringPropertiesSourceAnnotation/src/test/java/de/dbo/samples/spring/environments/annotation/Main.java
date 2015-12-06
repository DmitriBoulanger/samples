package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;


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
}
