<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp"></jsp:include>
<!-- Begin vung dieu kien tim kiem -->
<form action="ListUser.do" method="POST" name="mainform">
	<!-- Begin Tag hidden -->
	<input type="hidden" name="currentPage" value="1" />
	<input type="hidden" name="isPagingClick" value="" />
	<input type="hidden" name="isSortClick" value="" />
	<input type="hidden" name="sortType" value="${sortType}" />
	<input type="hidden" name="sortDetail" value="${sortDetail}" />
	<input type="hidden" name="sessionKey" value="${sessionKey}" />
	<!-- End Tag hidden -->
<table class="tbl_input" border="0" width="90%" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><fmt:message key="label.message" bundle="${ADM002Prop}" /></td>
	</tr>
	<tr>
		<td width="100%">
		<table class="tbl_input" cellpadding="4" cellspacing="0">
			<tr>
				<td class="lbl_left"><fmt:message key="label.searchFullName" bundle="${ADM002Prop}" /></td>
				<td align="left"><input class="txBox" type="text"
					name="fullName"
					value="${fn:escapeXml(fullName)}" size="20"
					onfocus="this.style.borderColor='#0066ff';"
					onblur="this.style.borderColor='#aaaaaa';" /></td>
				<td></td>
			</tr>
			<tr>
				<td class="lbl_left"><fmt:message key="label.searchGroup" bundle="${ADM002Prop}" /></td>
				<td align="left" width="80px"><select name="groupId">
					<option value="0"><fmt:message key="comboBox.deafultValue" bundle="${ADM002Prop}" /></option>
					<c:forEach var="group" items="${listGroup}">
						<c:choose>
							<c:when test="${groupId eq group.groupId}">
								<option value="${fn:escapeXml(group.groupId)}" selected>${fn:escapeXml(group.groupName)}</option>
							</c:when>
							<c:otherwise>
								<option value="${fn:escapeXml(group.groupId)}">${fn:escapeXml(group.groupName)}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select></td>
				<td align="left">
				<input class="btn" type="submit" value="<fmt:message key="btn.search" bundle="${ADM002Prop}" />" />
				<input class="btn" type="button" onclick="window.location.href='AddUserInput.do?sessionKey=${sessionKey}'" value="<fmt:message key="btn.add" bundle="${ADM002Prop}" />" />
				<input class="btn" type="button" onclick="window.location.href='ViewReport.do'" value="<fmt:message key="btn.viewReport" bundle="${ADM002Prop}" />" />
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
<!-- End vung dieu kien tim kiem -->
<form action="ExportCSV.do" method="get" name="frmExport" onsubmit="return validateExport(this);">
<table>
	<tr>
		<td colspan="8">
			<td>&nbsp;</td>

		</td>
	</tr>
	<tr>
		<td>
			<input type="checkbox" name = "ALL" value="ALL"/>ALL
		</td>
		<c:forEach items="${listJapan}" var="japan">
			<td>
				<input type="checkbox" name = "japanLevel" value="${fn:escapeXml(japan.codeLevel)}" />${fn:escapeXml(japan.codeLevel)}
			</td>
		</c:forEach>
		<td>
			<td>&nbsp;</td>
		</td>
		<td>
			<input type = "submit" value="<fmt:message key="btn.exportCsv" bundle="${ADM002Prop}" />" class="btn btn_wider" />
		</td>

	</tr>
	<tr>
		<td class="errMsg" colspan="7"><label id="errMsgExport" ></label></td>
	</tr>
</table>
</form>
<!-- Begin vung hien thi danh sach user -->
<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
	width="80%">
	<c:choose>
		<c:when test="${not empty message}">
			<tr>
				<td class="errMsg" colspan="9">${message}</td>
			</tr>
		</c:when>
		<c:otherwise>

	<tr class="tr2">
		<th align="center" width="20px"><fmt:message key="label.id" bundle="${ADM002Prop}" /></th>
		<th align="left"><fmt:message key="label.fullName" bundle="${ADM002Prop}" />
		<c:choose>
			<c:when
				test="${param.sortType == 'fullNameSort' and param.sortDetail == 'DESC'}">
				<a
					href="javascript:sort('ListUser.do', 'fullNameSort', 'ASC')">△▼</a>
			</c:when>
			<c:otherwise>
				<a
					href="javascript:sort('ListUser.do', 'fullNameSort', 'DESC')">▲▽</a>
			</c:otherwise>
		</c:choose></th>
		<th align="left"><fmt:message key="label.birthday" bundle="${ADM002Prop}" /></th>
		<th align="left"><fmt:message key="label.group" bundle="${ADM002Prop}" /></th>
		<th align="left"><fmt:message key="label.email" bundle="${ADM002Prop}" /></th>
		<th align="left" width="70px"><fmt:message key="label.phone" bundle="${ADM002Prop}" /></th>
		<th align="left"><fmt:message key="label.codeLevel" bundle="${ADM002Prop}" /> <c:choose>
			<c:when
				test="${param.sortType == 'codeLevelSort' and param.sortDetail == 'ASC'}">
				<a
					href="javascript:sort('ListUser.do', 'codeLevelSort', 'DESC')">▲▽</a>
			</c:when>
			<c:otherwise>
				<a
					href="javascript:sort('ListUser.do', 'codeLevelSort', 'ASC')">△▼</a>
			</c:otherwise>
		</c:choose></th>
		<th align="left"><fmt:message key="label.endDate" bundle="${ADM002Prop}" /> <c:choose>
			<c:when
				test="${param.sortType == 'endDateSort' and param.sortDetail == 'ASC'}">
				<a
					href="javascript:sort('ListUser.do', 'endDateSort', 'DESC')">▲▽</a>
			</c:when>
			<c:otherwise>
				<a
					href="javascript:sort('ListUser.do', 'endDateSort', 'ASC')">△▼</a>
			</c:otherwise>
		</c:choose></th>
		<th align="left"><fmt:message key="label.total" bundle="${ADM002Prop}" /></th>
	</tr>
	<c:forEach var="userInfor" items="${listUserInfor}">
		<tr>
			<td align="right"><a href="ViewDetail.do?userId=${userInfor.userId}&sessionKey=${sessionKey}">${fn:escapeXml(userInfor.userId)}</a>
			</td>
			<td>${fn:escapeXml(userInfor.fullName)}</td>
			<td align="center"><fmt:formatDate pattern="yyyy/MM/dd" value="${userInfor.birthday}"/></td>
			<td>${fn:escapeXml(userInfor.groupName)}</td>
			<td>${fn:escapeXml(userInfor.email)}</td>
			<td>${fn:escapeXml(userInfor.tel)}</td>
			<td>${fn:escapeXml(userInfor.nameLevel)}</td>
			<td align="center"><fmt:formatDate pattern="yyyy/MM/dd" value="${userInfor.endDate}"/></td>
			<c:choose>
				<c:when test="${empty userInfor.total or userInfor.total eq 0}">
					<td align="right"></td>
				</c:when>
				<c:otherwise>
					<td align="right">${userInfor.total}</td>
				</c:otherwise>
			</c:choose>

		</tr>
	</c:forEach>
</table>
<!-- End vung hien thi danh sach user -->

<!-- Begin vung paging -->
<table>
	<tr>
		<c:forEach var="pageId" items="${listPaging}" varStatus="pageIndex">
			<c:if test="${pageIndex.first and pageId > pageRange}">
				<td class="lbl_paging"><a
					href="javascript:paging('ListUser.do',${pageId - pageRange})">&laquo;</a></td>
			</c:if>
			<c:choose>
				<c:when test="${fn:length(listPaging) gt 1}">
					<c:choose>
						<c:when test="${currentPage eq pageId}">
							<td class="lbl_paging">${pageId}</td>
						</c:when>
						<c:otherwise>
							<td class="lbl_paging"><a
								href="javascript:paging('ListUser.do',${pageId})">${pageId}</a></td>
						</c:otherwise>
					</c:choose>
					<c:if test="${pageIndex.last and pageId < totalPage}">
						<td class="lbl_paging"><a
							href="javascript:paging('ListUser.do',${pageId + 1})">&raquo;</a></td>
					</c:if>
				</c:when>
				<c:when
					test="${(fn:length(listPaging) eq 1) and (pageId gt pageRange)}">
					<td class="lbl_paging">${pageId}</td>
				</c:when>
			</c:choose>

		</c:forEach>
	</tr>
	</c:otherwise>
	</c:choose>
</table>
<!-- End vung paging -->

<%-- Include footer --%>
<jsp:include page="footer.jsp"></jsp:include>
<%-- End include footer --%>