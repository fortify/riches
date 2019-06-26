<%@ include file="../common/moduleInclude.jsp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.sql.Date" %>
	<tr>
		<td>
			<table cellpadding="0" cellspacing="0" class="messageBox" width="60%" align="center">
				<tr valign="top">
					<td>
						<table cellspacing="0"  width="100%">
								<tr align="left"  class="titleRow">
									<td>&nbsp;</td>
									<td align="left">Date</td>
									<td align="left">Subject</td>
									<td>From</td>
								</tr>

                            <%
                                int size = ((java.util.List)request.getAttribute("messages")).size();
                                request.setAttribute("size", new Integer(size));
                            %>
                            <c:choose>
                                <c:when test="${size != 0}" >
                                    <s:form theme="simple" action="DeleteMessage">
                                        <c:forEach var="message" items="${messages}" varStatus="rowCount">
                                            <c:choose>
                                                <c:when test="${rowCount.count % 2 == 0}">
                                                    <tr class="dataCell alt">
                                                </c:when>
                                                <c:otherwise>
                                                    <tr class="dataCell">
                                                </c:otherwise>
                                            </c:choose>
                                                <td><input type="checkbox" name="messageID" value="${message.id}"/></td>
                                                <c:set var="date" scope="request" value="${message.date}"/>
                                                <%
                                                    Date date = (Date) request.getAttribute("date");
                                                    request.setAttribute("date", DateFormat.getDateInstance(DateFormat.MEDIUM).format(date));
                                                %>
                                                <td align="left"><c:out value="${date}"/></td>
                                                <td align="left"><a href="<s:url action="ViewMessage"><s:param name="id"><c:out value="${message.id}"/></s:param></s:url>"><c:out value="${message.subject}"/></a></td>
                                                <td><c:out value="${message.sender}"/></td>
                                            </tr>
                                            <c:set var="count" scope="page" value="${rowCount.count}"/>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${count % 2 == 0}">
                                                <tr class="dataCell" align="left">
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="dataCell alt" align="left">
                                            </c:otherwise>
                                        </c:choose>
                                                    <td colspan="4">&nbsp;<input type="image" align="absmiddle" alt="Delete" src="<s:url value="/img/btnDeleteMessages.jpg" includeParams="none"/>" value="Submit"/></td>                                        
                                                </tr>
                                    </s:form>
                                </c:when>
                                <c:otherwise>
                                    <tr class="dataCell alt">
                                        <td></td>
                                        <td></td>
                                        <td width="100%">No Messages found.</td>
                                        <td></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
						</table>
					</td>
				</tr>
			</table>
            <br/>
        </td>
	</tr>