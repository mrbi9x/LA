<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--<script src="https://code.jquery.com/jquery-1.9.0.js"></script>-->
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<!--<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
--><script type="text/javascript" src="js/user.js"></script>
<!-- Begin load text message from Properties -->

<!-- End load text message from Properties -->
<title>
	<fmt:message key="title" bundle="${commonBundle}" />
</title>
</head>
<body>
	<!-- Begin vung header -->
		<div>
			<div>
			<table>
			<tr>
			<td width = "80%"><img src="images/logo-manager-user.gif" alt="Luvina" /><td>
			<td align="left"><a href = "Logout.do"><fmt:message key="link.logout" bundle="${commonBundle}" /></a> &nbsp; <a href = "ListUser.do"><fmt:message key="link.top" bundle="${commonBundle}" /></a><td>
			</tr>
			</table>
			</div>
		</div>

<!-- End vung header -->