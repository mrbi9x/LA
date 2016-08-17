<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<title>
	<fmt:message key="title" bundle="${ADM001Prop}" />
</title>
</head>
<body align="center" >
<form action="Login.do" method="post">
<center>
	<table class="tbl_input" cellpadding="4" cellspacing="0" width="400px">
		<tr>
			<th width="120px">&nbsp;</th><th></th>
		</tr>
		<tr>
			<th colspan="2" align="left">
			<fmt:message key="label.message" bundle="${ADM001Prop}" />
			</th>
		</tr>
		<!-- Begin listErrorMessage -->
		<c:if test="${not empty lsErrorMessage}">
				<c:forEach items="${lsErrorMessage}" var="errorMessage">
				<tr>
					<td class="errMsg" colspan="2">${errorMessage}</td>
				</tr>
				</c:forEach>
		</c:if>
		<!-- End listErrorMessage -->
		<tr align="left">
			<td class="lbl_left"><fmt:message key="label.loginName" bundle="${ADM001Prop}" /></td>
			<td align="left">
				<input class="txBox" type="text" name="loginId" value="${fn:escapeXml(loginId)}" size="22" onfocus="this.style.borderColor='#0066ff';"
				onblur="this.style.borderColor='#aaaaaa';" />
			</td>
		</tr>
		<tr>
			<td class="lbl_left"><fmt:message key="label.password" bundle="${ADM001Prop}" /></td>
			<td align="left">
				<input class="txBox" type="password" name="password" value=""	size="22" onfocus="this.style.borderColor='#0066ff';"
				onblur="this.style.borderColor='#aaaaaa';" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="left">
				<input class="btn btn_wider" type="submit" value="<fmt:message key="btn.login" bundle="${ADM001Prop}" />" />
			</td>
		</tr>
	</table>
	</center>
</form>
</body>
</html>