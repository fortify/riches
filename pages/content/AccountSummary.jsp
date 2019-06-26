<%@ include file="../common/moduleInclude.jsp" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>

    <%
        String today = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
        NumberFormat numFormat = NumberFormat.getCurrencyInstance();
    %>
	
	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" class="detailBox" width="90%">
				<tr valign="top" align="center" class="titleRow">
					<td width="35%" align="left">Account Number</td>
					<td width="15%">As of Date</td>
					<td width="30%">Current Balance</td>
				</tr>
                <%
                    int size = ((java.util.List)request.getAttribute("accounts")).size();
                    request.setAttribute("size", new Integer(size));
                %>

                <c:choose>
                    <c:when test="${size != 0}" >
                        <c:forEach var="account" items="${accounts}">
                            <tr valign="top">
                                <%-- SEEME --%>
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;<a href="<s:url action="AccountDetails"><s:param name="acctno"><c:out value="${account.acctno}"/></s:param></s:url>"/><c:out value="${account.acctno}"/></a></td>
                                <td class="dataCell alt" align="center">&nbsp;&nbsp;<%=today%></td>
                                <c:set var="balance" scope="request" value="${account.balance}"/>
                                <%
                                    Double balance = (Double)request.getAttribute("balance");
                                    request.setAttribute("balance", numFormat.format(balance.doubleValue()));
                                %>
                                <td class="dataCell alt" align="center"><c:out value="${balance}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr valign="top">
                            <td class="dataCell">No accounts found.</td>
                            <td class="dataCell"></td>
                            <td class="dataCell"></td>
                        </tr>
                    </c:otherwise>
                </c:choose>

			</table>
			<br />
		</td>
	</tr>