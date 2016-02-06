package de.dbo.samples.email.greenmail;

import com.icegreen.greenmail.configuration.UserBean;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

/**
 * Test users and server configurations 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Users {
    
    public static final UserBean USER1 =  new UserBean("test1@localhost", "test1", "pwd1");
    public static final UserBean USER2 =  new UserBean("test2@localhost", "test2", "pwd2");
    
    public static final ServerSetup SERVER_SETUP_TO_SEND = ServerSetupTest.SMTP;
    public static final ServerSetup SERVER_SETUP_TO_READ = ServerSetupTest.IMAP;
    
    public static final ServerSetup SERVER_SETUP_TO_SEND_DEFAULT = ServerSetup.SMTP;
    public static final ServerSetup SERVER_SETUP_TO_READ_DEFAULT = ServerSetup.IMAP;
}