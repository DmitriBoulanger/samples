package de.dbo.samples.email.greenmail;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

public class GreenMailServerSetUp  {

    static final Hooks NULL_HOOKS = new Hooks() {
	@Override
	public void beforeStart() {
	    // TODO Auto-generated method stub
	}
    };

    private final ServerSetup[] configuration;
    private final Hooks hooks;
    private final Iterable<User> users;

    private GreenMail greenMail;

    public GreenMailServerSetUp(final GreenMailServerBuilder builder) {
	this.configuration = builder.configuration;
	this.hooks = builder.hooks;
	this.users = builder.users;
    }
    
    public StringBuilder print() {
	final StringBuilder sb = new StringBuilder();
	for (final ServerSetup serverSetup : configuration) {
	    sb.append("\n\t - " + serverSetup);
	}
	for (User user:users) {
	    sb.append("\n\t - " + user.print());
	}
	return sb;
    }

    protected void start(long initializationTimeout) throws Throwable {
	hooks.beforeStart();
	greenMail = new GreenMail(configuration);
	greenMail.start();
	for (final User user : users) {
	    greenMail.setUser(user.email, user.login, user.password);
	}
	Thread.sleep(initializationTimeout);
    }

    protected void stop() {
	greenMail.stop();
    }

    public GreenMail getServer() {
	return greenMail;
    }

}
