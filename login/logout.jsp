<%
    if (request.getRemoteUser() != null)
    {
        session.invalidate();
        Cookie cookie = new Cookie("authType","");
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath()+"/");
    }
%>
