<%-- set auth cookie --%>
<%@ include file="auth/authCheck.jsp" %>
<%
    response.sendRedirect(request.getContextPath()+"/auth/AccountSummary.action");
%>