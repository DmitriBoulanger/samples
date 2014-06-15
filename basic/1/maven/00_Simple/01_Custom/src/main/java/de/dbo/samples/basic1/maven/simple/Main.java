package de.dbo.samples.basic1.maven.simple;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		
		final long start = System.currentTimeMillis();
		

		// Read the Zip Code from the Command-line (if none supplied, use 60202)
		String zipcode = "02101";
        try {
		  zipcode = args[0];
        } catch( Exception e ) {}

		// Start the program
		new Main(zipcode).start(start);
	}

	private String zip;

	public Main(String zip) {
		this.zip = zip;
	}
	
	public void start(final long start) throws Exception {
		// Retrieve Data
		InputStream dataIn = new YahooRetriever().retrieve( zip );

		// Parse Data
		Weather weather = new YahooParser().parse( dataIn );

		// Format (Print) Data
		final String data = new WeatherFormatter().format( weather );
		log.info("Elaspsed " + (System.currentTimeMillis()-start) + " ms."
				+":\n" + data );
	}

}
