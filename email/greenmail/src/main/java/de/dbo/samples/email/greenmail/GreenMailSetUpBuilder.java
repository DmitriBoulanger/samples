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
     * Builder for the default configuration for a SMTP/IMAP server with imap:143 and smtp:25.
     * This configuration has no user accounts
     */
    public GreenMailSetUpBuilder() {
	configuration = ServerSetup.SMTP_IMAP;
	users = new HashSet<User>();
	hooks = NULL_HOOKS;
    }

    /**
     * @return set-up to be used
     */
    public GreenMailSetUp build() {
	return new GreenMailSetUp(this);
    }

    /**
     * Set-up Builder for the default TEST-configuration for a SMTP/IMAP server with imap:3143 and smtp:3025.
     */
    public GreenMailSetUpBuilder withDeaultTestConfiguration() {
	this.configuration = ServerSetupTest.SMTP_IMAP;
	return this;
    }

    /**
     * Set-up Builder with a single set-up configuration.
     * @param configuration
     * @return set-up builder
     */
    public GreenMailSetUpBuilder withConfiguration(final ServerSetup configuration) {
	this.configuration = new ServerSetup[] { configuration };
	return this;
    }

    /**
     * Set-up Builder with several set-ups.
     * @param configuration
     * @return set-up builder
     */
    public GreenMailSetUpBuilder withConfiguration(final ServerSetup... configuration) {
	this.configuration = configuration;
	return this;
    }

    /**
     * @param hooks to be inserted into the set-up
     * @return set-up builder
     */
    public GreenMailSetUpBuilder withHooks(final Hooks hooks) {
	this.hooks = hooks;
	return this;
    }
    
    /**
     * inserts user account into the set-up
     * @param user
     * @return set-up builder
     */
    public GreenMailSetUpBuilder withUser(final User user) {
	this.users.add(user);
	return this;
    }

    /**
     * inserts user account into the set-up.
     * EMail address is the user login-name
     * 
     * @param email 
     * @param password
     * @return set-up builder
     */
    public GreenMailSetUpBuilder withUser(final String email, final String password) {
	this.users.add(new User(email, password));
	return this;
    }

    /**
     * insert user account into the set-up
     * @param email
     * @param username
     * @param password
     * @return set-up builder
     */
    public GreenMailSetUpBuilder withUser(final String email, final String username, final String password) {
	this.users.add(new User(email, username, password));
	return this;
    }
}