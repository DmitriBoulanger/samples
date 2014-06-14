package de.dbo.samples.basic1.maven.simple;

import de.dbo.samples.basic1.maven.simple.Weather;
import de.dbo.samples.basic1.maven.simple.WeatherFormatter;
import de.dbo.samples.basic1.maven.simple.YahooParser;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class WeatherFormatterTest {

	@Test
	public void testFormat() throws Exception {
		InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse(nyData);
		String formattedResult = new WeatherFormatter().format(weather);
		InputStream expected = getClass().getClassLoader().getResourceAsStream("format-expected.dat");
		assertEquals(IOUtils.toString(expected), formattedResult);
	}
}
