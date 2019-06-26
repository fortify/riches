<%@ include file="../common/moduleInclude.jsp" %>


<td valign="top" align="center">

    <br/>
    <b> Your career starts here...</b>
    <br/>

    <c:choose>
        <c:when test="${param.j_clerk1 != null}" >
            Junior Clerk Position 1: You will work regular business hours. Good benefit program
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${param.j_clerk2 != null}" >
            Junior Clerk Position 2: You will work regular business hours. Good benefit program
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${param.manager != null}" >
            Manager: Regular business hours. Good benefit program
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${param.s_account != null}" >
            Senior Accountant: Great pay, good benefit program
        </c:when>
    </c:choose>

    <!-- TODO HERE, cancel out the previous options instead of error. That will trigger multiple errors!!! -->
    <c:choose>
        <c:when test="${param.j_clerk1 == null && param.j_clerk2 == null && param.manager == null && param.s_account == null}" >
            No career found for you!
            <%@ include file="../../pages/career_details_error.jsp" %>
        </c:when>
    </c:choose>

    <br/>
    <br/>
    
</td>
