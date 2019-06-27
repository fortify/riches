package com.fortify.samples.riches.model;

import net.sf.hibernate.*;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import com.fortify.samples.riches.database.ConnectionFactory;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class MessageService
{
    public static void addMessage(Message message)
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Transaction tx = session.beginTransaction();
            session.save(message);
            tx.commit();
            session.flush();
            session.close();
        }
        catch(Exception e)
        {
            // Treat all exceptions the same here
        }
    }

    public static void deleteMessages(String[] ids) throws Exception
    {

        Transaction transaction = null;
        Session session = ConnectionFactory.getInstance().getSession();
        try
        {
            transaction = session.beginTransaction();

            for (int i=0; i < ids.length; i++)
            {
                Message message = (Message)session.load(Message.class, Long.parseLong(ids[i]));
                session.delete(message);
            }

            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
                throw e;
            }

        }
        finally
        {
            session.close();
        }
    }

    public static List getMessage(Long id) throws Exception
    {
        Session session = ConnectionFactory.getInstance().getSession();

        String queryStr = "from Message message where message.id = " + id;
        Query query = session.createQuery(queryStr);
        List messages = query.list();
        session.close();
		
		// Sanitize incoming messages from database
		ArrayList sanitizedMessages = new ArrayList();
		
		for (int index=0; index < messages.size(); index++)
		{
			Message sanitizedMessage = validateMessage((Message)(messages.get(index)));
			sanitizedMessages.add(sanitizedMessage);
		}
		
        return (List)sanitizedMessages;
    }

	private static Message validateMessage(Message incomingMessage) throws Exception
	{
		// Validate sender
		String incomingSender = incomingMessage.getSender();
		if ((incomingSender == null) || (incomingSender.length() == 0))
			throw new Exception("invalid sender in message");
		
		// Validate subject
		String incomingSubject = incomingMessage.getSubject();
		if (incomingSubject == null)
			throw new Exception("invalid subject in message");
		
		// Validate severity
		String incomingSeverity = incomingMessage.getSeverity();
		if ((incomingSeverity == null) || (incomingSeverity.length() == 0))
			throw new Exception("invalid sender in message");
		
		// Validate body
		String incomingBody = incomingMessage.getBody();
		if (incomingBody == null)
			throw new Exception("invalid sender in message");
		
		return incomingMessage;
	}
	
	private static void debugMessages(List messages) throws Exception 
	{
		Logger debugLogger = Logger.getLogger(MessageService.class.getName());
		debugLogger.setLevel(Level.FINEST);
		FileHandler fh = new FileHandler("debug.log");
		fh.setLevel(Level.FINEST);
		debugLogger.addHandler(fh);
		
		for (int index=0; index < messages.size(); index++)
		{
			Message proposedMessage = (Message)messages.get(index);
			String privateMessageContents = "Request message: "+proposedMessage.getSender()+": "
			+ proposedMessage.getId() + "; "
			+ proposedMessage.getDate() + "; "
			+ proposedMessage.getSubject() + "; "
			+ proposedMessage.getBody() + "; ";
			
			debugLogger.finest(privateMessageContents);	
		}
	}
	
    public static List getMessage(String username) throws Exception
    {
        Session session = ConnectionFactory.getInstance().getSession();

        String queryStr = "from Message message where message.username ='" + username + "' order by message.date desc";
        Query query = session.createQuery(queryStr);
        List messages = query.list();
        session.close();

        return messages;
    }
}
