package de.dbo.samples.spring.environments.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
	AnnotationConfigApplicationContext ctx =
		     new AnnotationConfigApplicationContext();
		 ctx.register(AppConfig.class);
		 ctx.refresh();
		 
		 System.out.println(
		 ctx.getBean(TestBean.class).getName());
    }

}
