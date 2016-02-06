package de.dbo.samples.email.greenmail.main;

import de.dbo.samples.email.greenmail.Users;

import com.icegreen.greenmail.util.GreenMailUtil;

/**
 * Sends EMail to the (running!) standalone GreenMail server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class GreenMail_1_Send implements Users {

    public static final void main(final String[] args) throws Throwable {
	GreenMailUtil.sendTextEmail(USER2.email/* to */
		, USER1.email/* from */
		, "Standalone test"
		, "It worked"
		,SERVER_SETUP_TO_SEND);
    }
}
