package de.dbo.samples.spring.javadependencyinjection.injector;

import de.dbo.samples.spring.javadependencyinjection.consumer.Consumer;

public interface ServiceInjector {

	public Consumer getConsumer();
}
