<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Mass Messaging Page: Newsletters and other"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/Newsletter.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
