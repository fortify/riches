<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Upload Profile Picture"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/UploadProfilePicture.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
