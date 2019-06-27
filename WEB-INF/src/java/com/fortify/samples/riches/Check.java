package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.database.ConnectionFactory;
import com.fortify.samples.riches.model.Account;
import com.fortify.samples.riches.model.AccountService;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.expression.Expression;
import org.apache.struts2.ServletActionContext;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Check extends AdminSupport
{
    private String account;

    public String execute() throws Exception
    {
        HttpServletRequest request = ServletActionContext.getRequest();
	    String username = request.getRemoteUser();
        List accounts = AccountService.getAccounts(username);

        if(accounts.size() > 0)
        {
            account = ((Account)(accounts.get(0))).getAcctno();
        }
        else
        {
            account = "No account found";
        }
	    super.execute();
        return SUCCESS;
    }

    public String getAccount() {
        return account;
    }
}
