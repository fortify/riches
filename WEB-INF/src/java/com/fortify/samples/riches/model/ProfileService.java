package com.fortify.samples.riches.model;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Expression;
import java.util.List;
import com.fortify.samples.riches.database.ConnectionFactory;

public class ProfileService
{
    // NOTE: sample profiles can be reproduced through internal server
    // host: db1.riches.com; username: service, password: passw0rd1!

    public static final int SUCCESS = 0;
    public static final int USERNAME_EXISTS = 1;
    public static final int SSN_EXISTS = 2;
    public static final int EMAIL_EXISTS = 3;
    public static final int ADD_FAILED = 4;

    public static int addProfile(Profile profile) throws Exception
    {
        Profile checkProfile = getProfile(profile.getUsername());
        if (checkProfile != null)
            return USERNAME_EXISTS;

        String user = getUsername(profile.getEmail());
        if (user != null)
            return EMAIL_EXISTS;

        user = getUsernameBySsn(profile.getSsno());
        if (user != null)
            return SSN_EXISTS;

        Role role = new Role(profile.getUsername(), "user");

        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            net.sf.hibernate.Transaction tx = session.beginTransaction();
            session.save(profile);
            session.save(role);
            tx.commit();
            session.flush();
            session.close();
System.out.println("added profile for " + profile.getUsername() + " (" + profile.getSsno() + ")");
        }
        catch(Exception e)
        {
            return ADD_FAILED;
        }
        finally
        {
            if (session != null)
                session.close();
        }
        return SUCCESS;
    }

    public static Profile getProfile(String username) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Expression.eq("username", username));
            Profile profile = (Profile) criteria.uniqueResult();
            return profile;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }

    public static List getAllProfiles() throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Profile.class);
            return criteria.list();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }

    public static String getFirstname(String username) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Expression.eq("username", username));
            Profile profile = (Profile) criteria.uniqueResult();
            return profile.getFirstname();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }

    public static String getLastname(String username) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Expression.eq("username", username));
            Profile profile = (Profile) criteria.uniqueResult();
            return profile.getLastname();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }

    public static String getFullname(String username) throws Exception
    {
        Session session = null;
        String name = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            List ret = session.find("select profile.firstname, profile.lastname from Profile profile where username='"+username+"'");
            if (ret.size() > 0)
            {
                Object[] names = (Object[])ret.get(0);
                name = names[0] + " " + names[1];
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }

        return name;
    }

    public static List getAllEmail() throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            return session.find("SELECT profile.email FROM Profile profile");
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }

    }

    public static String getUsername(String email) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Expression.eq("email", email));
            Profile profile = (Profile) criteria.uniqueResult();
            if (profile != null)
                return profile.getUsername();
            else
                return null;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }
    public static String getUsernameBySsn(String ssn) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Profile.class);
            criteria.add(Expression.eq("ssno", ssn));
            Profile profile = (Profile) criteria.uniqueResult();
            if (profile != null)
                return profile.getUsername();
            else
                return null;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }

    public static boolean IsProfileExist(String username) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            List ret = session.find((new StringBuilder()).append("select profile.username from Profile profile where username='").append(username).append("'").toString());
            if(ret.size() <= 0)
            {
                return false;
            }
            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(session != null)
            {
                session.close();
            }
        }
    }
}
