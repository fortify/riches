package com.fortify.samples.riches.model;

import com.fortify.samples.riches.database.ConnFactory;
import com.fortify.samples.riches.database.ConnectionFactory;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.expression.Expression;

import java.util.logging.Logger;

public class LocationService
{
    final private static Logger log = Logger.getLogger(ConnectionFactory.class.getName());

    private static void safeClose(Session session){
        try{
            if(session!=null)
                session.close();
        }
        catch(HibernateException e)
        {
            //Nothing we can do... we might log this
            log.warning("Unable to close Session in LocationService!");
        }
    }

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

    public static List findAtmByZip(String zip)
    {
        List locations=null;
        Session session=null;
        try{
            session = ConnectionFactory.getInstance().getSession();
            String queryStr = "from Location where atm = 'Yes' and zip ='" + zip + "'";
            Query query = session.createQuery(queryStr);
            locations = query.list();
        }
        catch (Exception e){}
        finally{
            if(session!=null)
                safeClose(session);
        }
        return locations;
    }

    public static List findBranchByZip(String zip) throws Exception
    {
        List branches=null;
        Session session=null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Location.class);
            criteria.add(Expression.eq("branch",  "Yes"));
            criteria.add(Expression.eq("zip", zip));
            branches=criteria.list();
        }
        finally{
            if(session!=null)
                safeClose(session);
        }
        return branches;
    }

    /* Example on how prepareStatement can be misused in such a way that the query is still vuln to SQLi */
    public static List findByZip(String zip) throws Exception
    {
        Connection conn=null;
        PreparedStatement statement = null;
        ArrayList locations = new ArrayList();

        try{
            conn = ConnFactory.getInstance().getConnection();

            String queryStr = "SELECT * FROM location WHERE zip = '" + zip  + "'";
            statement = conn.prepareStatement(queryStr);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                locations.add(new Location(rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("atm"), rs.getString("branch")));
            }
        }
        finally{
            safeCloseStatement(statement);
            safeCloseConnection(conn);
        }

        return locations;


    }


    public static List findAtmByAddress(String address, String city, String state) throws Exception
    {
        Connection conn=null;
        Statement statement = null;
        ArrayList locations = new ArrayList();

        try{
            conn = ConnFactory.getInstance().getConnection();

            String queryStr = "SELECT * FROM location WHERE branch = 'Yes' AND state = '" + state + "' AND city = '" + city + "' AND address = '" + address + "'";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryStr);
            while (rs.next())
            {
                locations.add(new Location(rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("atm"), rs.getString("branch")));
            }
        } 
        finally{
            safeCloseStatement(statement);
            safeCloseConnection(conn);
        }

        return locations;
    }

    public static List findBranchByAddress(String address, String city, String state) throws Exception
    {
        List branches=null;
        Session session=null;
        try{
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Location.class);
            criteria.add(Expression.eq("branch",  "Yes"));
            criteria.add(Expression.eq("address", address));
            criteria.add(Expression.eq("city", city));
            criteria.add(Expression.eq("state", state));
            branches=criteria.list();
        }
        finally{
            if(session!=null)
                safeClose(session);
        }
        return branches;
    }

}
