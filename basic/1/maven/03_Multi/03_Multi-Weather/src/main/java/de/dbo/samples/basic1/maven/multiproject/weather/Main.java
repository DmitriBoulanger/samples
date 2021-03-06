package de.dbo.samples.basic1.maven.multiproject.weather;

import java.io.InputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		// Read the Zip Code from the Command-line (if none supplied, use 60202)
		String zipcode = "60202";
        try {
		  zipcode = args[0];
        } catch( Exception e ) {}

		// Start the program
		new Main(zipcode).start();
	}

	private String zip;

	public Main(String zip) {
		this.zip = zip;
	}
	
	public void start() throws Exception {
		// Retrieve Data
		InputStream dataIn = new YahooRetriever().retrieve( zip );

		// Parse Data
		Weather weather = new YahooParser().parse( dataIn );

		// Format (Print) Data
		System.out.print( new WeatherFormatter().format( weather ) );
	}

}
