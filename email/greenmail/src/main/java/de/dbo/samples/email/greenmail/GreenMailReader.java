package de.dbo.samples.email.greenmail;

import static de.dbo.samples.email.greenmail.GreenMailPrint.print;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icegreen.greenmail.configuration.UserBean;
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
   
    private final UserBean user;
    private final ServerSetup setUp;
    private final int readOnly;
    
    /* working cache */
    private Message[] messages = null;
    private Store store = null;
    private Folder folder;
    
    /**
     * javax.Mail reader that reads and then deletes all messages
     * 
     * @param user
     * @param setUp server configuration to read messages, i.e. IMAP
     */
    public GreenMailReader(final UserBean user, ServerSetup setUp) {
	this(user, false, setUp);
    }
    
    /**
     * javax.Mail reader
     * 
     * @param user
     * @param keepMessages if false then messages are deleted after reading them
     * @param setUp server configuration to read messages, i.e. IMAP
     */
    public GreenMailReader(final UserBean user, boolean keepMessages, ServerSetup setUp) {
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
	store = GreenMailUtil.getSession(setUp).getStore("imap");
	store.connect(user.getLogin(), user.getPassword());
	folder = store.getFolder("INBOX");
	switch (readOnly) {
	case Folder.READ_ONLY:
	    folder.open(Folder.READ_ONLY);
	default:
	    folder.open(Folder.READ_WRITE);   
	}
	messages = folder.getMessages();
	for (final Message message : messages) {
	    if (readOnly==Folder.READ_WRITE) {
		message.setFlag(Flag.DELETED,true);
	    }
	}
	return messages;
    }
    
    /**
     * closes this reader.
     * if reader is not read-only, then after close all messages are deleted
     * @throws MessagingException
     */
    public void close() throws MessagingException {
	if (null!=folder && folder.isOpen()) {
	    folder.close(true);
	}
	if (null!=store) {
	    store.close();
	}
	
	store = null;
	folder = null;
	messages = null;
    }
    
    public final StringBuilder printMessages() throws Exception {
	final StringBuilder sb = new StringBuilder("Messages for " + print(user) + ": ");
	if (null==messages || 0==messages.length) {
	    sb.append("\n\t - " + "EMPTY");
	    return sb;
	} 
	
	for (final Message message : messages) {
	    sb.append("\n\t -" + print(message));
	    if (readOnly==Folder.READ_WRITE) {
		sb.append("  " + "DELETED");
	    }
	}
	return sb;
    }
}
