package de.dbo.samples.email.greenmail;

import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;

import static org.junit.Assert.assertEquals;

public class GreenMailJUnit implements Users {
    private static final Logger log = LoggerFactory.getLogger(GreenMailJUnit.class);
    
    @Test
    public void test() throws Throwable {
	GreenMailSetUp setUp = new GreenMailSetUpBuilder()
		.withUser(USER1)
		.withUser(USER2)
		.withConfiguration(SERVER_SETUP_TO_READ_DEFAULT, SERVER_SETUP_TO_SEND_DEFAULT)
		.build();
	log.info(setUp.print().toString());
	setUp.start(400);
	
	GreenMailUtil.sendTextEmail(USER2.email, USER1.email, "Standalone test", "It worked", SERVER_SETUP_TO_SEND_DEFAULT);	 
	 final Message[] userMessages1 = new GreenMailReader(USER1,SERVER_SETUP_TO_READ_DEFAULT).readMessages();
	 final Message[] userMessages2 = new GreenMailReader(USER2,SERVER_SETUP_TO_READ_DEFAULT).readMessages();
	 
	 assertEquals("test1@localhost", userMessages2[0].getFrom()[0].toString());
         assertEquals("Standalone test", userMessages2[0].getSubject());
    }
}
