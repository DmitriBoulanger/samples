package de.dbo.samples.email.greenmail;

import static de.dbo.samples.email.greenmail.GreenMailPrint.print;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Flags.Flag;

import com.icegreen.greenmail.configuration.UserBean;
import com.icegreen.greenmail.util.ServerSetup;

public class GreenMailPrint {
    
    public static final StringBuilder print(final UserBean user) {
	final StringBuilder sb = new StringBuilder("EMail account:");
	sb.append(" ");
	sb.append(user.getEmail());
	sb.append(" ");
	sb.append("login=(" + user.getLogin() + "/"+ user.getPassword() + ")");
	return sb;
    }
    
    /**
     * @return pretty-print of the server set-up
     */
    public static final StringBuilder print(final GreenMailSetUp greenMailSetUp) {
	final StringBuilder  sb = print(greenMailSetUp.getConfiguration());
	for (final UserBean user : greenMailSetUp.getUsers()) {
	    sb.append("\n\t - " + print(user));
	}
	return sb;
    }
    
    /**
     * @return pretty-print of the server configuration
     */
    public static final StringBuilder print(final ServerSetup[] configuration) {
	final StringBuilder sb = new StringBuilder("GreenMail server configuration:");
	for (final ServerSetup serverSetup : configuration) {
	    sb.append("\n\t - " + print(serverSetup));
	}
	return sb;
    }
    
    public static final StringBuilder print(final ServerSetup serverSetup) {
	final StringBuilder sb = new StringBuilder(); 
	    sb.append(serverSetup.getProtocol().toUpperCase());
	    sb.append(" " + serverSetup.getBindAddress()); 
	    sb.append(":" + serverSetup.getPort());
	return sb;
    }
    
    public static final StringBuilder print(final Message message) throws Exception {
	final StringBuilder sb = new StringBuilder();
	    final Address[] from =  message.getFrom();
	    final Object content = message.getContent();
	    final StringBuilder sb2 = new StringBuilder(" FROM ");
	    for (final Address address: from) {
		sb2.append(" " + address);
	    }
	    sb.append(content);
	    sb.append(sb2);
	return sb;
    }
}
