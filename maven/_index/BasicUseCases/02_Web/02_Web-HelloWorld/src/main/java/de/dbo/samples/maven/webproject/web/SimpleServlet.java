package de.dbo.samples.maven.webproject.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = -7032592647920933370L;

	public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	out.println( "SimpleServlet Executed" );
        out.flush();
        out.close();
    }
}