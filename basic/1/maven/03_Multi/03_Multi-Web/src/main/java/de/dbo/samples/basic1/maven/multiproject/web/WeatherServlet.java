package de.dbo.samples.basic1.maven.multiproject.web;

import de.dbo.samples.basic1.maven.multiproject.weather.WeatherService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deployment as servlet of the Weather-service.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 5835859063740243337L;

	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String zip = request.getParameter("zip");
		final WeatherService weatherService = new WeatherService();
		final PrintWriter out = response.getWriter();
		try {
			out.println(weatherService.retrieveForecast(zip));
		} catch (final Exception e) {
			out.println("Error Retrieving Forecast: " + e.getMessage());
		}
		out.flush();
		out.close();
	}
}