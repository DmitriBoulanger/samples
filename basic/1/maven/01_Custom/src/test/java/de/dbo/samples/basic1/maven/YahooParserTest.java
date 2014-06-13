package de.dbo.samples.basic1.maven;

import de.dbo.samples.basic1.maven.Weather;
import de.dbo.samples.basic1.maven.YahooParser;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class YahooParserTest   {

	@Test
	public void testParser() throws Exception {
		InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse( nyData );
		assertEquals( "New York", weather.getCity() );
		assertEquals( "NY", weather.getRegion() );
		assertEquals( "US", weather.getCountry() );
		assertEquals( "39", weather.getTemp() );
		assertEquals( "Fair", weather.getCondition() );
		assertEquals( "39", weather.getChill() );
		assertEquals( "67", weather.getHumidity() );
	}
}