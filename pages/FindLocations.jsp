<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/basicHeader.jsp">
    <tiles:putAttribute name="pageDesc" value="Find Locations"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/FindLocations.jsp"/>
<tiles:insertTemplate template="/pages/tiles/basicFooter.jsp"/>
