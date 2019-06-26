<%@ include file="common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Bill Pay"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/PayBill.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
