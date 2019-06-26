<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.sendRedirect(request.getContextPath()+"/login/Login.action");        
%>
<html>
    <head>
        <title>Login Redirect</title>
    </HEAD>
        <BODY>
        This should redirect to <a href="<%=request.getContextPath()%>/login/Login.action"><%=request.getContextPath()%>/login/Login.action</a>
        </BODY>
</html>
