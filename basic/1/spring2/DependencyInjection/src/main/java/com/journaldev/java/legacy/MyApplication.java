package com.journaldev.java.legacy;

/*
 * Bad solution because uses the the exact instance of the service
 */

public class MyApplication {

	private EmailService email = new EmailService();
	
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.email.sendEmail(msg, rec);
	}
}
