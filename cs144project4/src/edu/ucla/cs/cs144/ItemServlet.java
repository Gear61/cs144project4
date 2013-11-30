package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

public class ItemServlet extends HttpServlet implements Servlet
{

	public ItemServlet()
	{
	}

	static DocumentBuilder builder;
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        //AuctionSearchClient is the class given by sunflower that has project3 funcionality given to us
		AuctionSearchClient searcher = new AuctionSearchClient();
        //get the xml as a string
		String xml = searcher.getXMLDataForItemId(request.getParameter("id"));
        //if the passed id doesn't exist, fail gracefully
        if (xml == "") {
            request.setAttribute("id", request.getParameter("id"));
            request.getRequestDispatcher("/ItemError.jsp").forward(request, response);
        }
        //passing the actual page with item information
        else {
            //initialize all the classes that need to be used to hold the item information
            MonsterBean result = new MonsterBean();
            User r_user = new User();
            ArrayList <String> r_category = new ArrayList <String>();
            ArrayList <Bid> r_bid = new ArrayList <Bid>();
    		try
    		{
                //use project 2's xml parsing aux functions (refactoring yay)
    			Document doc = loadXMLFromString(xml);
    			Element item = (Element) doc.getFirstChild();
    			setMonsterItemID(result, item);
    			setMonsterName(result, item);
    	        setMonsterCategory(result, item);
    	        setMonsterCurrently(result, item);
    	        setMonsterBuyPrice(result, item);
    	        setMonsterFirstBid(result, item);
    	        setMonsterNumBids(result, item);
    	        setMonsterBids(result, item);
    	        setMonsterStarted(result, item);
    	        setMonsterEnds(result, item);
    	        setMonsterItemUser(result, item);
    	        setMonsterDesc(result, item);
    	        r_user = result.getUser();
    	        r_category = result.getCategoryList();
    	        r_bid = result.getBidList();
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}

            //set all the attributes from the bean to the .jsp sections
    		request.setAttribute("title", "Item Information for "+result.getItemID());
            
    		request.setAttribute("itemID", result.getItemID());
    		request.setAttribute("name", result.getName());
    		
    		String buyPriceFormatted = "";
    		if (!result.getBuyPrice().equals("NULL"))
    		{
    			// Set some HTTP session attributes so we don't have to spam sunflower
    			HttpSession session = request.getSession(true);
    			session.setAttribute("itemID", result.getItemID());
    			session.setAttribute("itemName", result.getName());
    			session.setAttribute("buyPrice", result.getBuyPrice());
    			
    			// Display buy price and link to buy the item
    			buyPriceFormatted += result.getBuyPrice();
    			buyPriceFormatted += "   <a href=\"./pay\">Buy Now</a>";
    		}
    		else
    		{
    			// Display N/A
    			buyPriceFormatted = "None";
    		}
    		
    		request.setAttribute("buy_price", buyPriceFormatted);
    		
            //buildCategoryHTML takes the category bean and spits out the list of categories in HTML form
    		request.setAttribute("category", buildCategoryHTML(r_category));
    		request.setAttribute("currently", result.getCurrently());
    		request.setAttribute("first_bid", result.getFirstBid());
    		request.setAttribute("num_bids", result.getNumBids());
    		
            //buildBidHTML does the same thing as category aux function but with arraylist of bid beans
    		request.setAttribute("bid", buildBidHTML(r_bid));
    		request.setAttribute("location", r_user.getLocation());
    		request.setAttribute("country", r_user.getCountry());
    		request.setAttribute("started", result.getStarted());
    		request.setAttribute("ends", result.getEnds());
    		request.setAttribute("sellerID", r_user.getUserID());
    		request.setAttribute("rating", r_user.getRating());
    		request.setAttribute("description", result.getDesc());

    		request.getRequestDispatcher("/ItemInfo.jsp").forward(request, response); 
        }
	}

	/**************************************************/
	//helper functions down here.
	/**************************************************/

    //takes the attributes of the bid arraylist and forms a html
	public static String buildBidHTML(ArrayList <Bid> bid_list) {
		String result = "<dl>";
		Bid bid = new Bid();
		for (int i = bid_list.size(); i > 0; --i) {
			bid = bid_list.get(i-1);
			result += "<dt>Bid #"+(i)+"</dt>";
			result += "<dd>User ID: "+bid.getUser().getUserID()+"</dd>";
			result += "<dd>Rating: "+bid.getUser().getRating()+"</dd>";
			result += "<dd>Location: "+bid.getUser().getLocation()+"</dd>";
			result += "<dd>Country: "+bid.getUser().getCountry()+"</dd>";
			result += "<dd>Time: "+bid.getTime()+"</dd>";
			result += "<dd>Amount: "+bid.getAmount()+"</dd>";
		}
		result += "</dl>";
		return result;
	}

    //takes the attributes of the category arraylist and forms a html
	public static String buildCategoryHTML(ArrayList <String> category_list) {
		String result = "";
		for (int i = 0; i < category_list.size(); ++i) {
			result += "<dd> - "+category_list.get(i)+"</dd>";
		}
		result += "</dl>";
		return result;
	}

	/**************************************************/
	//helper functions used in project 2. reused here
	/**************************************************/

    public static void setMonsterItemID(MonsterBean monster, Element Item)
    {
            Node nodeItemID;
            String m_itemID;
            nodeItemID = Item.getAttributeNode("ItemID"); //get the attribute named ItemID
            m_itemID = nodeItemID.getNodeValue();	//get the value of the attribute and typecast string to int
            monster.setItemID(m_itemID); //set the ItemID of the monsterbean to the value retreived
    }
    
    /*
     * sets the Name element in the monsterbean passed, to the name of the item that was passed
     */
    public static void setMonsterName(MonsterBean monster, Element Item)
    {
            String name;
            name = getElementTextByTagNameNR(Item, "Name"); //get the value under the 'name' tag
            monster.setName(name); //set the value retrieved from above function
            //System.out.println("Name: " + monster.getName()); //test print function to see if it worked correctly
    }
    
    /*
     * set the Category element of the monsterbean passed, to the categories of the item that was passed (note that category is another arraylist of strings)
     */
    public static void setMonsterCategory(MonsterBean monster, Element Item)
    {
            Element[] categories = getElementsByTagNameNR(Item, "Category"); //get an array of category element nodes
            String category;
            for (int i = 0; i < categories.length; ++i) //loop through the array of category element nodes
            {
                    category = getElementText(categories[i]); //get the text of the element
                    monster.appendCategory(category); //the set method of the category, we append here because it's an arraylist
            }
    }
    
    /*
     * set the Currently element of the monsterbean passed, to the currently of the item that was passed
     */
    public static void setMonsterCurrently(MonsterBean monster, Element Item)
    {
            String currently;
            currently = getElementTextByTagNameNR(Item, "Currently"); //get the value under the 'currently' tag
            monster.setCurrently(currently); //set the value retreived from above function
            //System.out.println("Currently: " + monster.getCurrently()); //test print function to see if it worked correctly
    }

    /*
     * set the Buy_Price element of the monsterbean passed, to the buy price of the item that was passed
     */
    public static void setMonsterBuyPrice(MonsterBean monster, Element Item)
    {
            String buyPrice;
            buyPrice = getElementTextByTagNameNR(Item, "Buy_Price"); //get the value under the 'buy_price' tag
            if (!buyPrice.equals(""))
            {
            	monster.setBuyPrice(buyPrice); //set the value retreived from the above function
            }
            //System.out.println("Buy Price: " + monster.getBuyPrice()); //test print function to see if it worked correctly
    }

    /*
     * set the First_Bid element of the monsterbean passed, to the first bid of the item that was passed
     */
    public static void setMonsterFirstBid(MonsterBean monster, Element Item)
    {
            String firstBid;
            firstBid = getElementTextByTagNameNR(Item, "First_Bid"); //get the value under the 'first_bid' tag
            monster.setFirstBid(firstBid); //set the value retreived from the above function
            //System.out.println("First Bid: " + monster.getFirstBid()); //test print function to see if it worked correctly
    }

    /*
     * set the Number_of_Bids element of the monsterbean passed, to the number of bids of the item that was passed
     */
    public static void setMonsterNumBids(MonsterBean monster, Element Item)
    {
            String s_numBids;
            s_numBids = getElementTextByTagNameNR(Item, "Number_of_Bids"); //get the value under the 'number_of_bids' tag
            monster.setNumBids(s_numBids); //set the value retreived from the above two functions
    }
    
    /*
     * Gets all the bids and extract info for it. Implement same way as the items iterate through itself
     */
    public static void setMonsterBids(MonsterBean monster, Element Item)
    {
            //Array of elements that contains the element nodes for all the "Bid" tags
    		Element findBids = getElementByTagNameNR(Item, "Bids");
            Element[] bids = getElementsByTagNameNR(findBids, "Bid");
            Bid monsterBid;
            for(int i = 0; i < bids.length; ++i)
            {
            	monsterBid = new Bid();
            	setMonsterBidUser(monsterBid, bids[i]);
            	setMonsterBidTime(monsterBid, bids[i]);
            	setMonsterBidAmount(monsterBid, bids[i]);
            	monster.appendBid(monsterBid);
            }
    }
    
    /*
     * set the UserID attribute of the bidder in the monsterbean passed
     */
    public static void setMonsterBidUser(Bid bidder, Element bid)
    {
    	User monsterBidUser = new User();
    	Element elementBidUser = getElementByTagNameNR(bid, "Bidder");	//bidder tag, holds attributes for UserID and rating, userid and rating setter function wants element node that holds these attributes or BOOOOOOM
    	setMonsterUserID(monsterBidUser, elementBidUser);				//pass the elementBidUser since it's at the correct element node position
    	setMonsterRating(monsterBidUser, elementBidUser);				//same for this one. whoo
    	setMonsterLocation(monsterBidUser, elementBidUser);				//and dis
    	setMonsterCountry(monsterBidUser, elementBidUser);				//and thiiiiiiiiiiiiiis
    	bidder.setUser(monsterBidUser);
    }
    
    /******************************************************/
    /*
     * user aux functions(methods.. booo java boooo)
     * use for both item owner and bidder
     */
    //attribute values assum the element node passed is on the tag holding the attributes
    public static void setMonsterUserID(User user, Element node)
    {
    	Node nodeUserID;
    	String m_userID;
    	nodeUserID = node.getAttributeNode("UserID");			//get the attribute node under 'UserID'
    	m_userID = nodeUserID.getNodeValue();					//get node value
    	user.setUserID(m_userID);								//use setter function. yay
    }

    //attribute values assum the element node passed is on the tag holding the attributes
    public static void setMonsterRating(User user, Element node)
    {
    	Node nodeRating;
    	String m_rating;
    	nodeRating = node.getAttributeNode("Rating");			//get the attribute node under 'Rating'
    	m_rating = nodeRating.getNodeValue();	//get node value
    	user.setRating(m_rating);								//use setter functiooooon
    }

    //find and set the location ele value
    public static void setMonsterLocation(User user, Element node)
    {
    	//rinse and repeat
    	String location;
    	location = getElementTextByTagNameNR(node, "Location");
    	if (!location.equals(""))
    	{
    		user.setLocation(location);
    	}
    	//System.out.println(user.getLocation());
    }

    //find and set the country ele value
    public static void setMonsterCountry(User user, Element node)
    {
    	//and repeat again
    	String country;
    	country = getElementTextByTagNameNR(node, "Country");
    	if (!country.equals(""))
    	{
    		user.setCountry(country);
    	}
    	//System.out.println(user.getCountry());
    }
    /*
     * end of user aux functions
     */
    /*********************************************************/
    
    /*
     * set the time element of the bid element into the bid bean passed
     */
     
    public static void setMonsterBidTime(Bid bidder, Element bid)
    {
    	//the same deal going on here as above
    	String time;
    	time = getElementTextByTagNameNR(bid, "Time");
    	bidder.setTime(time);
    }
    
    /*
     * set the amount element of the bid element into the bid bean passed
     */
    public static void setMonsterBidAmount(Bid bidder, Element bid)
    {
    	//the same deal going on here as above
    	String amount;
    	amount = getElementTextByTagNameNR(bid, "Amount");
    	bidder.setAmount(amount);
    }

    /*
     * setting the item seller user
     */
    public static void setMonsterItemUser(MonsterBean monster, Element Item)
    {
    	User monsterUser = new User();
    	Element elementItemUser = getElementByTagNameNR(Item, "Seller");	//find the Seller ele tag because userid and rating set functions assume element node is in the position holding the attr nodes
    	setMonsterUserID(monsterUser, elementItemUser);		//assign userid attr value
    	setMonsterRating(monsterUser, elementItemUser);		//assign rating attr value
    	setMonsterLocation(monsterUser, Item);				//assign location ele value
    	setMonsterCountry(monsterUser, Item);				//assign country ele value
    	monster.setUser(monsterUser);		//set to the big ole monsta
    }
    
    /*
     * set the started element of the monsterbean passed, to the started of the item that was passed
     */
    public static void setMonsterStarted(MonsterBean monster, Element Item)
    {
            String started;
            started = getElementTextByTagNameNR(Item, "Started"); //get the value under the 'started' tag
            monster.setStarted(started); //set the value retreived from the above function
            //System.out.println("Started: " + monster.getStarted()); //test print function to see if it worked correctly
    }
    
    /*
     * set the ends element of the monsterbean passed, to the ends of the item that was passed
     */
    public static void setMonsterEnds(MonsterBean monster, Element Item)
    {
            String ends;
            ends = getElementTextByTagNameNR(Item, "Ends"); //get the value under the 'ends' tag
            monster.setEnds(ends); //set the value retreived from the above function
            //System.out.println("Ends: " + monster.getEnds()); //test print function to see if it worked correctly
    }
    
    /*
     * set the description element of the monsterbean passed, to the description of the item that was passed
     */
    public static void setMonsterDesc(MonsterBean monster, Element Item)
    {
            String desc;
            desc = getElementTextByTagNameNR(Item, "Description"); //get the value under the 'description' tag
            monster.setDesc(desc); //set the value retreived from the above function
            //System.out.println("Desc: " + monster.getDesc()); //test print function to see if it worked correctly
    }

    /****************************************************/

    static Element[] getElementsByTagNameNR(Element e, String tagName)
	{
		Vector<Element> elements = new Vector<Element>();
		Node child = e.getFirstChild();
		while (child != null)
		{
			if (child instanceof Element && child.getNodeName().equals(tagName))
			{
				elements.add((Element) child);
			}
			child = child.getNextSibling();
		}
		Element[] result = new Element[elements.size()];
		elements.copyInto(result);
		return result;
	}

	/*
	 * Returns the first subelement of e matching the given tagName, or null if
	 * one does not exist. NR means Non-Recursive.
	 */
	static Element getElementByTagNameNR(Element e, String tagName)
	{
		Node child = e.getFirstChild();
		while (child != null)
		{
			if (child instanceof Element && child.getNodeName().equals(tagName))
				return (Element) child;
			child = child.getNextSibling();
		}
		return null;
	}

	/*
	 * Returns the text associated with the given element (which must have type
	 * #PCDATA) as child, or "" if it contains no text.
	 */
	static String getElementText(Element e)
	{
		if (e.getChildNodes().getLength() == 1)
		{
			Text elementText = (Text) e.getFirstChild();
			return elementText.getNodeValue();
		}
		else
			return "";
	}

	/*
	 * Returns the text (#PCDATA) associated with the first subelement X of e
	 * with the given tagName. If no such X exists or X contains no text, "" is
	 * returned. NR means Non-Recursive.
	 */
	static String getElementTextByTagNameNR(Element e, String tagName)
	{
		Element elem = getElementByTagNameNR(e, tagName);
		if (elem != null)
			return getElementText(elem);
		else
			return "";
	}

}
