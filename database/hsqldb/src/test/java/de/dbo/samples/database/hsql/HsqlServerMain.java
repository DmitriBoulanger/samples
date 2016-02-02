package de.dbo.samples.database.hsql;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HsqlServerMain {
   
    public static void main(final String args[]) throws Exception {
	final Runnable runnable = new Runnable() {
	    public final void run() {
		@SuppressWarnings("resource")
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:hsql-server.xml");
		ctx.registerShutdownHook();
	    }
	};
	new Thread(runnable, "HSQL Server").start();
    }

}
