package com.fortify.samples.riches.database;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import java.util.logging.Logger;

import com.fortify.samples.riches.model.*;

public class ConnectionFactory
{
    private static Logger log = Logger.getLogger(ConnectionFactory.class.getName());

	private static ConnectionFactory instance = null;
	private SessionFactory sessionFactory = null;

	private ConnectionFactory()
	{
		try
		{
            String pFile = System.getProperty("ConnectionFactory.pfile");
            if (pFile != null)
            {
                java.util.Properties props = new java.util.Properties();
                props.load( new java.io.FileInputStream(pFile) );
            }
        }
        catch (Exception e)
        {
			// TODO: fill in this code
        }
		// Establish SessionFactory for Hibernate
		try
		{

			/*
			 * The Hibernate Configuration will contain all the classes to be
			 * persisted by Hibernate.  For each class persisted, Hibernate will
			 * expect to find a ClassName.hbm.xml file in the same location as the
			 * class file.  This XML file will define the mapping between the Java
			 * object and the database.
			 * 
			 * To add additional classes to the configuration, you may cascade the
			 * method calls thusly:
			 * 
			 * Configuration cfg = new Configuration().
			 *                         addClass(Foo.class).
			 *                         addClass(Bar.class);
			 * 
			 */
			Configuration cfg = new Configuration().addClass(Account.class).addClass(Transaction.class).addClass(Message.class).addClass(Profile.class).addClass(Role.class).addClass(Location.class);

			sessionFactory = cfg.buildSessionFactory();

		}
		catch (MappingException e)
		{
			/*
			 * Upon encountering a Hibernate generated Exception, we are throwing
			 * an unchecked RuntimeExcpetion that will cause the user's
			 * request to fail.
			 * 
			 */
            log.throwing(ConnectionFactory.class.getName(), "ConnectionFactory", e);
			throw new RuntimeException(e);
		}
		catch (HibernateException e)
		{
			/*
			 * Upon encountering a Hibernate generated Exception, we are throwing
			 * an unchecked RuntimeExcpetion that will cause the user's request to fail.
			 * 
			 */
            log.throwing(ConnectionFactory.class.getName(), "ConnectionFactory", e);
			throw new RuntimeException(e);
		}

	}

	/** 
	 * getInstance() returns the instance of the ConnectionFactory singleton.
	 * 
	 * Example call to retrieve session:
	 * 
	 * <pre>
	 * Session session = ConnectionFactory.getInstance().getSession();
	 * </pre>
	 * 
	 * @return Instance of the <code>ConnectionFactory</code> singleton.
	 */
	public static synchronized ConnectionFactory getInstance()
	{
		/* 
		 * If the instance of the Singleton has not been created, create and
		 * return.
		 */
		if (instance == null)
		{
			instance = new ConnectionFactory();
		}
		return instance;
	}

	/**
	 * getSession() returns a Hibernate <code>Session</code>
	 * 
	 * @return <code>Session</code> retrieved from Hibernate <Code>SessionFactory</code>
	 */
	public Session getSession()
	{
		try
		{
			/*
			 * Use the Hibernate Session Factory to return an open session to the caller.
			 */
			Session s = sessionFactory.openSession();
			return s;
		}
		catch (HibernateException e)
		{
			/*
			 * Upon encountering a Hibernate generated Exception, we are throwing
			 * an unchecked RuntimeExcpetion that will cause the user's request to fail.
			 * 
			 */
            log.throwing(ConnectionFactory.class.getName(), "getSession", e);
			throw new RuntimeException(e);
		}
	}

    private boolean DebugSession()
    {
        //some debug code for the session
       return true;
    }
}
