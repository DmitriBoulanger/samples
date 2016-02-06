package de.dbo.samples.email.greenmail;

import java.util.HashSet;
import java.util.Set;

import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

/**
 * SMTP/IMAP configuration (set-up) builder
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class GreenMailSetUpBuilder { 

    private static final Hooks NULL_HOOKS = new Hooks() {
	@Override
	public void beforeStart() {

	}
    };

    protected ServerSetup[] configuration;
    protected Hooks hooks;
    protected final Set<User> users;

    /**
     * Default configuration for a SMTP/IMAP server
     */
    public GreenMailSetUpBuilder() {
	configuration = ServerSetup.SMTP_IMAP;
	users = new HashSet<User>();
	hooks = NULL_HOOKS;
    }

    public GreenMailSetUp build() {
	return new GreenMailSetUp(this);
    }

    public GreenMailSetUpBuilder withDeaultTestConfiguration() {
	this.configuration = ServerSetupTest.SMTP_IMAP;
	return this;
    }

    public GreenMailSetUpBuilder withConfiguration(final ServerSetup configuration) {
	this.configuration = new ServerSetup[] { configuration };
	return this;
    }

    public GreenMailSetUpBuilder withConfiguration(final ServerSetup... configuration) {
	this.configuration = configuration;
	return this;
    }

    public GreenMailSetUpBuilder withHooks(final Hooks hooks) {
	this.hooks = hooks;
	return this;
    }

    public GreenMailSetUpBuilder withUser(final String email, final String password) {
	this.users.add(new User(email, password));
	return this;
    }

    public GreenMailSetUpBuilder withUser(User user) {
	this.users.add(user);
	return this;
    }

    public GreenMailSetUpBuilder withUser(final String email, final String username, final String password) {
	this.users.add(new User(email, username, password));
	return this;
    }

}