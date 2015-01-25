package de.dbo.samples.spring.javadependencyinjection.legacy;

/* 
 * Bad solution because uses the the exact instance of the service
 */

public class Consumer {

	private Service email = new Service();
	
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.email.sendEmail(msg, rec);
	}
}
