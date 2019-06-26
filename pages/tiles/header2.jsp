<tr valign="top">
    <td colspan="2">
        <table cellpadding="0" cellspacing="0" border="0" width="100%" id="utilities">
            <tr valign="middle">
                <td align="right"><a href="/riches/">Bank Home</a> | <a href="<s:url action="../FindLocations" includeParams="none"/>">Find Locations</a> | <a >Contact
                    Us</a> | <a >Help</a> | <a >Privacy Policy</a> | <a href="<s:url value="/login/Logout.action"/>">Sign Off</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr valign="top">
    <td align="left">
        <img id="logo" src="<s:url value="/img/rwi_50.gif" includeParams="none"/>"/>
    </td>
</tr>
<tr valign="top" id="menubar">
    <td colspan="2">
        <table cellpadding="0" cellspacing="0" border="0" align="right">
            <tr valign="top">
                <td><a href="<s:url value="/auth/AccountSummary.action" includeParams="none"/>" title="Account Summary">Accounts</a></td>
                <td><a href="<s:url value="/auth/Transfer.action" includeParams="none"/>" title="">Transfer Funds</a></td>
                <td><a href="<s:url value="/auth/Check.action" includeParams="none"/>" title="">Write a Check</a></td>
                <td><a href="<s:url value="/auth/PayBill.action" includeParams="none"/>" title="">Bill Pay</a></td>
<%--                <td><a href="" title="">Make Payments</a></td> --%>
                <td><a href="<s:url value="/auth/Messages.action" includeParams="none"/>" title="">Messages</a></td>
                <td><a href="<s:url value="/auth/ChangePass.action" includeParams="none"/>" title="">Change Password</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><a href="<s:url namespace="/auth/oper" action="ProfilePicture" includeParams="none"/>" title="">Picture</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr valign="top">
                <c:if test="${auth}"><td><a href="<s:url namespace="/auth/oper" action="Admin" includeParams="none"/>" title="">Admin Messaging</a>&nbsp;&nbsp;&nbsp;&nbsp;</td></c:if>
                <c:if test="${auth}"><td><a href="<s:url namespace="/auth/oper" action="Newsletter" includeParams="none"/>" title="">Mass Mailing</a>&nbsp;&nbsp;&nbsp;&nbsp;</td></c:if>
                <c:if test="${auth}"><td><a href="<s:url namespace="/auth/oper" action="AddAccount" includeParams="none"/>" title="">Create Account</a>&nbsp;&nbsp;&nbsp;</td></c:if>
                <c:if test="${auth}"><td><a href="<s:url namespace="/auth/oper" action="DeleteAccount" includeParams="none"/>" title="">Delete Account</a>&nbsp;&nbsp;&nbsp;</td></c:if>
                <c:if test="${auth}"><td><a href="<s:url namespace="/auth/oper" action="BrowseAccount" includeParams="none"/>" title="">Browse Account</a>&nbsp;&nbsp;&nbsp;</td></c:if>
                <c:if test="${auth}"><td><a href="<s:url namespace="/auth/oper" action="Files" includeParams="none"/>" title="">Files</a>&nbsp;&nbsp;&nbsp;</td></c:if>
            </tr>
        </table>
    </td>
</tr>
