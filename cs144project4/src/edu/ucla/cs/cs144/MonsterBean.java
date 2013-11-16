package edu.ucla.cs.cs144;

import java.util.ArrayList;

// A data structure meant to contain all information associated with an item
// Is later broken down into the beans corresponding to tables
public class MonsterBean
{
	private String m_itemID;
	private String m_name;
	private ArrayList <String> m_categoryList = new ArrayList <String>();
	private String m_currently;
	private String m_buyPrice = "NULL";	//initialized to NULL because it's optional (might not exist, ? in DTD)
	private String m_firstBid;
	private String m_numBids;		//initialized to zero because any item starts with 0 bids.
	private ArrayList <Bid> m_bidList = new ArrayList <Bid>();
	private String m_started;
	private String m_ends;
	private User m_user;
	private String m_desc;
	
	// m_itemID
	public void setItemID(String itemID)
	{
		m_itemID = itemID;
	}
	public String getItemID()
	{
		return m_itemID;
	}
	
	// m_name
	public void setName(String name)
	{
		m_name = name;
	}
	public String getName()
	{
		return m_name;
	}
	
	// m_categoryList
	public void appendCategory(String cat)
	{
		m_categoryList.add(cat);
	}
	public ArrayList <String> getCategoryList()
	{
		return m_categoryList;
	}
	
	// m_currently
	public void setCurrently(String currently)
	{
		m_currently = currently;
	}
	public String getCurrently()
	{
		return m_currently;
	}
	
	// m_buyPrice
	public void setBuyPrice(String buyPrice)
	{
		m_buyPrice = buyPrice;
	}
	public String getBuyPrice()
	{
		return m_buyPrice;
	}
	
	// m_firstBid
	public void setFirstBid(String firstBid)
	{
		m_firstBid = firstBid;
	}
	public String getFirstBid()
	{
		return m_firstBid;
	}
	
	// m_numBids
	public void setNumBids(String numBids)
	{
		m_numBids = numBids;
	}
	public String getNumBids()
	{
		return m_numBids;
	}
	
	// m_bidList
	public void appendBid(Bid newBid)
	{
		m_bidList.add(newBid);
	}
	public ArrayList <Bid> getBidList()
	{
		return m_bidList;
	}
	
	// m_started
	public void setStarted(String started)
	{
		m_started = started;
	}
	public String getStarted()
	{
		return m_started;
	}
	
	// m_ends
	public void setEnds(String ends)
	{
		m_ends = ends;
	}
	public String getEnds()
	{
		return m_ends;
	}
	
	// m_user
	public void setUser(User user)
	{
		m_user = user;
	}
	public User getUser()
	{
		return m_user;
	}
	
	// m_desc
	public void setDesc(String desc)
	{
		m_desc = desc;
	}
	public String getDesc()
	{
		return m_desc;
	}
}
