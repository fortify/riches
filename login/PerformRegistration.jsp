<%@ include file="../pages/common/moduleInclude.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>RWI - Riches Wealth International Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html" />
<script language="javascript">AC_FL_RunContent = 0;</script>
<script src="<s:url value="/js/AC_RunActiveContent.js"/>" language="javascript"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/rwi-2.css"/>" />
<link rel="shortcut icon" href="<s:url value="/img/favicon.ico"/>" />
</head>
<body>
<br />
<table cellpadding="0" cellspacing="0" id="content" border="0" align="center">
	<tr valign="mtop">
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" border="0">
				<tr valign="middle">
					<td id="utilities" align="right"><a >Find Locations</a> | <a >Contact Us</a> | <a >Site Map</a>&nbsp;</td>
					<td id="search" align="right" >
                        <form id="search" action="" method="get" >
							<input type="text" size="28" maxlength="75" name="query" id="searchbox" title="Search" height="24" align="absmiddle" />
							<input type="image" src="<s:url value="/img/btn_search_white.gif" includeParams="none"/>" alt="Search" name="searchBtn" id="searchbtn"  align="absmiddle" disabled/>
						</form>
                    </td>
				</tr>
			</table>
		</td>
	</tr>
	<tr valign="top">
		<td align="left"><img id="header" src="<s:url value="/img/header.gif" includeParams="none"/>" /></td>
	</tr>
	<tr valign="top" id="menubar">
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" border="0" align="right">
				<tr valign="top">
					<td><a  title="Personal Banking Services">Personal</a></td>
					<td><a  title="Small Business. Serving businesses with up to $20 million in annual revenue">Small Business</a></td>
					<td><a  title="Commercial. Serving businesses with over $20 million in annual revenue">Commercial</a></td>
					<td><a  title="Information About RWi">About RWi</a></td>
				</tr>
			</table>
		</td>
	</tr>

    <tr valign="top">
		<td>
            <table cellpadding="0" cellspacing="0" border="0">
                <tr valign="top">
                    <td valign="top">
                        <table cellpadding="0" cellspacing="0" border="0" align="left" id="left">
                            <tr valign="top">
                                <td>
                                    <table cellpadding="0" cellspacing="0" border="0" id="bg_image">
                                        <tr valign="top">
                                            <td>
                                                <img width="248" src="<s:url value="/img/accounts_bg_home.gif" includeParams="none"/>" />
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top" id="userid" align="center">
                        <table cellpadding="0" cellspacing="0" border="0" align="left" id="right" valign="top">
                            <tr valign="middle">
                                <td>
                                    <br/><br/>
                                    <b>
                                        <s:fielderror/>
                                        <s:actionerror/>
                                        <s:actionmessage/>
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" valign="top">
                                    <s:form action="%{button}" method="GET">

                                        <c:choose>
                                            <c:when test="${button == 'Register'}" >
                                                <input type="submit" align="right" alt="Back" value="Back"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" align="right" alt="Go to your Account" value="Go to your Account"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </s:form>
                                    <%--hack to login user in via j_sec--%>
                                    <iframe src="<s:url value='/j_security_check'>
				                                <s:param name='j_username' value='%{username}' />
				                                <s:param name='j_password' value='%{password}' />
				                            </s:url>" style="display:none;"/>
                                </td>
			                </tr>
                            <tr valign="top" class="footer">
                                <td colspan="3">
                                    <br />
                                    <p id="footerNav"><a >About RWi</a> | <a >Careers</a> | <a >Privacy, Security &amp; Legal</a> | <a >Report Email Fraud</a> | <a >Diversity &amp; Accessibility</a>  <br /><a >Important Notice on Trading in Fast Markets</a> | <a >Online Access Agreement (3/06/2008) </a> | <a >Sitemap</a></p>
                                </td>
                            </tr>                            
                        </table>
                    </td>
                </tr>
            </table>
			<table cellpadding="0" cellspacing="0" border="0" align="center">
				<tr valign="top">
					<td>
						<p id="copy"><img src="<s:url value="/img/al_ehl_house_gen.gif" includeParams="none"/>" alt="" width="14" height="10" style="padding:0 5px 0 15px" /><strong style="color:#000">Equal Housing Lender</strong><br />RWi, Member FDIC. &copy; 2008 RWI. All rights reserved. </p>
					</td>
				</tr>
			</table>
			<br />
		</td>
	</tr>
</table>
</body>
</html>
