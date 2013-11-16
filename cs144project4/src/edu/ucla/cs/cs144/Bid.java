package edu.ucla.cs.cs144;

public class Bid
{
	private User m_user;
	private String m_time;
	private String m_amount;
	
	// m_userID
	public void setUser(User user)
	{
		m_user = user;
	}

	public User getUser()
	{
		return m_user;
	}

	// m_time
	public void setTime(String time)
	{
		m_time = time;
	}

	public String getTime()
	{
		return m_time;
	}

	// m_amount
	public void setAmount(String amount)
	{
		m_amount = amount;
	}

	public String getAmount()
	{
		return m_amount;
	}
}