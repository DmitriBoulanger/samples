package de.dbo.samples.spring.environments.annotation;

import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBean;
//import de.dbo.samples.spring.environments.annotation.cfgbeans.TestBeanValue;

//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainValue {

    public static void main(String[] args) {
	final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/app.xml");
	ctx.registerShutdownHook();
	
	final TestBean testBean = ctx.getBean(TestBean.class);
	System.out.println("VALUE test-bean env:    " + testBean.getEnv());
	System.out.println("VALUE test-bean name:   " + testBean.getName());
	System.out.println("VALUE test-bean value:  " + testBean.getValue());
	
	ctx.close(); // destroys the above Spring-context!
    }

}
