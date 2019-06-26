<%@ include file="../../common/moduleInclude.jsp" %>
<tr>
    <td align="center">
        <table cellpadding="0" cellspacing="0" class="detailBox" width="45%">
            <tr valign="top" align="center" class="titleRow">
                <td width="35%" align="left">&nbsp;&nbsp;Status:</td>
            </tr>
            <tr>
                <td class="dataCell" align="center" width="100%">
                    <pre>
                    <s:fielderror/>
                    <s:actionerror/>
                    <s:actionmessage/>
                        <a href="<s:url value="/"/>profilepictures/${fileName}" title="Your Profile Picture">Your Profile Picture can be viewed here</a>
                    </pre>
                </td>
            </tr>
        </table>
        <br />
    </td>
</tr>