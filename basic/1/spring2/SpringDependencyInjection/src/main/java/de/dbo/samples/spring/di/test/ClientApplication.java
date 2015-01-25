package de.dbo.samples.spring.di.test;

import de.dbo.samples.spring.di.configuration.DIConfiguration;
import de.dbo.samples.spring.di.consumer.MyApplication;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		MyApplication app = context.getBean(MyApplication.class);
		
		app.processMessage("Hi Pankaj", "pankaj@abc.com");
		
		//close the context
		context.close();
	}

}
