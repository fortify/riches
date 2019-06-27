package com.fortify.samples.riches.restful;

import com.fortify.samples.riches.PerformRegistration;
import com.fortify.samples.riches.model.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import java.util.List;


@Path("/accounts")
public class AccountResources
{

    public AccountResources()
    {
    }

    @GET
    @Path("json")
    @Produces("application/xml")
    public String GetAccounts_JSON() {
        try {
            List list = AccountService.getAllAccounts();
            if(list != null) {
                return XMLUtil.GetAccountsJSON(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @GET
    @Produces("application/xml")
    public String GetAccounts() {
        try {
            List list = AccountService.getAllAccounts();
            if(list != null) {
                return XMLUtil.GetAccountsXML(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @GET
    @Path("{username}")
    @Produces("application/xml")
    public String GetAccountsByName(@PathParam("username") String username) {
        try {
            if(!ProfileService.IsProfileExist(username)) {
                return username + " is not a customer.";
            }
            List list = AccountService.getAccounts(username);
            if(list != null) {
                return XMLUtil.GetAccountsXML(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @GET
    @Path("{username}/json")
    @Produces("application/json")
    public String GetAccountsByName_JSON(@PathParam("username") String username) {
        try {
            if(!ProfileService.IsProfileExist(username)) {
                return username + " is not a customer.";
            }
            List list = AccountService.getAccounts(username);
            if (list != null) {
                return XMLUtil.GetAccountsJSON(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @PUT
    @Produces("application/xml")
    @Consumes("application/xml")
    public String AddAccount(String representation) {
        try {
            String username;
            Account acct;
            username = XMLUtil.GetValueXML(representation, "account", "username");
            double amount = Double.parseDouble(XMLUtil.GetValueXML(representation, "account", "balance"));
            if(!ProfileService.IsProfileExist(username)) {
                return (new StringBuilder()).append(username).append(" is not a customer.").toString();
            }
            acct = new Account(username, PerformRegistration.getNewAcctno(), amount, PerformRegistration.getNewCCN());
            AccountService.addAccount(acct);
            return (new StringBuilder()).append("Account ").append(acct.getAcctno()).append(" created for user ").append(username).toString();
        }
        catch (Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @POST
    @Path("{writecheck}")
    @Produces("application/xml")
    @Consumes("application/xml")
    public String WriteCheck(String representation) {
        try {
          String username = XMLUtil.GetValueXML(representation, "transaction", "payee");
            String acctno = XMLUtil.GetValueXML(representation, "transaction", "acctno");
            double amount = Double.parseDouble(XMLUtil.GetValueXML(representation, "transaction", "amount"));
//            String addr = XMLUtil.GetValueXML(representation, "transaction", "address");
            String memo = XMLUtil.GetValueXML(representation, "transaction", "memo");
            AccountService.writeCheck(username, "", memo, acctno, amount);
            return (new StringBuilder()).append("Customer ").append(username).append(" write check with account no. ").append(acctno).toString();
        } catch (Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @DELETE
    @Path("{acctno}")
    @Produces("application/xml")
    public String DeleteAccount(@PathParam("acctno") String acctno) {
        try {
            if(!AccountService.IsAccountExist(acctno)) {
                return "Account " + acctno + " does not exist";
            }
            AccountService.deleteAccount(acctno);
            return "Account " + acctno + " deleted";
        } catch (Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }

    @Path("transactions")
    public TransactionResources GetTransactionResources() {
        return new TransactionResources();
    }
}
