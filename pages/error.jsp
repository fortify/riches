<% response.setStatus(200); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Error</title></head>
<body>
<% java.util.Date d = new java.util.Date(); %>
<% java.util.Random r = new java.util.Random(); %>
<h1>
    An Error has occurred. Please contact your sys admin. Error code: <%= r.nextInt() + "" %> . Page: ${pageContext.request.requestURI}.
</h1>
Date stamp: <%= d.toString() %>.
</body>
</html>

