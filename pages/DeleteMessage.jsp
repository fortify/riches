<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Message Deletion"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/DeleteMessage.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
