package de.dbo.samples.maven.multiproject.weather.yahoo;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import de.dbo.samples.maven.multiproject.weather.Weather;
import de.dbo.samples.maven.multiproject.weather.WeatherFormatter;
import de.dbo.samples.maven.multiproject.weather.YahooParser;

public class WeatherFormatterTest {

	@Test
	public void testFormat() throws Exception {
		InputStream nyData = 
			getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse( nyData );
		String formattedResult = new WeatherFormatter().format( weather );
		InputStream expected = 
			getClass().getClassLoader().getResourceAsStream("format-expected.dat");
		assertEquals( IOUtils.toString( expected ), formattedResult );
	}
}
