package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.database.ConnectionFactory;
import com.fortify.samples.riches.model.Account;
import com.fortify.samples.riches.model.AccountService;
import net.sf.hibernate.Session;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class PerformChangePass extends ActionSupport
{
    private String old;
    private String new1;
    private String new2;

    //private String to;
    //private double amount = 0;

    public String execute() throws Exception
    {
        if(old==null || old.equals("")){
            addActionError("Old Password is null or empty");
            return ERROR;
        }
        if(new1 ==null || new1.equals("")){
            addActionError("New Password is null or empty");
            return ERROR;
        }
        if(new2 == null||  new2.equals("")){
            addActionError("New Password Confirmation is null or empty");
            return ERROR;
        }
        if(!new1.equals(new2)){
            addActionError("New Password and Confirmation Password differ");
            return ERROR;
        }

        HttpServletRequest request = ServletActionContext.getRequest();
	    String username = request.getRemoteUser();

        if(AccountService.changePassword(username,old,new1,new2)){
            addActionMessage("Password successfully changed");
            return SUCCESS;
        }
        else{
            addActionError("Old Password was incorrect");
            return ERROR;
        }
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getNew1() {
        return new1;
    }

    public void setNew1(String new1) {
        this.new1 = new1;
    }

    public String getNew2() {
        return new2;
    }

    public void setNew2(String new2) {
        this.new2 = new2;
    }
}