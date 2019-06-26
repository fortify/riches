<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Invalid Email"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/InvalidEmail.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
