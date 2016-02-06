package de.dbo.samples.email.greenmail.main;

import static org.junit.Assert.assertEquals;

import de.dbo.samples.email.greenmail.GreenMailReader;
import de.dbo.samples.email.greenmail.Users;

import javax.mail.Message;

/**
 * Reads EMails from the (running!) standalone GreenMail server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class GreenMail_2_Read implements Users {
    
    public static final void main(final String[] args) throws Throwable {
	
	@SuppressWarnings("unused") /* no messages there ...*/
	final Message[] userMessages1 = new GreenMailReader(USER1,SERVER_SETUP_TO_READ_DEFAULT).readMessages();
	 
	 final Message[] userMessages2 = new GreenMailReader(USER2,SERVER_SETUP_TO_READ_DEFAULT).readMessages();
	 if (null!=userMessages2 && 0!=userMessages2.length) {
	     assertEquals("test1@localhost", userMessages2[0].getFrom()[0].toString());
	     assertEquals("Standalone test", userMessages2[0].getSubject());
	 }
    }
}
