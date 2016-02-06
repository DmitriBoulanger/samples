package de.dbo.samples.email.greenmail;

import java.util.HashSet;
import java.util.Set;

import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

public final class GreenMailServerBuilder { 

	 protected ServerSetup[] configuration;
	 protected Hooks hooks;
	 protected final Set<User> users;

	public GreenMailServerBuilder() {
		configuration = ServerSetupTest.SMTP_IMAP;
		hooks = GreenMailServerSetUp.NULL_HOOKS;
		users = new HashSet<User>();
	}

	public GreenMailServerSetUp build() {
		return new GreenMailServerSetUp(this);
	}

	public GreenMailServerBuilder withConfiguration(final ServerSetup configuration) {
		this.configuration = new ServerSetup[] { configuration };
		return this;
	}

	public GreenMailServerBuilder withConfiguration(final ServerSetup... configuration) {
		this.configuration = configuration;
		return this;
	}

	public GreenMailServerBuilder withHooks(final Hooks hooks) {
		this.hooks = hooks;
		return this;
	}

	public GreenMailServerBuilder withUser(final String email, final String password) {
		this.users.add(new User(email, password));
		return this;
	}

	public GreenMailServerBuilder withUser(final String email, final String username, final String password) {
		this.users.add(new User(email, username, password));
		return this;
	}

}