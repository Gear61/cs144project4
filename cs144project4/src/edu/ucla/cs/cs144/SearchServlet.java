package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchServlet extends HttpServlet implements Servlet
{

	public SearchServlet()
	{
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Grab query
		String query = request.getParameter("q");
		int numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
		int numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));

		AuctionSearchClient searcher = new AuctionSearchClient();
		SearchResult[] results = searcher.basicSearch(query, numResultsToSkip, numResultsToReturn);

		try
		{
			String searchResults = "";

			for (int i = 0; i < results.length; i++)
			{
				searchResults += "<a href=\"./item?id=" + results[i].getItemId() + "\">" + "Item " + results[i].getItemId() + "</a>";
				searchResults += ": " + results[i].getName() + "<br>";
			}
			
			request.setAttribute("query", query);
			request.setAttribute("results", searchResults);
			request.getRequestDispatcher("/basicSearch.jsp").forward(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
