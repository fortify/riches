package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.model.AccountService;
import com.fortify.samples.riches.model.Account;
import com.fortify.samples.riches.model.Profile;
import com.fortify.samples.riches.model.ProfileService;

import java.util.regex.Matcher;

public class PerformRegistration extends ActionSupport
{
    private String firstName;
    private String lastName;
    private String email;
    private String ssn;
    private String username;
    private String password;
    private String confPassword;

    private String button;


    public String execute() throws Exception
    {
        button = "Register";

        if (firstName == null || firstName.equals(""))
        {
            addActionError("Enter your first name.");
            return INPUT;
        }
        if (lastName == null || lastName.equals(""))
        {
            addActionError("Enter your last name.");
            return INPUT;
        }
        if (email == null || email.equals(""))
        {
            addActionError("Enter your e-mail address.");
            return INPUT;
        }
        if (ssn == null || ssn.equals(""))
        {
            addActionError("Enter your social security number.");
            return INPUT;
        }
        else if (ssn.length() != 11 || !ssn.matches("\\d{3}\\-\\d{2}\\-\\d{4}"))
        {
            addActionError("Invalid social security number format. Use xxx-xx-xxxx.");
            return INPUT;
        }

        if (username == null || username.equals(""))
        {
            addActionError("Enter a username.");
            return INPUT;
        }

        if (!password.equals(confPassword))
        {
            addActionError("Your passwords don't match.");
            return INPUT;
        }

        Account account = new Account(username, getNewAcctno(), 0, getNewCCN());
        Profile profile = new Profile(username, password, firstName, lastName, email, ssn);

        int status = ProfileService.addProfile(profile);
        switch(status)
        {
            case ProfileService.USERNAME_EXISTS:
                addActionError("The username already exists.");
                break;
            case ProfileService.SSN_EXISTS:
                addActionError("The social security number is already linked to an account.");
                break;
            case ProfileService.EMAIL_EXISTS:
                addActionError("The e-mail address is already linked to an account.");
                break;
	    case ProfileService.ADD_FAILED:
		addActionError("Failed to add a profile.");
		break;
        }
        if (status != ProfileService.SUCCESS)
            return ERROR;

        if (AccountService.addAccount(account) == false)
        {
            //shouldn't happen
            addActionError("No profile found for the username.");
            return ERROR;
        }

        button = "/";
        addActionMessage("Account created.");
        return SUCCESS;
    }

	public static String getNewAcctno() {

		String account="";

		for(int i =0; i<10; i++)
		{
			account += new Double(Math.floor(Math.random() * 10)).intValue();
		}

		return account;
	}

	public static String getNewCCN() {

		String ccnumber="";

		do{
			ccnumber="";

			for(int i =0; i<16; i++)
			{
				ccnumber += new Double(Math.floor(Math.random() * 10)).intValue();
			}
		}while(!isValid(ccnumber));

		return ccnumber;
	}

	public static boolean isValid(String cardNumber)
	{
		int sum = 0;
		int digit = 0;
		int addend = 0;
		boolean timesTwo = false;

		for (int i = cardNumber.length() - 1; i >= 0; i--)
		{
			digit = Integer.parseInt(cardNumber.substring(i, i + 1));
			if (timesTwo)
			{
				addend = digit * 2;
				if (addend > 9)
				{
					addend -= 9;
				}
			}
			else
			{
				addend = digit;
			}
			sum += addend;
			timesTwo = !timesTwo;
		}

		int modulus = sum % 10;
		return modulus == 0;
	}


	public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }
}
