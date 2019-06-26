<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Newsletter Status"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/SendNewsletter.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
