<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Message Status"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/SendMessage.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
