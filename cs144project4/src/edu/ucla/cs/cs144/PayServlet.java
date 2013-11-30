package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class PayServlet extends HttpServlet implements Servlet
{
	public PayServlet()
	{
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		
		// If user got to this URL through "hacking"
		if (session.getAttribute("itemID") == null ||
			session.getAttribute("itemName") == null ||
			session.getAttribute("buyPrice") == null)
		{
			String message = "It looks as if you tried to purchase an item without using a \"Buy Now\" link.";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
		else
		{						
			String url;
			if (!request.isSecure())
			{
				url = "https://" + request.getServerName();
				url += ":8443" + request.getContextPath() + "/purchase.jsp";
				response.sendRedirect(url);
			}
			else
			{
				request.getRequestDispatcher("/purchase.jsp").forward(request, response);
			}
		}
	}
}
