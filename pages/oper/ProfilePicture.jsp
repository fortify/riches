<%@ include file="../common/moduleInclude.jsp" %>

<tiles:insertTemplate template="/pages/tiles/header.jsp">
    <tiles:putAttribute name="pageDesc" value="Change your profile picture"/>
</tiles:insertTemplate>
<tiles:insertTemplate template="/pages/content/oper/ProfilePicture.jsp"/>
<tiles:insertTemplate template="/pages/tiles/footer.jsp"/>
