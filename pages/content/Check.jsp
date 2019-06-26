<%@ include file="../common/moduleInclude.jsp" %>

<style type="text/css">
textarea { margin-top:.18em; background-color:#ffc; }
</style>

	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" class="detailBox" width="45%">
				<tr valign="top" align="center" class="titleRow">
					<td colspan="4" align="left">&nbsp;&nbsp;Write a Check</td>
					<td width="20%">&nbsp;&nbsp;</td>
					<td width="8%">&nbsp;&nbsp;</td>
				</tr>

                        <s:form action="PerformCheck" method="POST" theme="simple">
                            <tr valign="top">
                                <td class="dataCell alt" align="center">To</td>
                                <td class="dataCell alt" align="left">
                                    <s:textfield name="name" size="30"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                                <td class="dataCell alt" align="center">Amount&nbsp;&nbsp;</td>
                                <td class="dataCell alt" align="left"><s:textfield name="amount" size="10"/>&nbsp;</td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell" align="center">&nbsp;Address&nbsp;&nbsp;</td>
                                <td class="dataCell" align="left">
                                    <s:textarea name="addr" rows="3" cols="28"/>
                                </td>
                                <td class="dataCell" width="8%">&nbsp;</td>
				<td colspan="3" class="dataCell">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                            </tr>
                            <tr valign="top">
                                <td class="dataCell" align="center">Memo</td>
                                <td class="dataCell" align="left">
                                    <s:textfield name="memo" size="25"/>
                                </td>
                                <td class="dataCell" width="8%">&nbsp;</td>
				<td colspan="3" class="dataCell">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                            <tr>
                                <td colspan="6" class="dataCell alt" align="center">
				   <input type="HiDdEn" name="acct" value="<c:out value="${account}"/>"/>
                                   <s:submit value="Submit"/>
                                </td>
                            </tr>
                        </s:form>
		</table>
			<br />
		</td>
	</tr>
