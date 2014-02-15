package de.dbo.samples.maven.basic.enterprise.weather.yahoo;

import java.io.InputStream;

import junit.framework.TestCase;
import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;
import de.dbo.samples.maven.basic.enterprise.weather.service.YahooParser;

public class YahooParserTest extends TestCase {

	public YahooParserTest(String name) {
		super(name);
	}
	
	public void testParser() throws Exception {
		InputStream nyData = 
			getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse( "10002", nyData );
		assertEquals( "New York", weather.getLocation().getCity() );
		assertEquals( "NY", weather.getLocation().getRegion() );
		assertEquals( "US", weather.getLocation().getCountry() );
		assertEquals( "39", weather.getCondition().getTemp() );
		assertEquals( "Fair", weather.getCondition().getText() );
		assertEquals( "39", weather.getWind().getChill() );
		assertEquals( "67", weather.getAtmosphere().getHumidity() );
	}
}
