<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Message Center"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/Messages.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
