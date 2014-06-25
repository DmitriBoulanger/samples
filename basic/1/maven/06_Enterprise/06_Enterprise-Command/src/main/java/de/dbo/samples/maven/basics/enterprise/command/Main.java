package de.dbo.samples.maven.basics.enterprise.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		
		// Read the Zip Code from the Command-line (if none supplied, use 60202)
		String zipcode = "60101";
		try {
			zipcode = args[0];
		} catch (Exception e) {
			log.info("No args: " + e.toString());
		}
		log.info("zipcode=" + zipcode);

		// Read the Operation from the Command-line (if none supplied, use weather)
		String operation = "weather";
		try {
			operation = args[1];
		} catch (Exception e) {
			log.info("No operation (second) arg: " + e.toString());
		}
		log.info("operation=" + operation);
		
		new Command(zipcode,"weather").run();
		new Command(zipcode,"history").run();
	}

}
