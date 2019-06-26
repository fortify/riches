<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Write a Check"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/Check.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
