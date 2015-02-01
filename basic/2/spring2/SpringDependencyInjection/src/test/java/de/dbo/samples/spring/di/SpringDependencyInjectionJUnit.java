package de.dbo.samples.spring.di;

import de.dbo.samples.spring.di.consumer.MyApplication;
import de.dbo.samples.spring.di.services.MessageService;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "de.dbo.samples.spring.di.consumer")
public class SpringDependencyInjectionJUnit {
    private static final Logger log = LoggerFactory.getLogger(SpringDependencyInjectionJUnit.class);

    private AnnotationConfigApplicationContext context = null;

    @Bean
    public MessageService getMessageService() {
	return new MessageService() {
	    public boolean sendMessage(String msg, String rec) {
		log.info("Mock Service");
		return true;
	    }

	};
    }

    @Before
    public void setUp() throws Exception {
	context = new AnnotationConfigApplicationContext(SpringDependencyInjectionJUnit.class);
    }

    @After
    public void tearDown() throws Exception {
	context.close();
    }

    @Test
    public void test() {
	MyApplication app = context.getBean(MyApplication.class);
	Assert.assertTrue(app.processMessage("Hi Pankaj", "pankaj@abc.com"));
    }
}
