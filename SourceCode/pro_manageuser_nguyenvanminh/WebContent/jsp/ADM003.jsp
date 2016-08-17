<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp"></jsp:include>

<!-- Begin vung input-->
<%-- Start get current Year, Month, Day --%>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<fmt:formatDate var="yearNow" type="date" value="${now}" pattern="yyyy"/>
<fmt:formatDate var="monthNow" type="date" value="${now}" pattern="M"/>
<fmt:formatDate var="dayNow" type="date" value="${now}" pattern="d"/>
<%-- End get current Year, Month, Day --%>

<%-- Start get Year Month Day from birthday --%>
	<fmt:formatDate var="yearUserBirthday" type="date" value="${userInfor.birthday}" pattern="yyyy"/>
	<fmt:formatDate var="monthUserBirthday" type="date" value="${userInfor.birthday}" pattern="M"/>
	<fmt:formatDate var="dayUserBirthday" type="date" value="${userInfor.birthday}" pattern="d"/>
<%-- End get Year Month Day from birthday --%>

<%-- Start get Year Month Day from startDate --%>
<c:if test="${not empty userInfor.startDate}">
	<fmt:formatDate var="yearUserStartDate" type="date" value="${userInfor.startDate}" pattern="yyyy"/>
	<fmt:formatDate var="monthUserStartDate" type="date" value="${userInfor.startDate}" pattern="M"/>
	<fmt:formatDate var="dayUserStartDate" type="date" value="${userInfor.startDate}" pattern="d"/>
</c:if>
<%-- End get Year Month Day from startDate --%>

<%-- Start get Year Month Day from endDate --%>
<c:if test="${not empty userInfor.endDate}">
	<fmt:formatDate var="yearUserEndDate" type="date" value="${userInfor.endDate}" pattern="yyyy"/>
	<fmt:formatDate var="monthUserEndDate" type="date" value="${userInfor.endDate}" pattern="M"/>
	<fmt:formatDate var="dayUserEndDate" type="date" value="${userInfor.endDate}" pattern="d"/>
</c:if>
<%-- End get Year Month Day from endDate --%>

	<form action="AddUserInput.do" method="POST" name="inputform">
	<input type="hidden" name="status" value="confirm"/>
	<input type="hidden" name="userId" value="${userInfor.userId}"/>
	<input type="hidden" name="sessionKey" value="${sessionKey}"/>
	<table  class="tbl_input"   border="0" width="75%"  cellpadding="0" cellspacing="0" >
		<tr>
			<th align="left">
				<div style="padding-left:100px;">
					<fmt:message key="label.message" bundle="${ADM003Prop}" />
				</div>
			</th>
		</tr>
		<tr>
			<td class="errMsg">
		<c:forEach items="${lsError}" var="error">
				<div style="padding-left:120px">
				${fn:escapeXml(error)}
				</div>
		</c:forEach>
		</td>
		</tr>
		<tr>
			<td align="left" >
				<div style="padding-left:100px;">
					<table border="0" width="100%" class="tbl_input" cellpadding="4" cellspacing="0" >
					<tr>
						<td class="lbl_left"><font color = "red">*</font> <fmt:message key="label.loginName" bundle="${ADM003Prop}" /></td>
						<td align="left">
							<c:choose>
								<c:when test="${not empty userInfor.userId}">
									<input class="txBox" type="text" name="loginName" value="${fn:escapeXml(userInfor.loginName)}"
										size="15" onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" readonly="readonly"/>
								</c:when>
								<c:otherwise>
									<input class="txBox" type="text" name="loginName" value="${fn:escapeXml(userInfor.loginName)}"
										size="15" onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" />
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><font color = "red">*</font> <fmt:message key="label.group" bundle="${ADM003Prop}" /></td>
						<td align="left">
							<select name=groupId>
								<option value=""><fmt:message key="comboBox.group.defaultValue" bundle="${ADM003Prop}" /></option>
								<c:forEach items="${listMstGroup}" var="group">
									<c:choose>
										<c:when test="${group.groupId eq userInfor.groupId}">
											<option value="${group.groupId}" selected>${group.groupName}</option>
										</c:when>
										<c:otherwise>
											<option value="${group.groupId}">${group.groupName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<span>&nbsp;&nbsp;&nbsp;</span>
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><font color = "red">*</font> <fmt:message key="label.fullName" bundle="${ADM003Prop}" /></td>
						<td align="left">
						<input class="txBox" type="text" name="fullName" value="${fn:escapeXml(userInfor.fullName)}"
							size="30" onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" />
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.fullNameKana" bundle="${ADM003Prop}" /></td>
						<td align="left">
						<input class="txBox" type="text" name="fullNameKana" value="${fn:escapeXml(userInfor.fullNameKana)}"
							size="30" onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" />
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><font color = "red">*</font> <fmt:message key="label.birthday" bundle="${ADM003Prop}" /></td>
						<td align="left">
						<select name="birthday">
								<c:forEach items="${listYearBirthday}" var="yearBirthday">
									<c:choose>
										<c:when test="${(not empty lsBirthday[0]) && (lsBirthday[0] eq yearBirthday) && (empty yearUserBirthday)}">
											<option value="${yearBirthday}" selected>${yearBirthday}</option>
										</c:when>
										<c:when test="${(not empty yearUserBirthday) && (yearBirthday eq yearUserBirthday)}">
											<option value="${yearBirthday}" selected>${yearBirthday}</option>
										</c:when>
										<c:when test="${(empty yearUserBirthday) && (yearBirthday eq yearNow) && empty lsBirthday[0]}">
											<option value="${yearBirthday}" selected>${yearBirthday}</option>
										</c:when>
										<c:otherwise>
											<option value="${yearBirthday}">${yearBirthday}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>年
								<select name="birthday">
									<c:forEach items="${listMonth}" var="month">
										<c:choose>
											<c:when test="${(not empty lsBirthday) && (lsBirthday[1] eq month) && (empty monthUserBirthday)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${(not empty monthUserBirthday) && (monthUserBirthday eq month)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${(empty lsBirthday[1]) && (empty monthUserBirthday) && (month eq monthNow)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>月
								<select name="birthday">
									<c:forEach items="${listDay}" var="day" varStatus="indexDay">
										<c:choose>
											<c:when test="${(not empty lsBirthday[2]) && (lsBirthday[2] eq day) && (empty dayUserBirthday)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${(not empty dayUserBirthday) && (day eq dayUserBirthday)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${(empty lsBirthday[2]) && (empty dayUserBirthday) && (day eq dayNow)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>日
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><font color = "red">*</font> <fmt:message key="label.email" bundle="${ADM003Prop}" /></td>
						<td align="left">
							<input class="txBox" type="text" name="email" value="${fn:escapeXml(userInfor.email)}"
							size="30" onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" />
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><font color = "red">*</font><fmt:message key="label.tel" bundle="${ADM003Prop}" /></td>
						<td align="left">
						<input class="txBox" type="text" name="tel" value="${fn:escapeXml(userInfor.tel)}"
							size="30" onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" />
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><font color = "red">*</font> <fmt:message key="label.password" bundle="${ADM003Prop}" /></td>
						<td align="left">
							<input class="txBox" type="password" name="password" value=""
							size="30" onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" />
						</td>
					</tr>
					<tr>
						<td class="lbl_left"><fmt:message key="label.passwordConfirm" bundle="${ADM003Prop}" /></td>
						<td align="left">
							<input class="txBox" type="password" name="passwordConfirm" value=""
							size="30" onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" />
						</td>
					</tr>
					<tr>
						<th align="left" colspan = "2" >
								<a href = "javascript:toggleElement('userDetailJapan');"><fmt:message key="label.message.japan" bundle="${ADM003Prop}" /></a>
						</th>
					</tr>
					<!-- Start div UserDetailJapan -->
					<tr id = "userDetailJapan" class="userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.codeLevel" bundle="${ADM003Prop}" /></td>
						<td align="left">
							<select name="codeLevel">
								<option value=""><fmt:message key="comboBox.codeLevel.defaultValue" bundle="${ADM003Prop}" /></option>
								<c:forEach items="${listMstJapan}" var="japan">
									<c:choose>
										<c:when test="${japan.codeLevel eq userInfor.codeLevel}">
											<option value="${japan.codeLevel}" selected>${japan.nameLevel}</option>
										</c:when>
										<c:otherwise>
											<option value="${japan.codeLevel}">${japan.nameLevel}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr id = "userDetailJapan" class = "userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.startDate" bundle="${ADM003Prop}" /> </td>
						<td align="left">
							<select name="startDate">
								<c:forEach items="${listYearStartDateJapan}" var="yearStartDate" varStatus="index">
									<c:choose>
										<c:when test="${(not empty lsStartDate) && (lsStartDate[0] eq yearStartDate) && (empty yearUserStartDate)}">
												<option value="${yearStartDate}" selected>${yearStartDate}</option>
										</c:when>
										<c:when test="${(not empty yearUserStartDate) && (yearStartDate eq yearUserStartDate)}">
											<option value="${yearStartDate}" selected>${yearStartDate}</option>
										</c:when>
										<c:when test="${(empty yearUserStartDate) && (yearStartDate eq yearNow) && (empty lsStartDate)}">
											<option value="${yearStartDate}" selected>${yearStartDate}</option>
										</c:when>
										<c:otherwise>
											<option value="${yearStartDate}">${yearStartDate}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>年
								<select name="startDate">
									<c:forEach items="${listMonth}" var="month" varStatus="indexMonth">
										<c:choose>
											<c:when test="${(not empty lsStartDate) && (lsStartDate[1] eq month) && (empty monthUserStartDate)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${(not empty monthUserStartDate) && (monthUserStartDate eq month)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${(empty monthUserStartDate) && (month eq monthNow) && (empty lsStartDate)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>月
								<select name="startDate">
									<c:forEach items="${listDay}" var="day" >
										<c:choose>
											<c:when test="${(not empty lsStartDate) && (lsStartDate[2] eq day) && (empty dayUserStartDate)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${(not empty dayUserStartDate) && (day eq dayUserStartDate)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${(empty dayUserStartDate) && (day eq dayNow) && (empty lsStartDate)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>日
						</td>
					</tr>
					<tr id = "userDetailJapan" class = "userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.endDate" bundle="${ADM003Prop}" /> </td>
						<td align="left">
							<select name="endDate">
								<c:forEach items="${listYearEndDateJapan}" var="yearEndDate" varStatus="index">
									<c:choose>
										<c:when test="${(not empty lsEndDate) && (lsEndDate[0] eq yearEndDate) && (empty yearUserEndDate)}">
												<option value="${yearEndDate}" selected>${yearEndDate}</option>
										</c:when>
										<c:when test="${(not empty yearUserEndDate) && (yearEndDate eq yearUserEndDate)}">
											<option value="${yearEndDate}" selected>${yearEndDate}</option>
										</c:when>
										<c:when test="${(empty yearUserEndDate) && (empty lsEndDate) && (yearEndDate eq (yearNow + 1))}">
											<option value="${yearEndDate}" selected>${yearEndDate}</option>
										</c:when>
										<c:otherwise>
											<option value="${yearEndDate}">${yearEndDate}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>年
								<select name="endDate">
									<c:forEach items="${listMonth}" var="month" varStatus="indexMonth">
										<c:choose>
											<c:when test="${(not empty lsEndDate) && (lsEndDate[1] eq month) && (empty monthUserEndDate)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${(not empty monthUserEndDate) && (monthUserEndDate eq month)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${(empty monthUserEndDate) && (empty lsEndDate) &&  (month eq monthNow)}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>月
								<select name="endDate">
									<c:forEach items="${listDay}" var="day" varStatus="indexDay">
										<c:choose>
											<c:when test="${(not empty lsEndDate) && (lsEndDate[2] eq day) && (empty dayUserEndDate)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${(not empty dayUserEndDate) && (day eq dayUserEndDate)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${(empty dayUserEndDate)&& (empty lsEndDate) && (day eq dayNow)}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>日
						</td>
					</tr>
					<tr id = "userDetailJapan" class = "userDetailJapan">
						<td class="lbl_left"><fmt:message key="label.total" bundle="${ADM003Prop}" /> </td>
						<td align="left">
						<c:choose>
							<c:when test="${not empty strTotal}">
								<input class="txBox" type="text" name="total" value="${fn:escapeXml(strTotal)}"
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" />
							</c:when>
							<c:otherwise>
							<c:choose>
								<c:when test="${empty userInfor.total || userInfor.total eq 0}">
									<input class="txBox" type="text" name="total" value=""
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" />
								</c:when>
								<c:otherwise>
									<input class="txBox" type="text" name="total" value="${fn:escapeXml(userInfor.total)}"
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" />
								</c:otherwise>
							</c:choose>
							</c:otherwise>
						</c:choose>

						</td>
					</tr>
					<!-- End div UserDetailJapan -->
				</table>
				</div>
			</td>
		</tr>
	</table>
	<div style="padding-left:100px;">&nbsp;</div>
		<!-- Begin vung button -->
	<div style="padding-left:45px;">
	<table border="0" cellpadding="4" cellspacing="0" width="300px">
		<tr>
			<th width="200px" align="center">&nbsp;</th>
				<td>
					<input class="btn" type="submit" value="<fmt:message key="btn.confirm" bundle="${ADM003Prop}" />" />
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty userInfor.userId}">
							<input class="btn" type="button" onclick="window.location.href='ViewDetail.do?userId=${userInfor.userId}&sessionKey=${sessionKey}'" value="<fmt:message key="btn.back" bundle="${ADM003Prop}" />" />
						</c:when>
						<c:otherwise>
							<input class="btn" type="button" onclick="window.location.href='ListUser.do?status=back&sessionKey=${sessionKey}'" value="<fmt:message key="btn.back" bundle="${ADM003Prop}" />" />
						</c:otherwise>
					</c:choose>
				</td>
		</tr>
	</table>
	<!-- End vung button -->
</form>
<!-- End vung input -->

<%-- Begin Include footer --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- End include footer --%>