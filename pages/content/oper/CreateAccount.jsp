<%@ include file="../../common/moduleInclude.jsp" %>

<script type="text/javascript">
    function RestfulResource(resource_url)
    {
        this.resource_url = resource_url;
        this.xmlhttp = new XMLHttpRequest();

        this.create_json = function(jsonObject)
        {
            var jsonString = JSON.stringify(jsonObject);
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

            this.xmlhttp.open("PUT",this.resource_url,true);
            this.xmlhttp.setRequestHeader("Content-type", "application/json");
            this.xmlhttp.setRequestHeader("Content-length", jsonString.length);
            this.xmlhttp.setRequestHeader("Connection", "close");
            this.xmlhttp.send(jsonString);
        }

        this.create_xml = function(xml)
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

            this.xmlhttp.open("PUT",this.resource_url,true);
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

            //document.write(responseText);
        }

        /** The method called when the resource can't be created.*/
        this.onCreateError = function(statusText)
        {
            var labelError = document.getElementById("divError");
            labelError.innerText = statusText;
        }
    }

    function createAccount()
    {
        var baseUrl = getBaseUrl();
        var url = baseUrl +"/resources/account/add";
        var restful = new RestfulResource(url);

        var dropDownList = document.getElementById("ddCustomers");
        var intSelectedIndex = dropDownList.selectedIndex;
        var customerName=dropDownList.options[intSelectedIndex].innerHTML;

        var textControl = document.getElementById("textAmount");
        var amount = textControl.value;

        var xml = "<account><username>" + customerName + "</username><amount>" + amount + "</amount></account>";
        restful.create_xml(xml)
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
			<table cellpadding="0" cellspacing="0" class="detailBox" width="60%">
				<tr valign="top" align="center" class="titleRow">
					<td width="35%" align="left">&nbsp;&nbsp;Browse Accounts</td>
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
                                <td class="dataCell alt" align="right">Select a customer to create an account:</td>
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;
                                    <s:select list="profiles" id="ddCustomers" name="customer" listKey="username" listValue="username"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td class="dataCell alt" align="right">Amount:</td>
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;<s:textfield id="textAmount" name="amount" size="20"/>&nbsp;</td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="3" class="dataCell alt" align="center"><input type="button" value="Create" onclick="createAccount()"/></td>
                            </tr>
                            <tr valign="top">
                                <td colspan="2" class="dataCell alt" align="center"><div id="divError" style="color:red;"></div></td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                        </s:form>
                    </c:when>
                </c:choose>
			</table>
		</td>
	</tr>
