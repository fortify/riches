<%@ include file="../common/moduleInclude.jsp" %>
<%@ page import="com.fortify.samples.riches.model.*" %>

<% String incomingParameter = request.getParameter("id");
   Long decodedParameter = Long.decode(incomingParameter.trim());

   Message msg = (Message)(MessageService.getMessage(decodedParameter).get(0));
   pageContext.setAttribute("severity", msg.getSeverity());
   pageContext.setAttribute("sender", msg.getSender());
   pageContext.setAttribute("subject", msg.getSubject());
   pageContext.setAttribute("body", msg.getBody()); 
%>

	<tr>
		<td>
			<table cellpadding="0" cellspacing="0" class="messageBox"  width="60%" align="center">
				<tr valign="top">
					<td>
					    <table cellspacing="0" width="100%" align="center">
						<tr class="dataCell alt"  align="left">
						    <td width="10%">&nbsp;Date:</td>
					    	    <td><%=msg.getDate().toString()%></td>
						</tr>
						<tr class="dataCell" align="left">
						    <td>&nbsp;From:</td>
						    <td><c:out value="${sender}"/></td>
						</tr>
						<tr class="dataCell alt" align="left">
						    <td>&nbsp;Subject:</td>
						    <td><c:out value="${subject}"/></td>
						</tr>
						<c:if test='${severity != null && severity != "Low"}'>
						<tr class="dataCell" align="left">
						    <td>&nbsp;Severity:</td>
						    <td><c:out value="${severity}" escapeXml="false"/></td>
						</tr>
						</c:if>	
						<tr class="dataCell">								        
						    <td colspan="2" align="left"><textarea cols="125" readonly="readonly" rows="12" wrap="soft"><c:out value="${body}" escapeXml="false"/></textarea></td>
						</tr>
				             </table>
					</td>
				 </tr>
			</table>
            <br>
        </td>
	</tr>
