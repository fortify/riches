<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Perform Transfer"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/PerformTransfer.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
