<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp"></jsp:include>

<!-- Begin vung dieu kien tim kiem -->
<form action="ListUser.do" method="POST" name="mainform">
<table class="tbl_input" border="0" width="90%" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>会社でのユーザーの統計</td>
	</tr>
</table>
<!-- Begin vung hien thi danh sach user -->
<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
	width="80%">
	<c:choose>
		<c:when test="${not empty message}">
			<tr>
				<td class="errMsg" colspan="8">${message}</td>
			</tr>
		</c:when>
		<c:otherwise>
	<tr class="tr2">
		<th align="center">総ユーザー </th>
		<th align="center">総利用者は日本語を知っている  </th>
		<th align="center">日本人以外のユーザーの合計数</th>
		<th align="center">資格のあるユーザーの合計数N1</th>
		<th align="center">資格のあるユーザーの合計数N2</th>
		<th align="center">資格のあるユーザーの合計数N3</th>
		<th align="center">資格のあるユーザーの合計数N4</th>
		<th align="center">資格のあるユーザーの合計数N5</th>
	</tr>
		<tr>
			<td align="right">${tblReport.totalUser}</td>
			<td align="right">${tblReport.totalUserN}</td>
			<td align="right">${tblReport.totalUserN0}</td>
			<td align="right">${tblReport.totalUserN1}</td>
			<td align="right">${tblReport.totalUserN2}</td>
			<td align="right">${tblReport.totalUserN3}</td>
			<td align="right">${tblReport.totalUserN4}</td>
			<td align="right">${tblReport.totalUserN5}</td>
		</tr>
	</c:otherwise>
	</c:choose>
</table>
<table class="tbl_input" border="0" width="80%" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center"><input class="btn" type="submit" value="<fmt:message key="btn.ok" bundle="${commonBundle}" />"/></td>
	</tr>
</table>

</form>
<%-- Include footer --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- End include footer --%>