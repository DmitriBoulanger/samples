package de.dbo.samples.spring.javadependencyinjection.injector;

import de.dbo.samples.spring.javadependencyinjection.consumer.Consumer;
import de.dbo.samples.spring.javadependencyinjection.consumer.ConsumerImpl;
import de.dbo.samples.spring.javadependencyinjection.service.SMSServiceImpl;

public class SMSServiceInjectorImpl implements ServiceInjector {

	@Override
	public Consumer getConsumer() {
		return new ConsumerImpl(new SMSServiceImpl());
	}

}
