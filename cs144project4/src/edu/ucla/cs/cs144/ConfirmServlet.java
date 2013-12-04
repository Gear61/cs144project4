package edu.ucla.cs.cs144;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ConfirmServlet extends HttpServlet implements Servlet
{
	public ConfirmServlet()
	{
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);

		// If user got to this URL through "hacking"
		/*if (session.getAttribute("itemID") == null || session.getAttribute("itemName") == null
				|| session.getAttribute("buyPrice") == null)
		{
			String message = "It looks as if you tried going to the purchase confirmation page without "
					+ "going through the Item Purchase page.";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
		else
		{*/
			// Get credit card number and current time
			String creditCardNumber = request.getParameter("creditCardNumber");
			String timeOfPurchase = new SimpleDateFormat("MMM-dd-yyyy h:mm aa").format(Calendar.getInstance().getTime());
			String purchaseID = request.getParameter("purchaseID");
			
			// Associate them with session
			session.setAttribute("creditCardNumber", creditCardNumber);
			session.setAttribute("timeOfPurchase", timeOfPurchase);
			
			String url;
			if (request.isSecure())
			{
				url = "http://" + request.getServerName();
				url += ":8080" + request.getContextPath() + "/confirm.jsp?id=" + purchaseID;
				response.sendRedirect(url);
			}
			else
			{
				request.getRequestDispatcher("/confirm.jsp?id=" + purchaseID).forward(request, response);
			}
		//}
	}
}
