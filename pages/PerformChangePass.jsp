<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Change the Password"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/PerformChangePass.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
