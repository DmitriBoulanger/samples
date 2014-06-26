package org.sonatype.mavenbook.weather.yahoo;

import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import static org.apache.commons.io.IOUtils.toByteArray;
import junit.framework.TestCase;

import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;
import de.dbo.samples.maven.basic.enterprise.weather.service.WeatherFormatter;
import de.dbo.samples.maven.basic.enterprise.weather.service.YahooParser;

public class WeatherFormatterTest extends TestCase {

	public WeatherFormatterTest(String name) {
		super(name);
	}
	
	@Test
	public void testWeatherFormatter() throws Exception {
		InputStream nyData = 
				getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse( "10002", nyData );
		String weatherFormatted =  new WeatherFormatter().formatWeather( weather ); 
		
		
		
		InputStream nyDataFomatted = 
				getClass().getClassLoader().getResourceAsStream("format-expected.dat");
		
		assertNotNull(nyDataFomatted);
		
		String weatherFormattedExpected = new String(toByteArray(nyDataFomatted));
		System.out.println(weatherFormatted);
		System.out.println(weatherFormattedExpected);
		
		assertTrue("Not the same:"
				+ "\n EXPECTED:\n" + weatherFormattedExpected
				+ "\nACTUAL:\n" + weatherFormatted
				,weatherFormatted.equals(weatherFormattedExpected) );
	}
	

}
