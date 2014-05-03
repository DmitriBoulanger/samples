package de.dbo.samples.elk.logstash;

import java.util.UUID;

import org.slf4j.*;

public class ESLogsatshMessages {
	
	private static final Logger log = LoggerFactory.getLogger(ESLogsatshMessages.class);
	private static final Logger log2 = LoggerFactory.getLogger("AnotherLogger");
	private static final Logger log3 = LoggerFactory.getLogger("java.x.bla.bla.XLoger");
	private static final Logger log4 = LoggerFactory.getLogger("AnotherLoggerX");

	public static final void main(final String[] args) throws InterruptedException {
		while(true) {
		log2.warn("Info: Hallo!"
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
