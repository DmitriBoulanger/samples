package de.dbo.samples.basic1.maven.multiproject.weather;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;

import de.dbo.samples.basic1.maven.multiproject.weather.Weather;
import de.dbo.samples.basic1.maven.multiproject.weather.YahooParser;

public class YahooParserTest extends TestCase {

	public YahooParserTest(String name) {
		super(name);
	}
	
	@Test
	public void testParser() throws Exception {
		InputStream nyData = 
			getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
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
