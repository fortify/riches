<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.sendRedirect(request.getContextPath()+"/login/error.action");
%>
<html>
    <head>
        <title>Login Redirect</title>
    </HEAD>
        <BODY>
        This should redirect to <a href="<%=request.getContextPath()%>/login/error.action"><%=request.getContextPath()%>/login/error.action</a>
        </BODY>
</html>
