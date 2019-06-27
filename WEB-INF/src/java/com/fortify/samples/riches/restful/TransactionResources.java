package com.fortify.samples.riches.restful;

import com.fortify.samples.riches.model.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;


public class TransactionResources implements ITransactionResources
{

    public TransactionResources()
    {
    }

    public String GetTransctionsByAcctno(String acctno)
    {
        try
        {
            java.util.List list = TransactionService.getTransactions(acctno);
            if(list != null)
            {
                return XMLUtil.GetTransactionsXML(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e)
        {
            throw new WebApplicationException(e, 500);
        }
    }

    public String GetTransctionByAcctno_JSON(String acctno)
    {
        try
        {
            java.util.List list = TransactionService.getTransactions(acctno);
            if(list != null)
            {
                return XMLUtil.GetTransactionsJSON(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e)
        {
            throw new WebApplicationException(e, 500);
        }
    }

    @POST
    @Path("paybill")
    @Produces("application/xml")
    @Consumes("application/xml")
    public String PayBill(String representation)
    {
        try
        {
            String acctno = XMLUtil.GetValueXML(representation, "transaction", "acctno");
            double amount = Double.parseDouble(XMLUtil.GetValueXML(representation, "transaction", "amount"));
            String payee = XMLUtil.GetValueXML(representation, "transaction", "payee");
            String memo = XMLUtil.GetValueXML(representation, "transaction", "memo");
            AccountService.writeCheck(payee, "", memo, acctno, amount);
            return (new StringBuilder()).append(" pay bill to ").append(payee).append(" with account no. ").append(acctno).toString();
        }
        catch (Exception e)
        {
            throw new WebApplicationException(e, 500);
        }
    }

    @POST
    @Path("deposit")
    @Produces("application/xml")
    @Consumes("application/xml")
    public String Deposit(String representation) {
        try {
            String acctno = XMLUtil.GetValueXML(representation, "transaction", "acctno");
            double amount = Double.parseDouble(XMLUtil.GetValueXML(representation, "transaction", "amount"));
            String memo = XMLUtil.GetValueXML(representation, "transaction", "memo");
            AccountService.deposit(acctno, amount, memo);
            return new StringBuilder().append("$").append(amount).append(" deposited to account no. ").append(acctno).toString();
        }
        catch (Exception e) {
            throw new WebApplicationException(e, 500);
        }
    }


    @GET
    @Path("search/{username}:{acctno},{date}")
    @Produces("application/xml")
    public String GetTransactions(@PathParam("username") String username,
                           @PathParam("acctno") @DefaultValue("0422328325") String acctno,
                           @PathParam("date") String date,
                           @MatrixParam("payee") String payee,
                           @MatrixParam("amount") String amount,
                           @QueryParam("memo") String memo,
                           @QueryParam("id") String id){
        try
        {
            if(!ProfileService.IsProfileExist(username))
            {
                return (new StringBuilder()).append(username).append(" is not a customer.").toString();
            }

            java.util.List list = TransactionService.getTransactions(acctno, date, payee, amount, memo, id);
            if(list != null)
            {
                return XMLUtil.GetTransactionsXML(list);
            }

            throw new WebApplicationException(404);
        }
        catch(Exception e)
        {
            throw new WebApplicationException(e, 500);
        }
    }

}
