package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.model.LocationService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindLocations extends ActionSupport
{
	private String address;
	private String city;
	private String state;
	private String zip;
	private String type;
	private List locations;

	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setMaxInactiveInterval(20);

		if (type != null && type.equals("atm"))
		{
			if (zip != null && !zip.trim().equals(""))
			{
				locations = LocationService.findAtmByZip(zip);
			}
			else
			{
				locations = LocationService.findAtmByAddress(address, city, state);
			}
		}
		else if (type != null && type.equals("branch"))
		{
			if (zip != null && !zip.trim().equals(""))
			{
				locations = LocationService.findBranchByZip(zip);
			}
			else
			{
				locations = LocationService.findBranchByAddress(address, city, state);
			}
		}
		else
		{
			if (zip != null)
			{
				locations = LocationService.findByZip(zip);
			}
		}
		return SUCCESS;
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

	public List getLocations()
	{
		return locations;
	}

	public void setLocations(List locations)
	{
		this.locations = locations;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
