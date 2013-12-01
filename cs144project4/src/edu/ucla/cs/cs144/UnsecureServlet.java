package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UnsecureServlet extends HttpServlet implements Servlet
{
	public UnsecureServlet()
	{
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String url;
		if (request.isSecure())
		{
			url = "http://" + request.getServerName();
			url += ":8080" + request.getContextPath();
			response.sendRedirect(url);
		}
		// No need to switch if it's already https
		else
		{
			request.getRequestDispatcher("./").forward(request, response);
		}
	}
}
