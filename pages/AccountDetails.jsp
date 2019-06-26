<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Account Details"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/AccountDetails.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
