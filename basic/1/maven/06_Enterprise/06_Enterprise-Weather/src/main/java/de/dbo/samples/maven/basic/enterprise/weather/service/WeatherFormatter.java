package de.dbo.samples.maven.basic.enterprise.weather.service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.*;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import de.dbo.samples.maven.basic.enterprise.weather.model.Location;
import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;

public class WeatherFormatter {

	private static Logger log = LoggerFactory.getLogger(WeatherFormatter.class);

	public String formatWeather( Weather weather ) throws Exception {
		log.info( "Formatting Weather Data: " + weather + " ... " );
		final Reader reader = new InputStreamReader( getClass().getClassLoader().getResourceAsStream("weather.vm"));
		final VelocityContext context = new VelocityContext();
		context.put("weather", weather );
		final StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "", reader);
		log.info( "Formatting Weather Data done" );
		return writer.toString();		
	}

	public String formatHistory( Location location, List<Weather> weathers ) throws Exception {
		log.info( "Formatting History Data: " + location + " ... " );
		final Reader reader = new InputStreamReader( getClass().getClassLoader().getResourceAsStream("history.vm"));
		final VelocityContext context = new VelocityContext();
		context.put("location", location );
		context.put("weathers", weathers );
		final StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "", reader);
		log.info( "Formatting History Data done" );
		return writer.toString();		
	}
}
