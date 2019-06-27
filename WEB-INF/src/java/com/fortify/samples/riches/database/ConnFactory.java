package com.fortify.samples.riches.database;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

public class ConnFactory
{
	private static ConnFactory instance = null;
	private DataSource datasource = null;

	private ConnFactory()
    {
        try
        {
            String pFile = System.getProperty("ConnFactory.pfile");
            if (pFile != null)
            {
                java.util.Properties props = new java.util.Properties();
                props.load( new java.io.FileInputStream(pFile) );
            }
            Context ctx = new InitialContext();
            if (ctx == null)
            {
                throw new Exception("Boom - No Context");
            }
System.out.println("datasource before");
            datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/riches");
System.out.println("datasource after");            
            if (datasource == null)
            {
                throw new Exception("Boom - No java:comp/env/jdbc/riches");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

	/** 
	 * getInstance() returns the instance of the ConnFactory singleton.
	 * 
	 * Example call to retrieve Connection:
	 * 
	 * <pre>
	 * Connection connection = ConnFactory.getInstance().getConnection();
	 * </pre>
	 * 
	 * @return Instance of the <code>ConnFactory</code> singleton.
	 */
	public static synchronized ConnFactory getInstance()
	{
		if (instance == null)
		{
			instance = new ConnFactory();
		}
		return instance;
	}

	/**
	 * getConnection() returns a <code>Connection</code>
	 * 
	 * @return <code>Connection</code> retrieved <Code>ConnectionFactory</code>
	 */
	public Connection getConnection()
	{
        Connection conn = null;

        try
        {
            conn =  datasource.getConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return conn;
    }

}
