/**
 * Copyright(C) @2016 Luvina Software Company
 * StringUtil.java, Jun 27, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.luvina.manageuser.entities.SessionData;
import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Constant;

/**
 * AddUserConfirmController - Controller xử lý các logic của màn hình ADM004
 * @author Nguyễn Văn Minh
 * Servlet implementation class AddUserConfirmController
 */
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserConfirmController() {
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
		UserInfor userInfor = null;
		String sessionKey = request.getParameter("sessionKey");
		HttpSession session = request.getSession();
		SessionData sessionData = (SessionData) session.getAttribute(sessionKey);
		if (sessionData != null) {
			userInfor = sessionData.getUserInfor();
		}
		if (userInfor != null) {
			request.setAttribute("userInfor", userInfor);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.ADM004);
			dispatcher.forward(request, response);
		// Start fix bug ID 114 - MinhNV - 2016/07/01
		} else {
			response.sendRedirect(Constant.ERROR + "?type=ER013" + "&sessionKey=" + sessionKey);
		}
		// End fix bug ID 114 - MinhNV - 2016/07/01
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserInfor userInfor = new UserInfor();
		String sessionKey = request.getParameter("sessionKey");
		HttpSession session = request.getSession();
		SessionData sessionData = (SessionData) session.getAttribute(sessionKey);
		if (sessionData != null) {
			userInfor = sessionData.getUserInfor();
		}
		// Remove UserInfor session
		sessionData.setUserInfor(new UserInfor());
		if (userInfor != null) {
			Integer userId = userInfor.getUserId();
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			if (userId != null && userId > 0) {
				// Do update
				if (tblUserLogicImpl.updateUser(userInfor)) {
					response.sendRedirect(Constant.SUCCESS + "?type=" + Constant.UPDATE_SUCCESS + "&sessionKey=" + sessionKey);
				} else {
					response.sendRedirect(Constant.ERROR + "?type=ER013" + "&sessionKey=" + sessionKey);
				}
			} else {
				boolean isInsertSuccess = tblUserLogicImpl.createUser(userInfor);
				if (isInsertSuccess) {
					response.sendRedirect(Constant.SUCCESS + "?type=" + Constant.INSERT_SUCCESS + "&sessionKey=" + sessionKey);
				} else {
					response.sendRedirect(Constant.ERROR + "?type=" + Constant.MSG_SYSTEM_ERROR + "&sessionKey=" + sessionKey);
				}
			}
		} else {
			// If session = null, userInfor = null => Redirect ADM006 with MSG_SYSTEM_ERROR
			response.sendRedirect(Constant.SUCCESS + "?type=" + Constant.MSG_SYSTEM_ERROR + "&sessionKey=" + sessionKey);
		}
	}

}
