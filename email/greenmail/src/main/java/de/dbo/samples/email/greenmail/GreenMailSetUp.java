package de.dbo.samples.email.greenmail;

import com.icegreen.greenmail.configuration.UserBean;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * Set-up (configuration) of GreenMail standalone server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class GreenMailSetUp  {

    /* configuration of the server itself*/
    private final ServerSetup[] configuration;
    
    /* something to be done before start-up of the server */
    private final Hooks hooks;
    
    /* email accounts */
    private final Iterable<UserBean> users;

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
     * start-up of the server with timeout.
     * The current thread will be sleeping specified number of milliseconds
     * 
     * @param initializationTimeout
     * @throws Throwable
     */
    public void start(long initializationTimeout) throws Throwable {
	hooks.beforeStart();
	greenMail = new GreenMail(configuration);
	for (final UserBean user : users) {
	    greenMail.setUser(user.getEmail(), user.getLogin(), user.getPassword());
	}
	greenMail.start();
	Thread.sleep(initializationTimeout);
    }

    public void stop() {
	greenMail.stop();
    }
    
    public ServerSetup[] getConfiguration() {
	return configuration;
    }
    
    public Iterable<UserBean> getUsers() {
   	return users;
     }

}
