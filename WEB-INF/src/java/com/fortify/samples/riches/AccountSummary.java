package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import com.fortify.samples.riches.model.AccountService;
import com.fortify.samples.riches.model.ProfileService;
import com.fortify.samples.riches.model.Profile;

public class AccountSummary extends AdminSupport
{
    private String name;
	private String ssn;
	private List accounts;

    public String execute() throws Exception
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getRemoteUser();

		Profile p = ProfileService.getProfile(username);
		name = p.getFirstname() + " " + p.getLastname();
		ssn = p.getSsno();
		if ( request.isUserInRole("admin") ) {
			// display all Accounts if this is admin session
			accounts = AccountService.getAllAccounts();
		} else {
			accounts = AccountService.getAccounts(username);
		}
 
        ServletActionContext.getServletContext().log("Account Summary request for: "+name+" with ssn: "+ssn);

        super.execute();
        return SUCCESS;
    }

	public String getSsn() {
		return ssn;
	}

	public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.name = name;
    }

    public List getAccounts() {
        return accounts;
    }

    public void setAccounts(List accounts) {
        this.accounts = accounts;
    }
}

