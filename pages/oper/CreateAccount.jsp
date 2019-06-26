<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Create Account"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/CreateAccount.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>