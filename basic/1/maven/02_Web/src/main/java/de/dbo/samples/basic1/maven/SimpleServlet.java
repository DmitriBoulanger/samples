package de.dbo.samples.basic1.maven;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = -7032592647920933370L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	out.println("doGet(HttpServletRequest request, HttpServletResponse response): SimpleServlet from Samples Executed" );
        out.flush();
        out.close();
    }
}