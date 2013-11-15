package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ItemServlet extends HttpServlet implements Servlet
{

	public ItemServlet()
	{
	}

	static final String columnSeparator = "|*|";
	static DocumentBuilder builder;

	static final String[] typeName =
	{ "none", "Element", "Attr", "Text", "CDATA", "EntityRef", "Entity", "ProcInstr", "Comment", "Document", "DocType",
			"DocFragment", "Notation", };
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AuctionSearchClient searcher = new AuctionSearchClient();
		String xml = searcher.getXMLDataForItemId("1043374545");
		try
		{
			Document doc = loadXMLFromString(searcher.getXMLDataForItemId("1043374545"));
		}
		catch (Exception e)
		{
			xml = "WE DUN GOOFED.";
			e.printStackTrace();
		}
		
		request.setAttribute("query", "Random");
		request.setAttribute("results", xml);
		request.getRequestDispatcher("/basicSearch.jsp").forward(request, response);
	}
}
