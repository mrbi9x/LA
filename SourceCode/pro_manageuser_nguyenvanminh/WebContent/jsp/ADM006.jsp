<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- Begin include header --%>
<jsp:include page="header.jsp"></jsp:include>
<%-- End include header --%>
<!-- Begin vung input-->
	<form action="ListUser.do?status=back&sessionKey=${param.sessionKey}" method="post" name="inputform">
	<table  class="tbl_input"   border="0" width="80%"  cellpadding="0" cellspacing="0" >
		<tr>
			<td align="center" colspan="2">
				<div style="height:50px"></div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				${fn:escapeXml(message)}
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<div style="height:70px"></div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input class="btn" type="submit" value="<fmt:message key="btn.ok" bundle="${commonBundle}" />" onclick=""/>
			</td>
		</tr>
	</table>
	</form>
<!-- End vung input -->

<%-- Include footer --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- End include footer --%>