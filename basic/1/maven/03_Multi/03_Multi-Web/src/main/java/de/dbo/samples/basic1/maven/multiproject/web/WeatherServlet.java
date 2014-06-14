package de.dbo.samples.basic1.maven.multiproject.web;

import de.dbo.samples.basic1.maven.multiproject.weather.WeatherService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 5835859063740243337L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	String zip = request.getParameter("zip" );
	WeatherService weatherService = new WeatherService();
	PrintWriter out = response.getWriter();
        try {
	    out.println( weatherService.retrieveForecast( zip ) );
	} catch( Exception e ) {
	    out.println( "Error Retrieving Forecast: " + e.getMessage() );
	}
        out.flush();
        out.close();
    }
}