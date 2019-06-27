package com.fortify.samples.riches.legacy.mail;

import com.fortify.samples.riches.database.ConnFactory;
import java.sql.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class SendMail {

    private static String smtpServer;
	private static int smtpPort;
	private static String smtpUsername;
	private static String smtpPassword;

	static {
		try {

			// Read properties file.
			Properties properties = new Properties();
			properties.load(new FileInputStream("mailserver.legacy.properties"));

			smtpServer = properties.getProperty("riches.mail.smptHostname");
			smtpPort = Integer.parseInt(properties.getProperty("riches.mail.smtpPort"));
			smtpUsername = properties.getProperty("riches.mail.username");
			smtpPassword = properties.getProperty("riches.mail.password");

		}
		catch (IOException e) {
		 e.printStackTrace();
		}
	}
	
    //Stub class to send mail.
    public static void main(String args[])
    {

        String to = args[3];
	String sender = "RWi";
        String subject = args[0];
	String severity = args[1];
        String body = args[2];
        Date date = new Date(System.currentTimeMillis());


        //Send mail to email address.
        sendMail(to, sender, subject, severity, body, date);

        System.out.println("Message sent to: " + args[3]);
    }


    public static void sendMail(String to, String sender, String subject, String severity, String body, Date date)
    {
        //todo: implement native mail system
        return;
    }
}
