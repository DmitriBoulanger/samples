package de.dbo.samples.email.greenmail.main;

import de.dbo.samples.email.greenmail.Users;

import com.icegreen.greenmail.util.GreenMailUtil;

public class GreenMail_1_Send implements Users {

    public static final void main(final String[] args) throws Throwable {
	GreenMailUtil.sendTextEmail(USER2.email/* to */
		, USER1.email/* from */
		, "Standalone test"
		, "It worked"
		,SERVER_SETUP_TO_SEND);
    }
}
