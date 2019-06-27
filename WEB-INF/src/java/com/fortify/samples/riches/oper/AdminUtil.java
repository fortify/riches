package com.fortify.samples.riches.oper;

import com.fortify.samples.riches.database.ConnFactory;
import com.fortify.samples.riches.database.ConnectionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import net.sf.hibernate.Session;

public class AdminUtil
{
    final private static Logger log = Logger.getLogger(ConnectionFactory.class.getName());

    private static void safeCloseConnection(Connection conn){
        try{
            if(conn!=null)
                conn.close();
        }
        catch(SQLException e)
        {
            //Nothing we can do... we might log this
            log.warning("Unable to close Connection in AdminUtils!");
        }
    }

    private static void safeCloseStatement(Statement statement){
        try{
            if(statement!=null)
                statement.close();
        }
        catch(Exception e)
        {
            //Nothing we can do... we might log this
            log.warning("Unable to close Statement in AdminUtils!");
        }
    }

    public static boolean isAdmin(int roleid) throws Exception
    {
        boolean auth = false;
	if (roleid != -1) {
	    Connection conn = ConnFactory.getInstance().getConnection();
            ResultSet rs = null;
            Statement statement = null;
            try
            {
                statement =  conn.createStatement();
                rs = statement.executeQuery("SELECT rolename FROM auth WHERE roleid = " + roleid);
                rs.next();

                if (rs !=null && rs.getString("rolename").equals("admin"))
                    auth =  true;
            }
            catch(Exception e)
            {
                throw e;
            }
            finally{
                safeCloseStatement(statement);
                safeCloseConnection(conn);
            }
	}

        return auth;
    }

	// Leave all vulnerabilities in. This function is never executed.
    public static void debugAdminRoles(List roles) throws Exception
    {
        boolean auth = false;

		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

        try {
		  conn = ConnFactory.getInstance().getConnection();
          statement =  conn.createStatement();

		  for (int index=0; index < roles.size(); index++) {
		    int roleid = ((Integer)roles.get(index)).intValue();

            rs = statement.executeQuery("SELECT rolename FROM auth WHERE roleid = " + roleid);
            rs.next();

            if (rs !=null && rs.getString("rolename").equals("admin")) {
                System.err.println("Roleid: "+roleid+" is an admin");
				rs.close();
				rs = null; ;
			}
		  }
        }
        catch(Exception e) {
			if (rs != null) {
				rs.close();
				rs = null;
			}
            throw e;
        }
		finally {
		   System.err.println("Terminating here temporarily");
		   System.exit(-1);

		   if (statement != null) {
			   statement.close();
			   statement = null;
		   }
		}
    }
    
    public static boolean isAuth(HttpServletRequest request) throws Exception
    {
	    if(request==null)
		    return false;
			
		if ( request.isUserInRole("admin") ) return true;
			
        Cookie[] cookies = request.getCookies();
        int authType = -1;
        for (int i=0; i<cookies.length;i++)
        {
            if (cookies[i] != null && cookies[i].getName() != null && cookies[i].getName().equals("authType"))
            {
		try {
                    authType = Integer.parseInt(cookies[i].getValue());
		}
		catch(NumberFormatException e) {
		    // swallow exception for empty or bad authType value
		}
                break;
            }
        }
        return AdminUtil.isAdmin(authType);
    }
}
