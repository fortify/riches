<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/basicHeader.jsp">
    <tiles:putAttribute name="pageDesc" value="Careers"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/Careers.jsp"/>
<tiles:insertTemplate template="/pages/tiles/basicFooter.jsp"/>
