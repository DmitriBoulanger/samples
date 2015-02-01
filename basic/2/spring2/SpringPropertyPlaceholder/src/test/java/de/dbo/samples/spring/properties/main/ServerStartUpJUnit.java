package de.dbo.samples.spring.properties.main;

import java.rmi.NotBoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.RemoteLookupFailureException;

import static org.junit.Assert.fail;

public class ServerStartUpJUnit {
    private static final Logger log = LoggerFactory.getLogger(ServerStartUpJUnit.class);

    @BeforeClass
    public static void systemProperties() {
	System.setProperty("product.conf", "conf");
	System.setProperty("service.conf.name", "From system properties");
    }

    @Test
    public void serverStartupClient() throws Exception {
	final ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("server.xml");
	new Client().run();
	ctx.close();

	try {
	    new ClientTest().run();
	    fail("Server is closed but the test-client runs!");
	} catch (BeanCreationException e) {
	    assertion(e);
	} catch (Exception e) {
	    failure(e);
	}
    }

    @Test
    public void serverTestStartupClientTest() throws Exception {
	final ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("server-test.xml");
	ctx.registerShutdownHook();
	new ClientTest().run();
	ctx.close();

	try {
	    new ClientTest().run();
	    fail("Server is closed but the test-client runs!");
	} catch (BeanCreationException e) {
	    assertion(e);
	} catch (Exception e) {
	    failure(e);
	}
    }

    @Test
    public void errorClinetTest() {
	try {
	    new ClientTest().run();
	    fail("Server is closed but the test-client runs!");
	} catch (BeanCreationException e) {
	    assertion(e);
	} catch (Exception e) {
	    failure(e);
	}
    }

    //
    // Assertions
    //

    private static final void failure(Exception e) {
	log.error("Unexpected error:", e);
	fail("Server is closed but the client run has an unexpected exception");
    }

    private static final void assertion(BeanCreationException e) {
	if (null != e.getCause() && e.getCause() instanceof RemoteLookupFailureException
		&& null != e.getCause().getCause() && e.getCause().getCause() instanceof NotBoundException) {
	    log.info("Expected error: " + e.getCause().getCause().toString());
	} else {
	    failure(e);
	}
    }
}
