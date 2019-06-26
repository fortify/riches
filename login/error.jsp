<%
    String error = "Invalid credentials for " + request.getParameter("j_username");
    response.sendRedirect(request.getContextPath()+"/login/Login.action?errorMsg="+error);
%>

<html>
<head>
<title>Bad credentials</title>
</head>
<body bgcolor="white">
Bad credentials for : <%=request.getParameter("j_username")%><br>
Try <a href='<%= request.getContextPath() %>'>again</a>.
</body>
</html>
