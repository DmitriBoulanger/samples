package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;
import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBeanEnv;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainEnv {

    public static void main(String[] args) {
 	
	final AnnotationConfigApplicationContext ctx = 
		new AnnotationConfigApplicationContext();
	ctx.register(AppConfigEnv.class);
	ctx.refresh();
	ctx.registerShutdownHook();
	
	System.out.println("application name:           " + ctx.getApplicationName());
	System.out.println("application display name:   " + ctx.getDisplayName());
	System.out.println("application startup-date:   " + ctx.getStartupDate());

	final TestBean testBean =  ctx.getBean(TestBeanEnv.class);
	System.out.println("ENV test-bean env:    " + testBean.getEnv());
	System.out.println("ENV test-bean name:   " + testBean.getName());
	System.out.println("ENV test-bean value:  " + testBean.getValue());
	
	ctx.close(); // destroys the above Spring-context!
    }

}
