package de.dbo.samples.basic1.maven.multiproject.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Default trivial servlet.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = -5506915922932424758L;

	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
        throws ServletException, IOException {
	final PrintWriter out = response.getWriter();
	out.println( "SimpleServlet executed" );
        out.flush();
        out.close();
    }
}