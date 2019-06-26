<%@ include file="../common/moduleInclude.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>RWI - Riches Wealth International <tiles:getAsString name="pageDesc"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="<s:url value="/css/details.css" includeParams="none"/>" />
<link rel="shortcut icon" href="<s:url value="/img/favicon.ico" includeParams="none"/>" />
</head>
<body>
<br />
<table cellpadding="0" cellspacing="0" id="content" border="0" align="center">
<%@ include file="header2.jsp" %>
    <tr valign="top">
        <td align="left">
            <table cellpadding="0" cellspacing="0" id="pageTitle" border="0" width="100%">
                <tr valign="top">
                    <td colspan="2" id="summary"><tiles:getAsString name="pageDesc"/></td>
                </tr>
                <%-- <tr valign="top">
                    <td colspan="2" class="message">You have one <a href="">new message</a>.</td>
                </tr> --%>
            </table>
        </td>
    </tr>