<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Transfer Funds"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/Transfer.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
