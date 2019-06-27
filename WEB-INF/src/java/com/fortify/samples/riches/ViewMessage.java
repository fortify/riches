package com.fortify.samples.riches;

import com.opensymphony.xwork2.ActionSupport;
import com.fortify.samples.riches.database.ConnectionFactory;
import com.fortify.samples.riches.model.Message;
import com.fortify.samples.riches.model.MessageService;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import java.util.List;
import java.sql.Date;
import java.text.DateFormat;

public class ViewMessage extends ActionSupport
{
    private Long id;
    private String subject;
    private String body;
    private String sender;
    private String date;

    public String execute() throws Exception
    {

        List messages = MessageService.getMessage(id);

        if (messages.size() != 1)
        {
            System.out.println("Unexpected number of messages returned: " + messages.size());
            return ERROR;
        }
        else
        {
            Message message = (Message) messages.get(0);
            sender = message.getSender();
            subject = message.getSubject();
            body = message.getBody();
            date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getDate());
            return SUCCESS;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}