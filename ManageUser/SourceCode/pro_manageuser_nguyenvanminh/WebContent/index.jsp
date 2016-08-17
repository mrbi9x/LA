<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- Begin load text message from Properties --%>
<fmt:setBundle basename="language.Common" var="commonBundle" scope="application"/>
<fmt:setBundle basename="language.ADM001" var="ADM001Prop" scope="application"/>
<fmt:setBundle basename="language.ADM002" var="ADM002Prop" scope="application"/>
<fmt:setBundle basename="language.ADM003" var="ADM003Prop" scope="application" />
<fmt:setBundle basename="language.ADM004" var="ADM004Prop" scope="application"/>
<fmt:setBundle basename="language.ADM005" var="ADM005Prop" scope="application"/>
<%-- End load text message from Properties --%>
<jsp:forward page="jsp/ADM001.jsp" />