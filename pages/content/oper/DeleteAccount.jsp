<%@ include file="../../common/moduleInclude.jsp" %>

<script type="text/javascript">
    function RestfulResource(resource_url)
    {
        this.resource_url = resource_url;
        this.xmlhttp = new XMLHttpRequest();

        this.delete_account = function(acctno)
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

            this.xmlhttp.open("DELETE",this.resource_url + "/" + acctno,true);
            this.xmlhttp.setRequestHeader("Content-type", "application/xml");
            this.xmlhttp.setRequestHeader("Content-length", acctno.length);
            this.xmlhttp.setRequestHeader("Connection", "close");
            this.xmlhttp.send(acctno);
        }

        this.retrieve = function()
        {
            var self = this;
            this.xmlhttp.onreadystatechange=function()
            {
                if (self.xmlhttp.readyState==4)
                {
                    if (self.xmlhttp.status==200)
                    {
                        self.onRetrieveSuccess.call(self,self.xmlhttp.responseText);
                    }
                    else
                    {
                        self.onCreateError.call(self,self.xmlhttp.statusText);
                    }
                }
            }

            this.xmlhttp.open("GET",this.resource_url,true);
            this.xmlhttp.send(null);
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

        this.onRetrieveSuccess = function(statusText)
        {
            var myObject = eval("(" + statusText + ")");
            var ddAccounts = document.getElementById("ddAccounts");
            ddAccounts.length = 0;
            for( i=0;i<myObject.length;i++)
            {
                ddAccounts.options[ddAccounts.length] = new Option(myObject[i].acctno)
            }
        }
    }

    function retrieveAccounts()
    {
        var dropDownList = document.getElementById("ddCustomers");
        var intSelectedIndex = dropDownList.selectedIndex;
        var customerName=dropDownList.options[intSelectedIndex].innerHTML;
        var baseUrl = getBaseUrl();
        var url = baseUrl +"/resources/account/json/" + customerName ;
        var restful = new RestfulResource(url);
        restful.retrieve();
    }

    function deleteAccount()
    {
        var baseUrl = getBaseUrl();
        var url = baseUrl +"/resources/account/delete";
        var restful = new RestfulResource(url);

        var dropDownList = document.getElementById("ddAccounts");
        var intSelectedIndex = dropDownList.selectedIndex;
        var acctno=dropDownList.options[intSelectedIndex].innerHTML;

        restful.delete_account(acctno)
    }


    function getBaseUrl()
    {
        var url = window.location.href;
        var index = url.indexOf("riches");
        return url.substring(0, index+6);
    }

    window.onload = retrieveAccounts;

</script>

	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" class="detailBox" width="45%">
				<tr valign="top" align="center" class="titleRow">
					<td width="35%" align="left">&nbsp;&nbsp;Delete Account</td>
					<td width="20%">&nbsp;&nbsp;</td>
                    <td width="8%">&nbsp;&nbsp;</td>
				</tr>

                <%
                    int size = ((java.util.List)request.getAttribute("profiles")).size();
                    request.setAttribute("size", new Integer(size));
                %>

                <c:choose>
                    <c:when test="${size > 1}" >
                        <s:form theme="simple">
                            <tr valign="top">
                                <td class="dataCell alt" align="right">select a customer:</td>
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;
                                    <s:select list="profiles" id="ddCustomers" name="customer" listKey="username" listValue="username" cssStyle="width:100"  onchange="retrieveAccounts()"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td class="dataCell alt" align="right">select an account:</td>
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;
                                    <select id="ddAccounts" name="Account" cssStyle="width:100"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="3" class="dataCell alt" align="center"><input type="button" value="Delete" onclick="deleteAccount()"/></td>
                            </tr>
                            <tr valign="top">
                                <td colspan="2" class="dataCell alt"><div id="divError" style="color:red;"></div></td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                        </s:form>
                    </c:when>
                </c:choose>
			</table>
		</td>
	</tr>

