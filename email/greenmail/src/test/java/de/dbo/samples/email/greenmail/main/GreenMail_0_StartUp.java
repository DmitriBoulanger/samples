package de.dbo.samples.email.greenmail.main;

import de.dbo.samples.email.greenmail.GreenMailSetUp;
import de.dbo.samples.email.greenmail.GreenMailSetUpBuilder;
import de.dbo.samples.email.greenmail.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreenMail_0_StartUp implements Users {
    private static final Logger log = LoggerFactory.getLogger(GreenMail_0_StartUp.class);
    
    public static final void main(final String[] args) throws Throwable {
	final GreenMailSetUp setUp = new GreenMailSetUpBuilder()
		.withDeaultTestConfiguration()
		.withUser(USER1)
		.withUser(USER2)
		.build();
	
	log.info("GreenMail Server starting ...\n" + setUp.print().toString());
	setUp.start(1);
	Thread.sleep(100000);
    }
}
