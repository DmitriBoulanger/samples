package de.dbo.samples.email.greenmail.main;

import static org.junit.Assert.assertEquals;

import de.dbo.samples.email.greenmail.GreenMailReader;
import de.dbo.samples.email.greenmail.Users;

import javax.mail.Message;

public class GreenMail_2_Read implements Users {
    
    public static final void main(final String[] args) throws Throwable {
	
	 final Message[] userMessages1 = new GreenMailReader(USER1,SERVER_SETUP_TO_READ).readMessages();
	 final Message[] userMessages2 = new GreenMailReader(USER2,SERVER_SETUP_TO_READ).readMessages();
	 
	 if (null!=userMessages2 && 0!=userMessages2.length) {
	     assertEquals("test1@localhost", userMessages2[0].getFrom()[0].toString());
	     assertEquals("Standalone test", userMessages2[0].getSubject());
	 }
    }
}
