package com.fortify.samples.riches.model;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Expression;

import java.security.MessageDigest;
import java.util.List;
import java.sql.Date;
import java.util.logging.Logger;


import com.fortify.samples.riches.database.ConnectionFactory;

public class AccountService
{
    public static final int NO_FUNDS = -1;
    public static final int SUCCESS = 0;

	final private static Logger log = Logger.getLogger(ConnectionFactory.class.getName());

    public static boolean addAccount(Account account) throws Exception
    {
        Profile profile = ProfileService.getProfile(account.getUsername());
        if (profile == null)
            return false;

        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            net.sf.hibernate.Transaction tx = session.beginTransaction();
            session.save(account);
            tx.commit();
            session.flush();
            session.close();
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
        return true;
    }

    public static List getAllAccounts() throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Account.class);
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

    public static List getAccounts(String username) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Account.class);
            criteria.add(Expression.eq("username", username));
            return criteria.list();
            //return session.find("select account.id, account.username, account.acctno, account.balance, account.ccn from Account account where username='" + username +"'");
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

    public static int deposit(String acct, double amount, String memo) throws Exception {
        Account account;
        Session session = null;
        net.sf.hibernate.Transaction transaction = null;
        try {
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Account.class);

            criteria.add(Expression.eq("acctno", acct));
            account = (Account) criteria.uniqueResult();

            account.setBalance(account.getBalance() + amount);
            TransactionService.addTransaction(new Transaction(acct, amount, new Date(System.currentTimeMillis()), memo, (String) null));

            session.close();
            session = ConnectionFactory.getInstance().getSession();

            try{
                transaction = session.beginTransaction();
                session.update(account);
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
        }
        finally
        {
            if (session != null)
                session.close();
        }

        return SUCCESS;

    }

    public static int writeCheck(String payee, String addr, String memo, String acct, double amount) throws Exception
    {
        Account fromAcct;
        Session session = null;
        net.sf.hibernate.Transaction transaction = null;

        try{
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Account.class);

            criteria.add(Expression.eq("acctno", acct));
            fromAcct = (Account) criteria.uniqueResult();
            log.warning("check from: " + fromAcct.getAcctno() + " using the credit card on file: " + fromAcct.getCcn());
            if (amount > fromAcct.getBalance())
            {
                return NO_FUNDS;
            }
            fromAcct.setBalance(fromAcct.getBalance() - amount);
            TransactionService.addTransaction(new Transaction(acct, -amount, new Date(System.currentTimeMillis()), memo, payee));

            session.close();
            session = ConnectionFactory.getInstance().getSession();

            try{
                transaction = session.beginTransaction();
                session.update(fromAcct);
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
        }
        finally
        {
            if (session != null)
                session.close();
        }

        return SUCCESS;
    }

    public static int tranferFunds(String from, String to, double amount) throws Exception
    {
        Account fromAcct;
        Account toAcct;
        Session session = null; 

        try{
            session = ConnectionFactory.getInstance().getSession();
            Criteria criteria = session.createCriteria(Account.class);
            criteria.add(Expression.eq("acctno", from));
            fromAcct = (Account) criteria.uniqueResult();
            System.out.println("trans from: " + fromAcct.getAcctno());

            criteria = session.createCriteria(Account.class);
            criteria.add(Expression.eq("acctno", to));
            toAcct = (Account) criteria.uniqueResult();
            System.out.println("trans to: " + toAcct.getAcctno());

            //Race
            if (amount > fromAcct.getBalance())
            {
                return NO_FUNDS;
            }

            fromAcct.setBalance(fromAcct.getBalance() - amount);
            TransactionService.addTransaction(new Transaction(from, -amount, new Date(System.currentTimeMillis()),"Withdrawl"));        
            toAcct.setBalance(toAcct.getBalance() + amount);
            TransactionService.addTransaction(new Transaction(to, amount, new Date(System.currentTimeMillis()),"Deposit"));
            net.sf.hibernate.Transaction transaction = null;

            session.close();
            session = ConnectionFactory.getInstance().getSession();

            try{
                transaction = session.beginTransaction();
                session.update(toAcct);
                session.update(fromAcct);
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
        }
        finally
        {
            if(session!=null)
                session.close();
        }

        return SUCCESS;
    }
    
    public static boolean changePassword(String username, String old_pass, String new_pass, String new_pass2) throws Exception
    {

        Profile profile = ProfileService.getProfile(username);
        if (profile == null)
            return false;

        if(!new_pass.equals(new_pass2)){
            return false;
        }

        if(profile.getPassword().equals(old_pass)){
            profile.setPassword(new_pass);

	        //Try to hash the new password
	        try{
		        MessageDigest md = MessageDigest.getInstance("SHA-2");
	        }
	        catch (Exception e){
	        }


            Session session = null;
            try
            {
                session = ConnectionFactory.getInstance().getSession();
                net.sf.hibernate.Transaction tx = session.beginTransaction();
                session.update(profile);
                tx.commit();
                session.flush();
                session.close();
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
            return true;
        }

        return false;
    }

    public static void deleteAccount(String acctno) throws Exception
    {
        Session session = null;
        net.sf.hibernate.Transaction tx = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Account.class);
            criteria.add(Expression.eq("acctno", acctno));
            Account acct = (Account) criteria.uniqueResult();
            session.delete(acct);
            tx.commit();
        }
        catch(Exception e)
        {
            if(tx != null)
            tx.rollback();
            throw e;
        }
        finally
        {
            if (session != null)
                session.close();
        }
    }

    public static boolean IsAccountExist(String acctno) throws Exception
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            List ret = session.find((new StringBuilder()).append("select account.acctno from Account account where acctno='").append(acctno).append("'").toString());
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
            if (session != null)
                session.close();
        }
    }
}
