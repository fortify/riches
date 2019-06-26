<%@ include file="../common/moduleInclude.jsp" %>

                    <td valign="top" align="left" width="80%">
                        <br/><br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><label style="font-size:120%">Locations</label></b>
                        <br/><br/>
                        <table cellpadding="0" cellspacing="0" class="detailBox" align="center"  width="75%">
                            <tr valign="top" align="center">
                                <td align="center">
                                    <table cellspacing="0"  width="100%" class="subtitle">
                                            <%
                                                int size = ((java.util.List) request.getAttribute("locations")).size();
                                                request.setAttribute("size", new Integer(size));
                                            %>
                                            <c:choose>
                                                <c:when test="${size != 0}" >
                                                    <tr align="left">
                                                        <td style="font-size:125%">Address</td>
                                                        <td style="font-size:125%">Services</td>
                                                    </tr>

                                                    <c:forEach var="location" items="${locations}">
                                                        <tr class="alt">
                                                            <td style="font-size:125%">
                                                                <c:out value="${location.address}"/><br/>
                                                                <c:out value="${location.city}"/>, <c:out value="${location.state}"/><br/>
                                                                <c:out value="${location.zip}"/>
                                                            </td>
                                                            <td style="font-size:125%">
                                                                Has branch: <c:out value="${location.branch}"/><br/>
                                                                Has ATM: <c:out value="${location.atm}"/><br/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr class="alt" align="center">
                                                        <td align="center">No locations found.</td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br />

                    </td>