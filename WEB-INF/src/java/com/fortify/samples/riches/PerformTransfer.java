package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.database.ConnectionFactory;
import com.fortify.samples.riches.model.Account;
import com.fortify.samples.riches.model.AccountService;
import net.sf.hibernate.Session;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPairGenerator;

public class PerformTransfer extends ActionSupport
{
    private String from;
    private String to;
    private double amount = 0;

    public String execute() throws Exception
    {
        if (amount <= 0 )
        {
            addFieldError("amount", "Enter an amount");
            return INPUT;
        }

        int status = AccountService.tranferFunds(from, to, amount);
        if (AccountService.NO_FUNDS == status)
        {
	        try{
		        throw new FundException("Not enough funds available. Amount trying to transfer: "+amount);
	        }
	        catch(FundException e){
		        addActionError(e.getMessage());
		        return ERROR;
	        }
        }

	    // Throwing Exceptions for RTAL
	    try{
		    byte[] theKey={'a'};
		    SecretKeySpec skeySpec = new SecretKeySpec(theKey, "AES");
		    Cipher cipher = Cipher.getInstance("AES");
		    cipher.init(Cipher.ENCRYPT_MODE, skeySpec); // Exception thrown:java.security.InvalidKeyException: Invalid AES key length: 1 bytes
	    }
	    catch (Exception e){
	    }

	    try{
		    SecretKeySpec skeySpec = new SecretKeySpec(from.getBytes(), "AES/CBC/PKCS7Padding");
		    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
		    cipher.init(Cipher.ENCRYPT_MODE, skeySpec); // Exception thrown:java.security.NoSuchAlgorithmException: Cannot find any provider supporting AES/CBC/PKCS7Padding
	    }
	    catch (Exception e){
	    }

        addActionMessage("Transfer complete");
        return SUCCESS;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}