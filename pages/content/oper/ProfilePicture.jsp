<%@ include file="../../common/moduleInclude.jsp" %>


<tr>
    <td>
        <table cellpadding="0" cellspacing="0" class="messageBox" width="60%" align="center">

            <s:form action="UploadProfilePicture" method="post" enctype="multipart/form-data" theme="simple">
                <tr valign="top" class="titleRow">
                    <td  align="left">&nbsp;&nbsp;Upload your new profile picture:</td>
                </tr>
                <tr class="subtitle">
                    <td colspan="2" align="left">
                        <table cellpadding="0" cellspacing="0"><tr ><td style="border:0px" width="50px"></td><td style="border:0px"> <s:file name="upload" label="File" size="30"/> </td></tr></table>
                    </td>
                </tr>

                <tr valign="top">
                    <td align="left">&nbsp;<s:submit /></td>
                </tr>
            </s:form>

        </table>
        <br>
    </td>
</tr>