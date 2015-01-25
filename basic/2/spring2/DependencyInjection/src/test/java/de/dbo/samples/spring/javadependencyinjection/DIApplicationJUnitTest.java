package de.dbo.samples.spring.javadependencyinjection;

import de.dbo.samples.spring.javadependencyinjection.consumer.Consumer;
import de.dbo.samples.spring.javadependencyinjection.consumer.ConsumerImpl;
import de.dbo.samples.spring.javadependencyinjection.injector.ServiceInjector;
import de.dbo.samples.spring.javadependencyinjection.service.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DIApplicationJUnitTest {
	private static final Logger log = LoggerFactory.getLogger(DIApplicationJUnitTest.class);

	private ServiceInjector injector;
	
	@Before
	public void setUp(){
		//mock the injector with anonymous class
		injector = new ServiceInjector() {
			
			@Override
			public Consumer getConsumer() {
				//mock the message service
				return new ConsumerImpl(new Service() {
					
					@Override
					public void sendMessage(String msg, String rec) {
						log.info("Mock Message Service implementation");			
					}
				});
			}
		};
	}
	
	@Test
	public void test() {
		Consumer consumer = injector.getConsumer();
		consumer.processMessages("Hi Pankaj", "pankaj@abc.com");
	}
	
	@After
	public void tear(){
		injector = null;
	}

}
