package com.fortify.samples.riches.oper;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.model.ProfileService;
import com.fortify.samples.riches.AdminSupport;

import java.util.List;

import org.apache.struts2.ServletActionContext;

public class Newsletter extends AdminSupport
{
    private List addresses;

    public String execute() throws Exception
    {
        addresses = ProfileService.getAllEmail();
        super.execute();
        return SUCCESS;
    }

    public List getAddresses() {
        return addresses;
    }

    public void setAddresses(List addresses) {
        this.addresses = addresses;
    }
}
