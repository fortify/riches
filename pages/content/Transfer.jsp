<%@ include file="../common/moduleInclude.jsp" %>

	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" class="detailBox" width="45%">
				<tr valign="top" align="center" class="titleRow">
					<td width="35%" align="left">&nbsp;&nbsp;Transfer Funds</td>
					<td width="20%">&nbsp;&nbsp;</td>
					<td width="8%">&nbsp;&nbsp;</td>
				</tr>

                <%
                    int size = ((java.util.List)request.getAttribute("accounts")).size();
                    request.setAttribute("size", new Integer(size));
                %>

                <c:choose>
                    <c:when test="${size > 1}" >
                        <s:form action="PerformTransfer" method="POST" theme="simple">
                            <tr valign="top">
                                <td class="dataCell alt" align="center">Transfer From Account</td>
                                <td class="dataCell alt" align="right">&nbsp;&nbsp;
                                    <s:select list="accounts" name="from" listKey="acctno" listValue="acctno"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell" align="center">Transfer to Account</td>
                                <%--ENHANCEMENT: validate --%>
                                <td class="dataCell" align="right">&nbsp;&nbsp;
                                    <s:select list="accounts" name="to" listKey="acctno" listValue="acctno"/>
                                </td>
                                <td class="dataCell" width="8%">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell alt" align="center">Transfer Amount</td>
                                <td class="dataCell alt" align="right"><s:textfield name="amount" size="20"/>&nbsp;</td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="3" class="dataCell alt" align="center"><s:submit value="Initiate Transfer"/></td>
                            </tr>
                        </s:form>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td class="dataCell" align="center" width="100%">You need more than 1 account to transfer funds.</td>
                        <td class="dataCell" align="center"></td>
                        <td class="dataCell" align="center"></td>
                    </tr>
                    </c:otherwise>
                </c:choose>
			</table>
		</td>
	</tr>
