package de.dbo.samples.spring.javadependencyinjection.injector;

import de.dbo.samples.spring.javadependencyinjection.consumer.Consumer;
import de.dbo.samples.spring.javadependencyinjection.consumer.ConsumerImpl;
import de.dbo.samples.spring.javadependencyinjection.service.EmailServiceImpl;

public class EmailServiceInjectorImpl implements ServiceInjector {

	@Override
	public Consumer getConsumer() {
		ConsumerImpl app = new ConsumerImpl();
		app.setService(new EmailServiceImpl());
		return app;
	}

}
