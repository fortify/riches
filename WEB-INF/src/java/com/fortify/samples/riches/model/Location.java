package com.fortify.samples.riches.model;

public class Location
{
	private Long id;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String atm;
	private String branch;

	public Location()
	{
		
	}

	public Location(Long id, String address, String city, String state, String zip, String atm, String branch)
	{
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.atm = atm;
		this.branch = branch;
	}

	public Location(String address, String city, String state, String zip)
	{
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Location(String address, String city, String state, String zip, String atm, String branch)
	{
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.atm = atm;
		this.branch = branch;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getAtm()
	{
		return atm;
	}

	public void setAtm(String atm)
	{
		this.atm = atm;
	}

	public String getBranch()
	{
		return branch;
	}

	public void setBranch(String branch)
	{
		this.branch = branch;
	}
}
