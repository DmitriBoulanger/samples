package de.dbo.samples.email.greenmail;

import static org.junit.Assert.*;

import static de.dbo.samples.email.greenmail.GreenMailPrint.print;

import javax.mail.Message;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icegreen.greenmail.configuration.UserBean;
import com.icegreen.greenmail.util.GreenMailUtil;

public class GreenMailJUnit implements Users {
    private static final Logger log = LoggerFactory.getLogger(GreenMailJUnit.class);

    @Test
    public void test() throws Throwable {
	GreenMailSetUp setUp = new GreenMailSetUpBuilder()
		.withUser(USER1)
		.withUser(USER2)
		.withConfiguration(SERVER_SETUP_TO_READ_DEFAULT, SERVER_SETUP_TO_SEND_DEFAULT)
		.build();
	log.info("" + print(setUp));
	setUp.start(400);

	GreenMailUtil.sendTextEmail(USER2.getEmail(), USER1.getEmail(), "Standalone test", "It worked", SERVER_SETUP_TO_SEND_DEFAULT);	 
	
	GreenMailReader reader;
	
	reader = getGreenMailReader(USER1);
	reader.readMessages();
	log.info("" + reader.printMessages());
	
	reader = getGreenMailReader(USER2);
	final Message[] userMessages2 = reader.readMessages();
	assertNotNull("Messages for " + print(USER2) + " are NULL" , userMessages2);
	assertTrue("Messages for " + print(USER2) + " are EMPTY" , 0!=userMessages2.length);
	log.info("" + reader.printMessages());
	assertEquals("test1@localhost", userMessages2[0].getFrom()[0].toString());
	assertEquals("Standalone test", userMessages2[0].getSubject());
    }
    
    private static final GreenMailReader getGreenMailReader(final UserBean user) {
	return new GreenMailReader(user,SERVER_SETUP_TO_READ_DEFAULT);
	
    }
}
