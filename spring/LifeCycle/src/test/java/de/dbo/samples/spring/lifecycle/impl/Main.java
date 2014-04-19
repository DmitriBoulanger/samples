package de.dbo.samples.spring.lifecycle.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.spring.api.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	@Test
	public void test() {
		log.info("running ...");
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext("LifeCycle.xml");
		assertNotNull(context);
		final Hello hello = (Hello) context.getBean("helloWorld");
		assertNotNull(hello);
		hello.getMessage();
		context.registerShutdownHook();
	}
}
