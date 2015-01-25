package de.dbo.samples.spring.javadependencyinjection.legacy;

import de.dbo.samples.spring.javadependencyinjection.legacy.Consumer;

public class Legacy {

	public static void main(String[] args) {
		Consumer app = new Consumer();
		app.processMessages("Hi Pankaj", "pankaj@abc.com");
	}

}
