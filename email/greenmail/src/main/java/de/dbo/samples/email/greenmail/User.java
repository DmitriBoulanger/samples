package de.dbo.samples.email.greenmail;

public final class User {

    public final String email;
    public final String login;
    public final String password;

    public User(final String email, final String password) {
	this(email, email, password);
    }

    public User(final String email, final String login, final String password) {
	this.email = email;
	this.login = login;
	this.password = password;
    }
    
    public String print() {
	return "EMail account: " + email +  " login=(" + login + "/"+ password + ")";
    }
    
}
