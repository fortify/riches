package com.fortify.samples.riches.model;

import java.sql.Date;

public class Message
{
    private Long id;
    private String username;
    private Date date;
    private String sender;
    private String subject;
    private String body;
    private String severity;

    public Message() {
    }

    public Message(Long id, String username, String sender, String subject, String severity, String body, Date date) {
        this.id = id;
        this.username = username;
        this.sender = sender;
        this.subject = subject;
	this.severity = severity;
        this.body = body;
        this.date = date;
    }


    public Message(String username, String sender, String subject, String severity, String body, Date date) {
            this.username = username;
            this.sender = sender;
            this.subject = subject;
	    this.severity = severity;
            this.body = body;
            this.date = date;
        }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
