package de.dbo.samples.email.greenmail;

import javax.mail.Address;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * javax.Mail Message reader for a SMTP/IMAP (GreenMail) server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class GreenMailReader {
    private static final Logger log = LoggerFactory.getLogger(GreenMailReader.class);
    
    private final User user;
    private final ServerSetup setUp;
    private final int readOnly;
    
    /**
     * javax.Mail reader that reads and then deletes all messages
     * 
     * @param user
     * @param setUp server configuration to read messages, i.e. IMAP
     */
    public GreenMailReader(final User user, ServerSetup setUp) {
	this(user, false, setUp);
    }
    
    /**
     * javax.Mail reader
     * 
     * @param user
     * @param keepMessages if false then messages are deleted after reading them
     * @param setUp server configuration to read messages, i.e. IMAP
     */
    public GreenMailReader(final User user, boolean keepMessages, ServerSetup setUp) {
	this.user = user;
	this.setUp = setUp;
	this.readOnly = keepMessages? Folder.READ_ONLY : Folder.READ_WRITE;
    }
    
    /**
     * javax.Mail reader
     * 
     * @param store
     * @param user
     * @throws Throwable
     */
    public final Message[] readMessages() throws Throwable {
	final Store store = GreenMailUtil.getSession(setUp).getStore("imap");
	final Message[] userMessages;
	final StringBuilder sb;
	try {
	    store.connect(user.login, user.password);
	    final Folder folder = store.getFolder("INBOX");
	    try {
		switch (readOnly) {
			case Folder.READ_ONLY:
			    folder.open(readOnly);
			default:
			    folder.open(Folder.READ_WRITE);   
		}
		
		userMessages = folder.getMessages();
		sb = new StringBuilder("Messages for " + user.print() + ": ");
		for (final Message message : userMessages) {
		    final Address[] from =  message.getFrom();
		    final Object content = message.getContent();
		    final StringBuilder sb2 = new StringBuilder(" FROM ");
		    for (final Address address: from) {
			sb2.append(" " + address);
		    }
		    sb.append("\n\t - " + content);
		    sb.append(sb2);
		    if (readOnly==Folder.READ_WRITE) {
			message.setFlag(Flag.DELETED,true);
			sb.append("  " + "DELETED");
		    }
		}
	    } finally {
		folder.close(true);
	    } 
	} finally {
	    store.close();
	}
	log.info(sb.toString());
	return userMessages;
    }

}
