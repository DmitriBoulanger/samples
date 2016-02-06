package de.dbo.samples.email.greenmail;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * Set-up of GreenMail standalone server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class GreenMailSetUp  {

    private final ServerSetup[] configuration;
    private final Hooks hooks;
    private final Iterable<User> users;

    /* server instance */
    private GreenMail greenMail;

    public GreenMailSetUp(final GreenMailSetUpBuilder builder) {
	this.configuration = builder.configuration;
	this.hooks = builder.hooks;
	this.users = builder.users;
    }
    
    /**
     * @return configured server instance
     */
    public GreenMail getServer() {
   	return greenMail;
    }
    
    /**
     * @return pretty-print of the server configuration
     */
    public StringBuilder print() {
	final StringBuilder sb = new StringBuilder();
	for (final ServerSetup serverSetup : configuration) {
	    sb.append("\n\t - " 
		    + serverSetup.getProtocol().toUpperCase()
		    + " " + serverSetup.getBindAddress() 
		    + ":" + serverSetup.getPort());
	}
	for (User user:users) {
	    sb.append("\n\t - " + user.print());
	}
	return sb;
    }

    /**
     * start-up of the server with timeout.
     * The current thread will be sleeping specified number of milliseconds
     * 
     * @param initializationTimeout
     * @throws Throwable
     */
    public void start(long initializationTimeout) throws Throwable {
	hooks.beforeStart();
	greenMail = new GreenMail(configuration);
	for (final User user : users) {
	    greenMail.setUser(user.email, user.login, user.password);
	}
	greenMail.start();
	Thread.sleep(initializationTimeout);
    }

    public void stop() {
	greenMail.stop();
    }
}
