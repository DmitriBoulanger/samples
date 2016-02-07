package de.dbo.samples.maven.basics.enterprise.command;

import de.dbo.samples.maven.basic.enterprise.weather.model.Location;
import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;
import de.dbo.samples.maven.basic.enterprise.weather.persist.LocationDAO;
import de.dbo.samples.maven.basic.enterprise.weather.persist.WeatherDAO;
import de.dbo.samples.maven.basic.enterprise.weather.service.WeatherFormatter;
import de.dbo.samples.maven.basic.enterprise.weather.service.WeatherService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Command {
	private static final Logger log = LoggerFactory.getLogger(Command.class);

	private WeatherService weatherService;
	private WeatherDAO weatherDAO;
	private LocationDAO locationDAO;

	private String zip;
	private String operation;;

	public Command(final String zip, final String operation) {
		this.zip = zip;
		this.operation = operation;
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
	
	public void run() throws Exception {
		@SuppressWarnings("resource")
		final ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[] { "classpath:applicationContext-weather.xml",
									   "classpath:applicationContext-persist.xml" });
			log.info("Service beans available");
			weatherService = (WeatherService) context.getBean("weatherService");
			locationDAO = (LocationDAO) context.getBean("locationDAO");
			weatherDAO = (WeatherDAO) context.getBean("weatherDAO");
			
			if( operation.equals("weather")) {
				getWeather();
			} else if (operation.equals("history"))  {
				getHistory();
			} else {
				throw new Exception("Operation " + operation + " unknown");
			}
	}
}
