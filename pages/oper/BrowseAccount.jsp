<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Browse Accounts"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/BrowseAccount.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>