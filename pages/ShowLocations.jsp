<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/basicHeader.jsp">
    <tiles:putAttribute name="pageDesc" value="Locations"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/ShowLocations.jsp"/>
<tiles:insertTemplate template="/pages/tiles/basicFooter.jsp"/>
