package de.dbo.samples.spring.environments.jcg.test;

import de.dbo.samples.spring.environments.AppConfig;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    /*
     * Doesn't work!
     */
    public static void main(String[] args) {
	final AnnotationConfigApplicationContext ctx = 
			new AnnotationConfigApplicationContext();
	ctx.getEnvironment().setActiveProfiles("test");
	ctx.register(AppConfig.class);
	ctx.refresh();
	
	

	System.out.println(ctx.getBean(DatabaseProperties.class).getDriverClass());  
	
	ctx.close(); // destroys the above Spring-context!
    }
}
