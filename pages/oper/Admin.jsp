<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Admin Messaging Page"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/Admin.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
