package de.dbo.samples.maven.basics.enterprise.command;

import java.util.List;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.maven.basic.enterprise.weather.model.Location;
import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;
import de.dbo.samples.maven.basic.enterprise.weather.persist.LocationDAO;
import de.dbo.samples.maven.basic.enterprise.weather.persist.WeatherDAO;
import de.dbo.samples.maven.basic.enterprise.weather.service.WeatherFormatter;
import de.dbo.samples.maven.basic.enterprise.weather.service.WeatherService;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	private WeatherService weatherService;
	private WeatherDAO weatherDAO;
	private LocationDAO locationDAO;
	
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

		// Start the program
		Main main = new Main(zipcode);

		final ApplicationContext context = 
			new ClassPathXmlApplicationContext(
					new String[] { "classpath:applicationContext-weather.xml",
								   "classpath:applicationContext-persist.xml" });
		main.weatherService = (WeatherService) context.getBean("weatherService");
		main.locationDAO = (LocationDAO) context.getBean("locationDAO");
		main.weatherDAO = (WeatherDAO) context.getBean("weatherDAO");
		log.info("Service beans created");
		
		if( operation.equals("weather")) {
			main.getWeather();
		} else {
			main.getHistory();
		}
	}

	private String zip;

	public Main(String zip) {
		this.zip = zip;
	}

	public void getWeather() throws Exception {
		final Weather weather = weatherService.retrieveForecast(zip);
		weatherDAO.save( weather );
		log.info("\n" + new WeatherFormatter().formatWeather(weather));
	}

	public void getHistory() throws Exception {
		final Location location = locationDAO.findByZip(zip);
		List<Weather> weathers = weatherDAO.recentForLocation(location);
		log.info("\n" + new WeatherFormatter().formatHistory(location, weathers));
	}
}
