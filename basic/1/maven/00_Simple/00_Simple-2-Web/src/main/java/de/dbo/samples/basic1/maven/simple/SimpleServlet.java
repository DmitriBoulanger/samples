package de.dbo.samples.basic1.maven.simple;

import static de.dbo.tools.utils.print.Profiler.elapsed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = -7032592647920933370L;
	private static final Logger log = LoggerFactory.getLogger(SimpleServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final long start = System.currentTimeMillis();
		PrintWriter out = response.getWriter();
		out.println("doGet(HttpServletRequest request, HttpServletResponse response): SimpleServlet from Samples Executed");
		out.flush();
		out.close();
		out = null;
		log.info("doGet-request done." + elapsed(start));
	}
	
}