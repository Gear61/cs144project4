package edu.ucla.cs.cs144;

public class User
{
	private String m_userID;
	private String m_rating;
	private String m_location = "NULL";
	private String m_country = "NULL";

	// m_userID
	public void setUserID(String userID)
	{
		m_userID = userID;
	}

	public String getUserID()
	{
		return m_userID;
	}

	// m_rating
	public void setRating(String rating)
	{
		m_rating = rating;
	}

	public String getRating()
	{
		return m_rating;
	}

	// m_location
	public void setLocation(String location)
	{
		m_location = location;
	}

	public String getLocation()
	{
		return m_location;
	}

	// m_country
	public void setCountry(String country)
	{
		m_country = country;
	}

	public String getCountry()
	{
		return m_country;
	}
}
