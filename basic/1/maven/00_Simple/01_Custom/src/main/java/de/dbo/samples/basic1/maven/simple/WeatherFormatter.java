package de.dbo.samples.basic1.maven.simple;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.slf4j.*;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class WeatherFormatter {

	private static Logger log = LoggerFactory.getLogger(WeatherFormatter.class);

	public String format( Weather weather ) throws Exception {
		log.info( "Formatting Weather Data" );
		Reader reader = new InputStreamReader( getClass().getClassLoader().getResourceAsStream("output.vm"));
		VelocityContext context = new VelocityContext();
		context.put("weather", weather );
		StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "", reader);
		return writer.toString();		
	}
}
