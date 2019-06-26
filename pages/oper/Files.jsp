<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Files Page"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/Files.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
