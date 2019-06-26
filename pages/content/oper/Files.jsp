<%@ include file="../../common/moduleInclude.jsp" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>

    <%
        String today = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
        NumberFormat numFormat = NumberFormat.getCurrencyInstance();
    %>
	
	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" class="detailBox" width="90%">
				<tr valign="top" align="center" class="titleRow">
					<td width="35%" align="left">File Name</td>
					<td width="15%">File Type</td>
					<td width="30%">Level</td>
				</tr>
            <tr valign="top">
               <td class="dataCell alt" align="center">Design Doc</td>
               <td class="dataCell alt" align="center">Word</td>
               <td class="dataCell alt" align="center">unclassified</td>
            </tr>
            <tr valign="top">
               <td class="dataCell alt" align="center"><a href="../../pages/FilesViewer.jsp">Trade Secrets</a></td>
               <td class="dataCell alt" align="center">Text</td>
               <td class="dataCell alt" align="center">top secret</td>
            </tr>

         </table>
			<br />
		</td>
	</tr>