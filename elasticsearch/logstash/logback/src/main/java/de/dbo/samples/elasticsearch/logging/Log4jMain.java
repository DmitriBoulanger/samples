package de.dbo.samples.elasticsearch.logging;

import java.util.UUID;

import org.slf4j.*;

public class Log4jMain {
	private static final Logger log = LoggerFactory.getLogger(Log4jMain.class);
	private static final Logger log2 = LoggerFactory.getLogger("AnotherLogger");
	private static final Logger log3 = LoggerFactory.getLogger("java.x.bla.bla.XLoger");
	private static final Logger log4 = LoggerFactory.getLogger("java.x.bla.bla.XXXXLoger");

	public static final void main(final String[] args) throws InterruptedException {
		while(true) {
		log.info("Info: Hallo!"
				+ "\n\t - again hello"
				+ "\n\t - again hello"
				+ "\n\t - again hello"
				+ "\n\t - again hello"
				+ "\n\t - again hello"
				+ "\n\t - again hello"
				+ "\n\t - again hello"
				
				);
		log.warn("Warning: Hallo!");
		log3.error("Error: Hallo!", new Exception("test"));
		log2.warn("SOS!",new Exception("test"));
		
		try {
			Class.forName("hashhha");
		} catch (ClassNotFoundException e) {
			
			log4.error("no class " + UUID.randomUUID().toString(), e);
		}
		
			Thread.sleep(2000);
		}
	}
}
