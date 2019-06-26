<%@ include file="../../common/moduleInclude.jsp" %>

<script type="text/javascript">
    function RestfulResource(resource_url)
    {
        this.resource_url = resource_url;
        this.xmlhttp = new XMLHttpRequest();

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
            var divAccounts = document.getElementById("divAccounts");
            var token = statusText.substr(statusText.length - 18, 18 )
            if(token == "is not a customer.")
            {
                divAccounts.innerHTML = statusText;
            }
            else
            {
                var myObject = eval("(" + statusText + ")");

                var table=document.createElement("TABLE");
                table.cellPadding=0;
                table.cellspacing=0;
                table.width = "100%";
                var tbdy=document.createElement("TBODY");
                table.appendChild(tbdy);

                var tr=document.createElement("TR");
                tr.valign = "top";
                tr.align="left";
                tbdy.appendChild(tr);

                var td=document.createElement("TD");
                td.appendChild(document.createTextNode("Customer"));
                tr.appendChild(td);

                td=document.createElement("TD");
                td.appendChild(document.createTextNode("Account Number"));
                tr.appendChild(td);

                td=document.createElement("TD");
                td.appendChild(document.createTextNode("Current Balance"));
                tr.appendChild(td);

                for(var i=0;i<myObject.length;i++)
                {
                      tr=document.createElement("TR");
                      tbdy.appendChild(tr);

                      td=document.createElement("TD");
                      //td.class="dataCell alt";
                      td.align="left";
                      td.appendChild(document.createTextNode(myObject[i].username));
                      tr.appendChild(td);

                      td=document.createElement("TD");
                      //td.class="dataCell alt";
                      td.align="left";
                      td.appendChild(document.createTextNode(myObject[i].acctno));
                      tr.appendChild(td);

                      td=document.createElement("TD");
                      //td.class="dataCell alt";
                      td.align="left";
                      td.appendChild(document.createTextNode(myObject[i].balance));
                      tr.appendChild(td);
                }

                divAccounts.innerHTML = "";
                divAccounts.appendChild(table);
            }
        }
    }

    function retrieveAccounts()
    {
        var dropDownList = document.getElementById("ddCustomers");
        var intSelectedIndex = dropDownList.selectedIndex;
        var  customerName=dropDownList.options[intSelectedIndex].innerHTML;

        var baseUrl = getBaseUrl();
        var url = baseUrl +"/resources/account/json";
        if(customerName != "[All Accounts]")
        {
            url = url +"/" + customerName ;
        }
        var restful = new RestfulResource(url);
        restful.retrieve();

        if(dropDownList.options[0].value == "Select a customer")
        {
            dropDownList.options[0] = null;
        }
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
			<table cellpadding="0" cellspacing="0" class="detailBox" width="90%">
				<tr valign="top" align="center" class="titleRow">
					<td width="35%" align="left">&nbsp;&nbsp;Browse Account</td>
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
                                <td class="dataCell alt" align="right">Select a customer:</td>
                                <td class="dataCell alt" align="left">&nbsp;&nbsp;
                                    <s:select list="profiles" id="ddCustomers" name="customer" listKey="username" listValue="username" cssStyle="width:100" onchange="retrieveAccounts()"/>
                                </td>
                                <td class="dataCell alt" width="8%">&nbsp;</td>
                            </tr>
                            <tr valign="top">
                                <td colspan="3" class="dataCell alt" align="center">
                                    <div id="divAccounts"  />
                                </td>
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
