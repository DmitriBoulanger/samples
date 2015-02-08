package de.dbo.samples.spring.autowiring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class SpringAutowireAbstractionJUnit {
    private static final Logger log = LoggerFactory.getLogger(SpringAutowireAbstractionJUnit.class);
    
    @Test
    public void test1() {
	final String spring = "abstraction-v1.xml";
	final de.dbo.samples.spring.autowiring.special.abstraction.v1.Extension extension;
	try {
	    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(spring);
	    	    extension = ctx.getBean(de.dbo.samples.spring.autowiring.special.abstraction.v1.Extension.class);
	    ctx.close();
	} catch (BeansException e) {
	    final String msg = "Failure getting context and/or bean for ctx=" + spring;
	    log.error(msg + ": ",e);
	    fail(msg);
	    return;
	}
	assertNotNull("Extension is null", extension);
	assertNotNull("Data is null in the extension", extension.dataFromSuperClass());
    }
    
    @Test
    public void test2() {
	final String spring = "abstraction-v2.xml";
	final de.dbo.samples.spring.autowiring.special.abstraction.v2.Extension extension;
	try {
	    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(spring);
	    	    extension = ctx.getBean(de.dbo.samples.spring.autowiring.special.abstraction.v2.Extension.class);
	    ctx.close();
	} catch (BeansException e) {
	    final String msg = "Failure getting context and/or bean for ctx=" + spring;
	    log.error(msg + ": ",e);
	    fail(msg);
	    return;
	}
	assertNotNull("Extension is null", extension);
	assertNotNull("Data is null in the extension", extension.dataFromSuperClass());
    }
    
    @Test
    public void test3() {
	final String spring = "abstraction-v3.xml";
	final de.dbo.samples.spring.autowiring.special.abstraction.v3.Extension extension;
	try {
	    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(spring);
	    	    extension = ctx.getBean(de.dbo.samples.spring.autowiring.special.abstraction.v3.Extension.class);
	    ctx.close();
	} catch (BeansException e) {
	    final String msg = "Failure getting context and/or bean for ctx=" + spring;
	    log.error(msg + ": ",e);
	    fail(msg);
	    return;
	}
	assertNotNull("Extension is null", extension);
	assertNotNull("Data is null in the extension", extension.dataFromSuperClass());
    }

}
