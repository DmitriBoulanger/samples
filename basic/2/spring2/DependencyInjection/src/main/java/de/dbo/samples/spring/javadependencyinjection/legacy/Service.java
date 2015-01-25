package de.dbo.samples.spring.javadependencyinjection.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * Bad solution because it has no interface
 * and, therefore, cannot be used in the abstract implementation-independent form
 */

public class Service {
	private static final Logger log = LoggerFactory.getLogger(Service.class);

	public void sendEmail(String message, String receiver){
		//logic to send email
		log.info("Email sent to "+receiver+ " with Message="+message);
	}
}
