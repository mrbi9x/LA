<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- Begin include header --%>
<jsp:include page="header.jsp"></jsp:include>
<%-- End include header --%>

<!-- Begin vung input-->
	<form action="DeleteUser.do" method="post" name="inputform" onsubmit="return confirm('<fmt:message key="label.confirm.delete" bundle="${ADM005Prop}" />');">
	<%-- Begin hidden field --%>
	<input type="hidden" name="userId" value="${userInfor.userId}"/>
	<%-- End hidden field --%>
	<table  class="tbl_input" border="0" width="75%"  cellpadding="0" cellspacing="0" >
		<tr>
			<th align="left">
				<div style="padding-left:100px;">
					<fmt:message key="label.message" bundle="${ADM005Prop}" />
				</div>
				<div style="padding-left:100px;">&nbsp;</div>
			</th>
		</tr>
		<tr>
			<td align="left" >
				<div style="padding-left:100px;">
					<table border="1" width="70%" class="tbl_input" cellpadding="4" cellspacing="0" >
					<tr>
						<td class="lbl_left"><fmt:message key="label.loginName" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.loginName)}</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.groupName" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.groupName)}</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.fullName" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.fullName)}</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.fullNameKana" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.fullNameKana)}</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.birthday" bundle="${ADM005Prop}" /></td>
						<td align="left">
							<fmt:formatDate type="date" value="${userInfor.birthday}" pattern="yyyy/MM/dd"/>
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.email" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.email)}</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.tel" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.tel)}</td>
					</tr>
					<tr>
						<th colspan = "2"><a href = "javascript:toggleElement('userDetailJapan')"><fmt:message key="label.message.japan" bundle="${ADM005Prop}" /></a></th>
					</tr>
					<tr class="userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.codeLevel" bundle="${ADM005Prop}" /></td>
						<td align="left">${fn:escapeXml(userInfor.nameLevel)}</td>
					</tr>
					<tr class="userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.startDate" bundle="${ADM005Prop}" /></td>
						<td align="left"><fmt:formatDate type="date" value="${userInfor.startDate}" pattern="yyyy/MM/dd"/></td>
					</tr>
					<tr class="userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.endDate" bundle="${ADM005Prop}" /></td>
						<td align="left"><fmt:formatDate type="date" value="${userInfor.endDate}" pattern="yyyy/MM/dd"/></td>
					</tr>
					<tr class="userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.total" bundle="${ADM005Prop}" /></td>
						<c:choose>
							<c:when test="${userInfor.total eq 0}">
								<td align="left"></td>
							</c:when>
							<c:otherwise>
								<td align="left">${fn:escapeXml(userInfor.total)}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<div style="padding-left:100px;">&nbsp;</div>
		<!-- Begin vung button -->
	<div style="padding-left:100px;">
	<table border="0" cellpadding="4" cellspacing="0" width="300px">
		<tr>
			<th width="200px" align="center">&nbsp;</th>
			<td>
				<input class="btn" type="button" onclick="window.location.href='AddUserInput.do?status=edit&userId=${param.userId}&sessionKey=${param.sessionKey}'" value="<fmt:message key="btn.edit" bundle="${ADM005Prop}" />" />
			</td>
			<td>
				<input class="btn" type="submit" value="<fmt:message key="btn.delete" bundle="${ADM005Prop}" />" />
			</td>
			<td>
				<input class="btn" type="button" onclick="window.location.href='ListUser.do?status=back&sessionKey=${param.sessionKey}'" value="<fmt:message key="btn.back" bundle="${ADM005Prop}" />" />
			</td>
		</tr>
	</table>
	<!-- End vung button -->
</form>
<!-- End vung input -->

<%-- Include footer --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- End include footer --%>