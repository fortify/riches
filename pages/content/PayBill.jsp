<%@ include file="../common/moduleInclude.jsp" %>

<style type="text/css">
textarea { margin-top:.18em; background-color:#ffc; }
</style>

<script type="text/javascript">
    function RestfulResource(resource_url)
    {
        this.resource_url = resource_url;
        this.xmlhttp = new XMLHttpRequest();

        this.pay_bill = function(xml)
        {
            var self = this;
            this.xmlhttp.onreadystatechange=function()
            {
                if (self.xmlhttp.readyState==4)
                {
                    if (self.xmlhttp.status==200)
                    {
                        self.onCreateSuccess.call(self,self.xmlhttp.responseText);
                    }
                    else
                    {
                        self.onCreateError.call(self,self.xmlhttp.statusText);
                    }
                }
            }

            this.xmlhttp.open("POST",this.resource_url,true);
            this.xmlhttp.setRequestHeader("Content-type", "application/xml");
            this.xmlhttp.setRequestHeader("Content-length", xml.length);
            this.xmlhttp.setRequestHeader("Connection", "close");
            this.xmlhttp.send(xml);
        }

         /** The method called when the resource is successfully created.*/
        this.onCreateSuccess = function(responseText)
        {
            var labelError = document.getElementById("divError");
            labelError.innerHTML = responseText;
        }

        /** The method called when the resource can't be created.*/
        this.onCreateError = function(statusText)
        {
            var labelError = document.getElementById("divError");
            labelError.innerText = statusText;
        }
    }

    function paybill()
    {
        var baseUrl = getBaseUrl();
        var url = baseUrl +"/resources/account/transaction/paybill";
        var restful = new RestfulResource(url);

        var dropDownList = document.getElementById("ddAccounts");
        var intSelectedIndex = dropDownList.selectedIndex;
        var acctno=dropDownList.options[intSelectedIndex].innerHTML;

        var textControl = document.getElementById("textAmount");
        var amount = textControl.value;

        var textControl2 = document.getElementById("textPayTo");
        var payto = textControl2.value;

        var textControl3 = document.getElementById("textMemo");
        var memo = textControl3.value;

        var xml = "<bill><acctno>"+acctno+"</acctno><amount>"+amount+"</amount><payee>"+payto+"</payee><memo>"+memo+"</memo></bill>";

        restful.pay_bill(xml)
    }


    function getBaseUrl()
    {
        var url = window.location.href;
        var index = url.indexOf("riches");
        return url.substring(0, index+6);
    }

</script>

	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" class="detailBox" width="45%">
				<tr valign="top" align="center" class="titleRow">
					<td align="left">&nbsp;&nbsp;Bill Pay</td>
					<td width="20%">&nbsp;&nbsp;</td>
                    <td width="8%">&nbsp;&nbsp;</td>
				</tr>

                <%
                    int size = ((java.util.List)request.getAttribute("accounts")).size();
                    request.setAttribute("size", new Integer(size));
                %>

                <c:choose>
                    <c:when test="${size > 1}" >
                        <s:form theme="simple">
                            <tr valign="top">
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;Pay To:&nbsp;</td>
                                <td class="dataCell alt" align="left">
                                    <s:textfield id="textPayTo" name="payee" size="30"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;Amount:&nbsp;</td>
                                <td class="dataCell alt" align="left"><s:textfield id="textAmount" name="amount" size="10"/>&nbsp;</td>
                                <td class="dataCell alt" width="8%">&nbsp;&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;select an account:</td>
                                <td class="dataCell alt" align="left">
                                    <s:select list="accounts" id="ddAccounts" name="account" listKey="acctno" listValue="acctno" cssStyle="width:100"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;Memo:&nbsp;</td>
                                <td class="dataCell alt" align="left">
                                    <s:textfield id="textMemo" name="memo" size="25"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;&nbsp;</td>
                            </tr>
                            <tr valign="top">
                            <tr>
                                <td colspan="2" class="dataCell alt" align="center"><input type="button" value="Submit" onclick="paybill()"/></td>
                                <td class="dataCell alt" width="8%">&nbsp;&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td colspan="2" class="dataCell alt" align="center"><div id="divError" style="color:red;"></div></td>
                                <td class="dataCell alt" width="8%">&nbsp;&nbsp;</td>
                            </tr>
                        </s:form>
                    </c:when>
                </c:choose>
		    </table>
			<br />
		</td>
	</tr>
