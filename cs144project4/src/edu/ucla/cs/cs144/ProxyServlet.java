package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet implements Servlet
{
	public ProxyServlet()
	{
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// your codes here
		String query, test;
		query = request.getParameter("q"); // query is null value if nothing is
											// passed (not "null")
		test = "http://google.com/complete/search?output=toolbar&q=" + EncodingUtil.encodeURIComponent(query);
		
		// URL u = new URL(test);
		// get a new http connection to google for suggest
		HttpURLConnection conn = (HttpURLConnection) new URL(test).openConnection();// u.openConnection();
		conn.setRequestMethod("GET");
		InputStream stream = conn.getInputStream();
		
		// converting the stream we're reading off to a inputstreamreader
		// (inputstream takes in byte by byte while inputstreamreader reads
		// line by line)
		BufferedReader buff = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		//read and return the response xml from google
		String temp;
		temp = buff.readLine();
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		while (temp != null)
		{
			out.println(temp);
			temp = buff.readLine();
		}
		conn.disconnect();
	}
}
