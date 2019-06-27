package com.fortify.samples.riches.restful;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;


public interface ITransactionResources
{

    @GET
    @Path("{acctno}")
    @Produces("application/xml")
    public String GetTransctionsByAcctno(@PathParam("acctno") String acctno);

    @GET
    @Path("{acctno}/json")
    @Produces("application/json")
    public String GetTransctionByAcctno_JSON(@PathParam("acctno") String acctno);


    @PUT
    @Path("{paybill}")
    public String PayBill(String representation);

}
