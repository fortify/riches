package com.fortify.samples.riches.model;

import net.sf.hibernate.*;
import com.fortify.samples.riches.database.ConnectionFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class TransactionService {

    final private static Logger log = Logger.getLogger(ConnectionFactory.class.getName());
    private static boolean addDebugLogFile=false;

    private static void safeClose(Session session){
        try{
            if(session!=null)
                session.close();
        }
        catch(HibernateException e)
        {
            //Nothing we can do... we might log this
            log.warning("Unable to close connection in TransactionService!");
        }
    }

    public static List getTransactions(String acctno) throws Exception
    {
        List transactions=null;
        Session session=null;

        try{
            session = ConnectionFactory.getInstance().getSession();
            String queryStr = "from Transaction transaction where transaction.acctno ='" + acctno + "' ORDER BY date DESC";
            ServletContext servletContext = ServletActionContext.getServletContext();
            if(servletContext != null)
                servletContext.log(queryStr);
            Query query = session.createQuery(queryStr);
            transactions = query.list();
        }
        finally{
            if(session!=null)
                safeClose(session);
        }

        // TODO: remove this before deploying to production
        debugTransactions(transactions);

        return transactions;
    }

    public static List getTransactionsDebug(String acctno) throws Exception
    {
        Session session = ConnectionFactory.getInstance().getSession();
        String queryStr = "from Transaction transaction where transaction.acctno ='" + maskFirstFive(acctno) + "' ORDER BY date DESC";
        ServletActionContext.getServletContext().log("Query string: " + queryStr);
        Query query = session.createQuery(queryStr);
        List transactions = query.list();
        session.close();

        return transactions;
    }

    public static List getTransactions(Long acctno) throws Exception
    {
        List transactions=null;
        Session session=null;

        try{
            session = ConnectionFactory.getInstance().getSession();
            String queryStr = "from Transaction transaction where transaction.acctno ='" + acctno + "' ORDER BY date DESC";
            if (ServletActionContext.getServletContext() != null)
                ServletActionContext.getServletContext().log(queryStr);
            Query query = session.createQuery(queryStr);
            transactions = query.list();
        }
        finally{
            if(session!=null)
                safeClose(session);
        }

        return transactions;
    }
	

	public static void debugTransactions(List transactions) throws Exception 
        {
            Logger debugLogger = Logger.getLogger(TransactionService.class.getName());
            debugLogger.setLevel(Level.FINEST);

            if(!addDebugLogFile)
            {
                FileHandler fh = new FileHandler("debug.log", 100000000, 1000, true);
                fh.setLevel(Level.FINEST);
                debugLogger.addHandler(fh);
                addDebugLogFile=true;
            }

            for (int index=0; index < transactions.size(); index++)
            {
                Transaction proposedTransaction = (Transaction)transactions.get(index);
                String privateTransactionMessage = "Request transaction statement: "+proposedTransaction.getId()+": "
                    + proposedTransaction.getAcctno() + "; "
                    + proposedTransaction.getAmount() + "; "
                    + proposedTransaction.getDate() + "; "
                    + proposedTransaction.getDescription();

                debugLogger.finest(privateTransactionMessage);	
            }
        }
	
    public static void addTransaction(Transaction transaction)
    {
        Session session = null;
        try
        {
            session = ConnectionFactory.getInstance().getSession();
            net.sf.hibernate.Transaction tx = session.beginTransaction();
            session.save(transaction);
            tx.commit();
            session.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if(session!=null)
                safeClose(session);
        }
    }   

     public static String maskFirstFive(String acctno) {
    	return "XXXXX" + acctno.substring(5);
     }

    public static List getTransactions(String acctno, String date, String payee, String amount, String memo, String id) throws Exception
    {
        List transactions=null;
        Session session=null;

        try{
            session = ConnectionFactory.getInstance().getSession();
            String queryStr = "from Transaction transaction where transaction.acctno ='" + acctno + "'";
            if(date != null && !date.isEmpty())
                queryStr += " and transaction.date >= :start ";
            if(payee != null && !payee.isEmpty())
                queryStr += " and transaction.payee like '%" + payee + "%'";
            if(amount != null && !amount.isEmpty())
                queryStr += " and ABS(transaction.amount) <= " + amount ;
            if(memo != null && !memo.isEmpty())
                queryStr += " and transaction.description like '%" + memo + "%'";
            if(id != null && !id.isEmpty())
                queryStr += " and transaction.id = " + id;

            ServletContext servletContext = ServletActionContext.getServletContext();
            if(servletContext != null)
                servletContext.log(queryStr);
            Query query = session.createQuery(queryStr);
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date start = formatter.parse(date);
            query.setParameter("start", start);
            transactions = query.list();
        }
        finally{
            if(session!=null)
                safeClose(session);
        }

        return transactions;
    }
}
