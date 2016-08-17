/**
 * Copyright(C) @2016 Luvina Software Company
 * ListUserController.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.luvina.manageuser.entities.MstGroup;
import net.luvina.manageuser.entities.MstJapan;
import net.luvina.manageuser.entities.SessionData;
import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.impl.MstGroupLogicImpl;
import net.luvina.manageuser.logics.impl.MstJapanLogicImpl;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.ConfigProperties;
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.utils.MessageProperties;

/**
 *
 * ListUserController - Controller xử lý hiển thị danh sách User màn hình ADM002
 *
 * @author Nguyễn Văn Minh Servlet implementation class ListUserController
 */
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
			MstJapanLogicImpl mstJapanLogicImpl = new MstJapanLogicImpl();
			List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
			List<MstGroup> listMstGroup = new ArrayList<MstGroup>();
			List<MstJapan> listMstJapan = new ArrayList<MstJapan>();
			List<Integer> listPaging = new ArrayList<Integer>();
			HttpSession session = request.getSession();
			String message = "";
			String pageForward = "";
			String fullNameSearch = request.getParameter("fullName");

			// variable sort type Deafult
			String sortType = request.getParameter("sortType");
			String sortDetail = request.getParameter("sortDetail");
			String sortByFullName = Constant.DFAULT_SORT_BY_FULL_NAME;
			String sortByCodeLevel = Constant.DFAULT_SORT_BY_CODE_LEVEL;
			String sortByEndDate = Constant.DFAULT_SORT_BY_END_DATE;
			Integer groupId = 0;
			Integer currentPage = 0;

			// Check paging click from request
			String checkPagingClick = request.getParameter("isPagingClick");
			if (checkPagingClick != null) {
				checkPagingClick = checkPagingClick.toString();
			}
			// Check sort click from request
			String checkSortClick = request.getParameter("isSortClick");
			if (checkSortClick != null) {
				checkSortClick = checkSortClick.toString();
			}

			if (fullNameSearch != null) {
				fullNameSearch = fullNameSearch.toString();
			}
			if (request.getParameter("groupId") != null) {
				try {
					groupId = Integer.parseInt(request.getParameter("groupId")
							.toString());
				} catch (NumberFormatException nfe) {
					// TODO: handle exception
					System.out
							.println("Parse current page NumberFormatException : "
									+ nfe.getMessage());
				}
			}
			// Get data sort from request
			if (sortType != null && sortDetail != null) {
				sortType = sortType.toString();
				sortDetail = sortDetail.toUpperCase();
			}
			// Prepare data for sort List User
			if ("fullNameSort".equals(sortType) && sortDetail != null) {
				if ("ASC".equals(sortDetail.toUpperCase())
						|| "DESC".equals(sortDetail.toUpperCase())) {
					sortByFullName = sortDetail.toString();
				} else {
					sortByFullName = Constant.DFAULT_SORT_BY_FULL_NAME;
				}
			}
			if ("codeLevelSort".equals(sortType) && sortDetail != null) {
				if ("ASC".equals(sortDetail.toUpperCase())
						|| "DESC".equals(sortDetail.toUpperCase())) {
					sortByCodeLevel = sortDetail.toUpperCase();
				} else {
					sortByCodeLevel = Constant.DFAULT_SORT_BY_CODE_LEVEL;
				}
			}
			if ("endDateSort".equals(sortType) && sortDetail != null) {
				if ("ASC".equals(sortDetail.toUpperCase())
						|| "DESC".equals(sortDetail.toUpperCase())) {
					sortByEndDate = sortDetail.toUpperCase();
				} else {
					sortByEndDate = Constant.DFAULT_SORT_BY_END_DATE;
				}
			}
			// Get current page from request
			String strCurrentPage = request.getParameter("currentPage");
			if (strCurrentPage != null) {
				try {
					currentPage = Integer.parseInt(strCurrentPage);
				} catch (NumberFormatException nfe) {
					// TODO: handle exception
					System.out
							.println("Parse current page NumberFormatException : "
									+ nfe.getMessage());
				}

			}
			// trường hợp back từ các trang khác lấy dữ liệu từ session
			String strStatus = request.getParameter("status");
			if ((strStatus != null && "back".equals(strStatus.toString())
					|| "yes".equals(checkPagingClick) || "yes"
					.equals(checkSortClick))) {
				String sessionKey = request.getParameter("sessionKey");
				if (sessionKey != null && !"".equals(sessionKey)) {
					SessionData sessionData = (SessionData) session
							.getAttribute(sessionKey);
					if (sessionData != null) {
						fullNameSearch = sessionData.getKeySearchFullName();
						groupId = sessionData.getGroupId();
						if (strStatus != null
								&& "back".equals(strStatus.toString())) {
							sortType = sessionData.getSortType();
							sortByFullName = sessionData.getSortByFullName();
							sortByCodeLevel = sessionData.getSortByCodeLevel();
							sortByEndDate = sessionData.getSortByEndDate();
							currentPage = sessionData.getCurrentPage();
						}
					}
					// Remove sesssion Search
					session.removeAttribute(sessionKey);
				}
			}

			// Count total records
			int totalUser = tblUserLogicImpl.countTotalUsers(groupId,
					fullNameSearch);
			// Get limit from config
			int limit = Common.getLimit();
			// Get page ranger from config
			int pageRange = 0;
			try {
				pageRange = Integer.parseInt(ConfigProperties
						.getMessage("pageRange"));
			} catch (NumberFormatException nfe) {
				// TODO: handle exception
				System.out.println("Parse pageRange NumberFormatException : "
						+ nfe.getMessage());
			}
			// Count total page
			int totalPage = Common.countTotalPage(totalUser, limit);
			if (currentPage == 0) {
				currentPage = 1;
			}
			if (totalPage == 0) {
				currentPage = 0;
			}
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}
			// get offset from currentPage
			int offset = Common.getOffset(currentPage, limit);

			// Get list Paging
			listPaging = Common.getListPaging(totalUser, limit, currentPage);

			// Get List Users
			listUserInfor = tblUserLogicImpl.getListUser(offset, limit,
					groupId, fullNameSearch, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
			if (listUserInfor.size() == 0) {
				message = MessageProperties.getMessage("MSG005");
				// Set message
				request.setAttribute("message", message);
			}
			// Get List Group
			listMstGroup = mstGroupLogicImpl.getAllMstGroup();
			// Get list Japan Level
			listMstJapan = mstJapanLogicImpl.getAllMstJapan();
			// Begin set data to request
			// Set fullNameSearch
			request.setAttribute("fullName", fullNameSearch);
			// Set group Id
			request.setAttribute("groupId", groupId);
			// Set List UserInfor
			request.setAttribute("listUserInfor", listUserInfor);
			// Set list Group
			request.setAttribute("listGroup", listMstGroup);
			// Set list Japan
			request.setAttribute("listJapan", listMstJapan);
			// Set List Paging
			request.setAttribute("listPaging", listPaging);
			// Set currentPage
			request.setAttribute("currentPage", currentPage);
			// Set totalPage
			request.setAttribute("totalPage", totalPage);
			// Set pageRange
			request.setAttribute("pageRange", pageRange);
			// Set sortType
			request.setAttribute("sortType", sortType);
			// Set sortDetail
			request.setAttribute("sortDetail", sortDetail);
			// End set data to request

			// Đưa dữ liệu search vào session
			SessionData sessionData = new SessionData(fullNameSearch, groupId,
					sortType, sortByFullName, sortByCodeLevel, sortByEndDate,
					currentPage);
			String sessionKey = Common.getSessionKey();
			request.setAttribute("sessionKey", sessionKey);
			session.setAttribute(sessionKey, sessionData);

			pageForward = Constant.ADM002;
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher(pageForward);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("SystemError.do?type=ER013");
		}
	}

}
