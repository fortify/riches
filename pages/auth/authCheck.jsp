<%
    boolean authFound = false;
    Cookie[] cookies = request.getCookies();
    if (null != cookies)
    {
        for (int i=0;i<cookies.length;i++)
        {
            if (cookies[i].getName().equals("authType"))
            {
                authFound = true;
                break;
            }
        }
    }
    if (!authFound)
    {
        Cookie authCookie = null;
        if (request.isUserInRole("admin"))
            authCookie = new Cookie("authType", "0");
        else
            authCookie = new Cookie("authType", "1");
        authCookie.setMaxAge(-1);
        authCookie.setPath(request.getContextPath());
        response.addCookie(authCookie);
    }          
%>
