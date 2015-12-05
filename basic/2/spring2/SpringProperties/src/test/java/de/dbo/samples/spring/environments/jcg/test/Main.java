package de.dbo.samples.spring.environments.jcg.test;

import de.dbo.samples.spring.environments.AppConfig;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
	AnnotationConfigApplicationContext ctx =
		     new AnnotationConfigApplicationContext();
		 ctx.register(AppConfig.class);
		 ctx.refresh();
		 ctx.getEnvironment().setActiveProfiles("test");
		 
		System.out.println(ctx.getBean(DatabaseProperties.class).getDriverClass());  
    }

}
