package com.fortify.samples.riches.database;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DatabaseController implements ServletContextListener {

	private DataSource datasource = null;
	public static final String JDBC_RESOURCES = "java:comp/env/jdbc/riches";

    /**
	 * Makes a string safe for use in regex replace (String.replaceAll(), Matcher.appendReplacement())
	 * by escaping any special characters
	 */
	public static String sanitizeForReplace(String val){
		val = val.replaceAll("[\\\\]", "\\\\\\\\");
		return val.replaceAll("[\\$]", "\\\\\\$");
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			datasource = (DataSource)new InitialContext().lookup(JDBC_RESOURCES);
		} catch (NamingException ex) {
			throw new RuntimeException("Could not locate JNDI information for JDBC (" + JDBC_RESOURCES + ").\n"
					+ "Riches will not be able to connect to any database.\n"
					+ "This problem must be corrected before proceeding.");
		}
	}


	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Stopping the built in database...");
		try {
			Connection conn = datasource.getConnection();
			conn.createStatement().execute("SHUTDOWN COMPACT");
			conn.close();
		}
		catch(SQLException e) {
			System.err.println("Cannot shutdown the builtin database:" + e.getMessage());
		}
	}
}



