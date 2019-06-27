package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.database.ConnectionFactory;
import com.fortify.samples.riches.model.Account;
import com.fortify.samples.riches.model.AccountService;
import com.fortify.samples.riches.model.ProfileService;
import net.sf.hibernate.Session;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;

public class PerformCheck extends ActionSupport
{
    private String acct;
    private String name;
    private String addr;
    private String memo;
    private double amount = 0;

    public String execute() throws Exception
    {
	    try{
		    String message="some message";
		    String keyString="1234567890abcdef";
		    SecretKeySpec skeySpec = new SecretKeySpec(keyString.getBytes(), "AES");
		    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		    byte[] encrypted = cipher.doFinal(message.trim().getBytes());  // Exception thrown:javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes
	    }
	    catch (Exception e){
	    }

        if ("000000000000000".equals(memo)) {
          printUsers();
          return SUCCESS;
        }

        if (amount <= 0 )
        {
            addFieldError("amount", "Enter an amount");
            return INPUT;
        }

        int status = AccountService.writeCheck(name, addr, memo, acct, amount);
        if (AccountService.NO_FUNDS == status)
        {
            addActionError("Not enough funds available");
            return ERROR;
        }
        addActionMessage("Check sent");
        return SUCCESS;
    }

   public void printUsers() {
	try {
        	for (Object email : ProfileService.getAllEmail()) {
        		addActionMessage((String) email);
		}
	}
	catch (Exception e) {
		throw new RuntimeException(e);
	}
   }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr; 
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo; 
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
