package de.dbo.samples.email.greenmail;

//import com.icegreen.greenmail.configuration.PropertiesBasedGreenMailConfigurationBuilder;
import com.icegreen.greenmail.util.GreenMailUtil;
//import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

//import org.junit.Ignore;
import org.junit.Test;

import javax.mail.Folder;
import javax.mail.Message;
//import javax.mail.MessagingException;
import javax.mail.Store;
//import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class GreenMailServerStartUpJUnit {
    
    
    @Test
    public void testDoRun2() throws Throwable {
	GreenMailServerBuilder builder = new GreenMailServerBuilder();
	GreenMailServerSetUp setUp = builder
//		.withConfiguration(ServerSetup.PORT_IMAP,  )
		.withUser("test1@localhost", "test1", "pwd1")
		.withUser("test2@localhost", "test2", "pwd2")
		.build();
	System.out.println(
	setUp.print());
	setUp.start(1000);
	
	check();
    }
    
    private void check() throws Throwable {
        GreenMailUtil.sendTextEmail("test2@localhost", "test1@localhost", "Standalone test", "It worked",
                ServerSetupTest.SMTP);

        Store store = GreenMailUtil.getSession(ServerSetupTest.IMAP).getStore("imap");
        try {
            store.connect("test2", "pwd2");
            final Folder folder = store.getFolder("INBOX");
            try {
                folder.open(Folder.READ_ONLY);
                Message[] msgs = folder.getMessages();
                for (Message msg:msgs) {
                    System.out.println(msg.getContent());
                }
                Message msg = msgs[0];
                assertEquals("test1@localhost", msg.getFrom()[0].toString());
                assertEquals("Standalone test", msg.getSubject());
            } finally {
                folder.close(true);
            }
        } finally {
            store.close();
        }
    }

//    @Ignore
//    @Test
//    public void testDoRun() throws Exception {
//        GreenMailServerStartUp runner = new GreenMailServerStartUp();
//        
//        final Properties properties = new Properties();
//        properties.setProperty("greenmail.setup.test.smtp", "");
//        properties.setProperty("greenmail.setup.test.imap", "");
//        properties.setProperty(PropertiesBasedGreenMailConfigurationBuilder.GREENMAIL_USERS,
//                "test1:pwd1,test2:pwd2@localhost");
//        runner.doRun(properties);
//        
//   
//
//         
//        GreenMailUtil.sendTextEmail("test2@localhost", "test1@localhost", "Standalone test", "It worked",
//                ServerSetup.SMTP);
//
//        Store store = GreenMailUtil.getSession(ServerSetupTest.IMAP).getStore("imap");
//        try {
//            store.connect("test2", "pwd2");
//            final Folder folder = store.getFolder("INBOX");
//            try {
//                folder.open(Folder.READ_ONLY);
//                Message msg = folder.getMessages()[0];
//                assertEquals("test1@localhost", msg.getFrom()[0].toString());
//                assertEquals("Standalone test", msg.getSubject());
//            } finally {
//                folder.close(true);
//            }
//        } finally {
//            store.close();
//        }
//        
//        Thread.sleep(20000000000L);
//    }
    
   
    
    /*
     public GreenMail startGreenMail() {
    GreenMail greenMail = null;
    if (!this.error && !Mode.PROD.equals(Application.getMode())) {
        greenMail = new GreenMail(new ServerSetup(
                this.config.getInt(Key.SMTP_PORT, Default.SMTP_PORT.toInt()),
                this.config.getString(Key.SMTP_HOST, Default.LOCALHOST.toString()), Default.FAKE_SMTP_PROTOCOL.toString()));
        greenMail.start();
    }
    
    return greenMail;
    
    =====================================
    
    protected void before() throws Throwable {
    ServerSetup setup = new ServerSetup(3025, "localhost", "smtp");

    greenMail = new GreenMail(setup);
    greenMail.start();
}
}
     */
}
