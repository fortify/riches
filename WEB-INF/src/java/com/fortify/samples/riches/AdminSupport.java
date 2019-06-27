package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.database.ConnectionFactory;
import com.fortify.samples.riches.oper.AdminUtil;
import com.fortify.samples.riches.model.MessageService;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import org.apache.struts2.ServletActionContext;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

public class AdminSupport extends ActionSupport
{
    private boolean auth;

    public String execute() throws Exception
    {
        setAuth(AdminUtil.isAuth(ServletActionContext.getRequest()));
        return SUCCESS;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }

}
