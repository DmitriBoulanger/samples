package de.dbo.samples.email.greenmail.main;

import static org.junit.Assert.assertEquals;

import de.dbo.samples.email.greenmail.GreenMailReader;
import de.dbo.samples.email.greenmail.Users;

import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icegreen.greenmail.configuration.UserBean;

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
    private static final Logger log = LoggerFactory.getLogger(GreenMail_0_StartUp.class);
    
    public static final void main(final String[] args) throws Throwable {
	
	GreenMailReader reader;
	
	reader = getGreenMailReader(USER1);
	reader.readMessages();
	log.info("" + reader.printMessages());
	reader.close();
	
	reader = getGreenMailReader(USER2);
	final Message[] userMessages2 = reader.readMessages();
	log.info("" + reader.printMessages());
	
	 if (null!=userMessages2 && 0!=userMessages2.length) {
	     assertEquals("test1@localhost", userMessages2[0].getFrom()[0].toString());
	     assertEquals("Standalone test", userMessages2[0].getSubject());
	 }
	 reader.close();
    }
    
    private static final GreenMailReader getGreenMailReader(final UserBean user) {
	return new GreenMailReader(user,SERVER_SETUP_TO_READ_DEFAULT);
	
    }
}
