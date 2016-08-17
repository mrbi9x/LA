/**
 * Copyright(C) @2016 Luvina Software Company
 * AddUserInputController.java, Jun 21, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.validates.ValidateUser;

/**
 *
 * AddUserInputController - Controller xử lý các logic của màn hình ADM003
 * @author Nguyễn Văn Minh
 * Servlet implementation class AddUserInputController
 */
public class AddUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserInputController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set SessionKey to Request
		request.setAttribute("sessionKey", request.getParameter("sessionKey"));
		setDataLogic(request, response);
		UserInfor userInfor = setDefaultValue(request, response);
		if (userInfor != null) {
			request.setAttribute("userInfor", userInfor);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.ADM003);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("SystemError.do?type=ER013");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> lsError = new ArrayList<String>();
		UserInfor userInfor = new UserInfor();
		MstGroup mstGroup = new MstGroup();
		MstJapan mstJapan = new MstJapan();
		setDataLogic(request, response);
		userInfor = setDefaultValue(request, response);
		lsError = ValidateUser.validateUserInfor(userInfor);
		// Lấy sesionSearch
		String sessionKey = request.getParameter("sessionKey");
		if (lsError.size() > 0) {
			// Set data to request
			request.setAttribute("userInfor", userInfor);
			request.setAttribute("lsError", lsError);
			// Set SessionKey to Request
			request.setAttribute("sessionKey", sessionKey);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.ADM003);
			dispatcher.forward(request, response);
		} else {
			//Prepare UserInfor set to session
			// Get groupName by groupId
			MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
			mstGroup = mstGroupLogicImpl.getMstGroupByGroupId(userInfor.getGroupId());
			userInfor.setGroupName(mstGroup.getGroupName());
			// Get JapanLevel by codeLevel
			if (!"".equals(userInfor.getCodeLevel())) {
				MstJapanLogicImpl mstJapanLogicImpl = new MstJapanLogicImpl();
				mstJapan = mstJapanLogicImpl.getMstJapanByCodeLevel(userInfor.getCodeLevel());
				userInfor.setNameLevel(mstJapan.getNameLevel());
			}
			//Handle session
			HttpSession session = request.getSession();
			SessionData sessionData = (SessionData) session.getAttribute(sessionKey);
			if (sessionData != null) {
				sessionData.setUserInfor(userInfor);
				session.setAttribute(sessionKey, sessionData);
			}
			//redirect to page confirm
			response.sendRedirect(Constant.ADD_USER_CONFIRM + "?sessionKey=" + sessionKey);
		}
	}

	/**
	 * setDataLogic - Thực hiện set giá trị cho các hạng mục selectbox ở màn hình ADM003
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	private void setDataLogic(HttpServletRequest request, HttpServletResponse response) {

		List<Integer> listYearBirthday = new ArrayList<Integer>();
		List<Integer> listYearStartDateJapan = new ArrayList<Integer>();
		List<Integer> listYearEndDateJapan = new ArrayList<Integer>();
		List<Integer> listMonth = new ArrayList<Integer>();
		List<Integer> listDay = new ArrayList<Integer>();

		// Prepare data set to Request
		// Get all group
		MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
		List<MstGroup> listMstGroup = new ArrayList<MstGroup>();
		listMstGroup = mstGroupLogicImpl.getAllMstGroup();
		// Get all Japan
		MstJapanLogicImpl mstJapanLogicImpl = new MstJapanLogicImpl();
		List<MstJapan> listMstJapan = new ArrayList<MstJapan>();
		listMstJapan = mstJapanLogicImpl.getAllMstJapan();
		// Get year Now
		int yearNow = Common.getYearNow();
		// Get List Year for Birthday
		listYearBirthday = Common.getListYear(1980, yearNow);
		// Get List Year for Start Date Japan
		listYearStartDateJapan = Common.getListYear(2000, yearNow);
		// Get List Year for End Date Japan
		listYearEndDateJapan = Common.getListYear(2000, yearNow + 1);
		// Get list Month
		listMonth = Common.getListMonth();
		// Get list Day
		listDay = Common.getListDay();
		// Start data set to Request
		request.setAttribute("listMstGroup", listMstGroup);
		request.setAttribute("listMstJapan", listMstJapan);
		request.setAttribute("listYearBirthday", listYearBirthday);
		request.setAttribute("listYearStartDateJapan", listYearStartDateJapan);
		request.setAttribute("listYearEndDateJapan", listYearEndDateJapan);
		request.setAttribute("listMonth", listMonth);
		request.setAttribute("listDay", listDay);
	}

	/**
	 * Set giá trị default cho màn hình ADM003
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return UserInfor userInfor đối tượng chứa thông tin của màn hình ADM003
	 */
	private UserInfor setDefaultValue (HttpServletRequest request, HttpServletResponse response) {
		UserInfor userInfor = null;
		HttpSession session = null;
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		String status = request.getParameter("status");
		// Trường hợp edit
		if (status != null && "edit".equals(status)) {
			String strUserId = request.getParameter("userId");
			try {
				Integer userId = Integer.parseInt(strUserId);
				userInfor = tblUserLogicImpl.getUserInforByUserId(userId);
			} catch (NumberFormatException nfe) {
				// TODO: handle exception
				userInfor = null;
			}
		// Trường hợp back
		} else if (status != null && "back".equals(status)) {
			userInfor = new UserInfor();
			String sessionKey = request.getParameter("sessionKey");
			session = request.getSession();
			SessionData sessionData = (SessionData) session.getAttribute(sessionKey);
			if (sessionData != null) {
				userInfor = sessionData.getUserInfor();
				sessionData.setUserInfor(new UserInfor());
			} else {
				userInfor = null;
			}
		// Trường hợp bấm xác nhận
		} else if (status != null && "confirm".equals(status)) {
			String strUserId = request.getParameter("userId");
			Integer userId = null;
			String loginName = request.getParameter("loginName");
			String strGroupId = request.getParameter("groupId");
			int groupId = 0;
			String groupName = "";
			String fullName = request.getParameter("fullName");
			String fullNameKana = request.getParameter("fullNameKana");
			Date birthday = null;
			String[] strArrBirthday = request.getParameterValues("birthday");
			List<Integer> lsIntBirthday = new ArrayList<Integer>();
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("passwordConfirm");
			String codeLevel = request.getParameter("codeLevel");
			String nameLevel = "";
			Date startDate = null;
			String[] strArrStartDate = request.getParameterValues("startDate");
			List<Integer> lsIntStartDate = new ArrayList<Integer>();
			Date endDate = null;
			String[] strArrEndDate = request.getParameterValues("endDate");
			List<Integer> lsIntEndDate = new ArrayList<Integer>();
			String strTotal = request.getParameter("total");
			int total = 0;

			if (strUserId != null && !"".equals(strUserId)) {
				try {
					userId = Integer.parseInt(strUserId);
				} catch (NumberFormatException nfe) {
					// TODO: handle exception
					System.out.println("AddUserInputController - setDefaultValue - userId - NumberFormatException " + nfe.getMessage());
				}
			}

			if (loginName != null && !"".equals(loginName)) {
				loginName = loginName.trim().toString();
			}
			if (strGroupId != null && !"".equals(strGroupId)) {
				try {
					groupId = Integer.parseInt(strGroupId);
				} catch (NumberFormatException nfe) {
					// TODO: handle exception
					System.out.println("AddUserInputController - setDefaultValue - NumberFormatException " + nfe.getMessage());
				}
			}
			if (fullName != null && !"".equals(fullName)) {
				fullName = fullName.trim();
			}
			if (fullNameKana != null && !"".equals(fullNameKana)) {
				fullNameKana = fullNameKana.trim();
			}
			if (strArrBirthday != null) {
				for (int i = 0; i < strArrBirthday.length; i++) {
					try {
						lsIntBirthday.add(Integer.parseInt(strArrBirthday[i]));
					} catch (NumberFormatException nfe) {
						// TODO: handle exception
						System.out.println("AddUserInputController - setDefaultValue - NumberFormatException " + nfe.getMessage());
					}
				}
				if (lsIntBirthday.size() == 3) {
					String strBirthday = Common.convertToString(lsIntBirthday.get(0), lsIntBirthday.get(1), lsIntBirthday.get(2));
					birthday = Common.toDate(lsIntBirthday.get(0), lsIntBirthday.get(1), lsIntBirthday.get(2));
					if (!strBirthday.equals(Common.dateToStringFormat(birthday))) {
						request.setAttribute("lsBirthday", lsIntBirthday);
						birthday = null;
					}
				}
			}
			if (email != null && !"".equals(email)) {
				email = email.trim();
			}
			if (tel != null && !"".equals(tel)) {
				tel = tel.trim();
			}
			if (password != null && !"".equals(password)) {
				password = password.toString();
			}
			if (passwordConfirm != null && !"".equals(passwordConfirm)) {
				passwordConfirm = passwordConfirm.toString();
			}
			if (codeLevel != null && !"".equals(codeLevel)) {
				codeLevel = codeLevel.trim();
			}
			if (strArrStartDate != null) {
				for (int i = 0; i < strArrStartDate.length; i++) {
					try {
						lsIntStartDate.add(Integer.parseInt(strArrStartDate[i].trim()));
					} catch (NumberFormatException nfe) {
						// TODO: handle exception
						System.out.println("AddUserInputController - setDefaultValue - NumberFormatException " + nfe.getMessage());
					}
				}
				if (lsIntStartDate.size() == 3) {
					String strStartDate = Common.convertToString(lsIntStartDate.get(0), lsIntStartDate.get(1), lsIntStartDate.get(2));
					startDate = Common.toDate(lsIntStartDate.get(0), lsIntStartDate.get(1), lsIntStartDate.get(2));
					if (!strStartDate.equals(Common.dateToStringFormat(startDate))) {
						request.setAttribute("lsStartDate", lsIntStartDate);
						startDate = null;
					}
				}
			}
			if (strArrEndDate != null) {
				for (int i = 0; i < strArrEndDate.length; i++) {
					try {
						lsIntEndDate.add(Integer.parseInt(strArrEndDate[i].trim()));
					} catch (NumberFormatException nfe) {
						// TODO: handle exception
						System.out.println("AddUserInputController - setDefaultValue - NumberFormatException " + nfe.getMessage());
					}
				}
				if (lsIntEndDate.size() == 3) {
					String strEndDate = Common.convertToString(lsIntEndDate.get(0), lsIntEndDate.get(1), lsIntEndDate.get(2));
					endDate = Common.toDate(lsIntEndDate.get(0), lsIntEndDate.get(1), lsIntEndDate.get(2));
					if (!strEndDate.equals(Common.dateToStringFormat(endDate))) {
						request.setAttribute("lsEndDate", lsIntEndDate);
						endDate = null;
					}
				}
			}
			if (strTotal != null && !"".equals(strTotal)) {
				try {
					total = Integer.parseInt(strTotal);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("AddUserInputController - ParseException : " + e.getMessage());
				}
			}
			request.setAttribute("strTotal", strTotal);
			// Set data to Obj UserInfor
			userInfor = new UserInfor(userId ,loginName, password, passwordConfirm, fullName, fullNameKana, birthday, groupId, groupName, email, tel, codeLevel, nameLevel, startDate, endDate, total, strTotal);
		// Trường hợp không có trình độ tiếng nhật set các giá trị mặc định ngày tháng năm
//		} else {
//			userInfor = new UserInfor();
//		}
		} else {
			userInfor = new UserInfor();
			Calendar cal = Calendar.getInstance();
			Date dateNow = cal.getTime();
			userInfor.setBirthday(dateNow);
			userInfor.setStartDate(dateNow);
			cal.add(Calendar.YEAR, 1);
			Date endDate = cal.getTime();
			userInfor.setEndDate(endDate);
		}
		return userInfor;
	}

}
