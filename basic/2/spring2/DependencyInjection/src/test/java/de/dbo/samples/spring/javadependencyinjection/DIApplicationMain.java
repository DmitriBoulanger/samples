package de.dbo.samples.spring.javadependencyinjection;

import de.dbo.samples.spring.javadependencyinjection.consumer.Consumer;
import de.dbo.samples.spring.javadependencyinjection.injector.EmailServiceInjectorImpl;
import de.dbo.samples.spring.javadependencyinjection.injector.SMSServiceInjectorImpl;
import de.dbo.samples.spring.javadependencyinjection.injector.ServiceInjector;

public class DIApplicationMain {

	public static void main(String[] args) {
		String msg = "Hi Pankaj";
		String email = "pankaj@abc.com";
		String phone = "4088888888";
		
		ServiceInjector injector = null;
		Consumer app = null;
		
		//Send email
		injector = new EmailServiceInjectorImpl();
		app = injector.getConsumer();
		app.processMessages(msg, email);
		
		//Send SMS
		injector = new SMSServiceInjectorImpl();
		app = injector.getConsumer();
		app.processMessages(msg, phone);
	}

}
