package de.dbo.samples.spring.environments.jcg.test;

//import de.dbo.samples.spring.environments.AppConfig;
import de.dbo.samples.spring.environments.jcg.prop.DatabaseProperties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
	final AnnotationConfigApplicationContext ctx = 
		new AnnotationConfigApplicationContext("classpath:spring/xml-config-context-mini.xml");
	ctx.getEnvironment().setActiveProfiles("test");

	System.out.println(ctx.getBean(DatabaseProperties.class).print());  

	ctx.close(); // destroys the above Spring-context!
    }
}
