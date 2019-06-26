<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Privacy, Security and Legal"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/Security.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
