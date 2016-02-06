package de.dbo.samples.email.greenmail;

import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

public interface Users {
    
    public static final User USER1 =  new User("test1@localhost", "test1", "pwd1");
    public static final User USER2 =  new User("test2@localhost", "test2", "pwd2");
    
    public static final ServerSetup SERVER_SETUP_TO_SEND = ServerSetupTest.SMTP;
    public static final ServerSetup SERVER_SETUP_TO_READ = ServerSetupTest.IMAP;
}
