<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/basicHeader.jsp">
    <tiles:putAttribute name="pageDesc" value="CareerDetails"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/CareerDetails.jsp"/>
<tiles:insertTemplate template="/pages/tiles/basicFooter.jsp"/>
