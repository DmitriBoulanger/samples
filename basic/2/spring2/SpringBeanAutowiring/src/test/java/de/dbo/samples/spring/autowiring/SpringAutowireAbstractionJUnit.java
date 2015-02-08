package de.dbo.samples.spring.autowiring;

import de.dbo.samples.spring.autowiring.special.abstraction.v1.ExtensionV1;
import de.dbo.samples.spring.autowiring.special.abstraction.v2.ExtensionV2;

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
	final ExtensionV1 extension;
	try {
	    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(spring);
	    	    extension = ctx.getBean(ExtensionV1.class);
	    ctx.close();
	} catch (BeansException e) {
	    final String msg = "Failure getting context and/or bean for ctx=" + spring;
	    log.error(msg + ": ",e);
	    fail(msg);
	    return;
	}
	assertNotNull("ExtensionV1 is null", extension);
	assertNotNull("Data is null in the extension", extension.getData());
    }
    
    @Test
    public void test2() {
	final String spring = "abstraction-v2.xml";
	final ExtensionV2 extension;
	try {
	    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(spring);
	    	    extension = ctx.getBean(ExtensionV2.class);
	    ctx.close();
	} catch (BeansException e) {
	    final String msg = "Failure getting context and/or bean for ctx=" + spring;
	    log.error(msg + ": ",e);
	    fail(msg);
	    return;
	}
	assertNotNull("ExtensionV1 is null", extension);
	assertNotNull("Data is null in the extension", extension.getData());
    }

}
