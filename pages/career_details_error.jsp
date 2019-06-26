<!-- Page is coming from pebble,  web/WEB-INF/jsp/error.jsp -->

<%@ page isErrorPage="true" %>

<div class="contentItem">

  <h1>Error when looking for the documentation</h1>
  <h2>&nbsp;</h2>

  <div class="contentItemBody">
    <fmt:message key="error.error" />

    <br /><br />
    <a href="javascript:toggleVisibility('stacktrace')"><fmt:message key="common.readMore"/></a>
    <br /><br />

    <textarea id="stacktrace" rows="10" cols="60" readonly="true">
${stackTrace}
Request URL : ${pageContext.request.requestURL}
Request URI : ${pageContext.request.requestURI}
Query string : ${pageContext.request.queryString}
External URI : ${externalUri}
Internal URI : ${internalUri}
Parameters : <c:forEach var="entry" items="${paramValues}">
<c:forEach var="value" items="${entry.value}">${entry.key} = ${value}
</c:forEach></c:forEach>
</textarea>
  </div>

</div>