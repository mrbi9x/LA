/**
 * Copyright(C) @2016 Luvina Software Company
 * ViewDetailController.java, Jun 28, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Constant;

/**
 * ViewDetailController - Controller xử lý màn hình ADM005
 *
 * Servlet implementation class ViewDetailController
 */
public class ViewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDetailController() {
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
		String strUserId = request.getParameter("userId");
		Integer userId = null;
		try {
			userId = Integer.parseInt(strUserId);
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("SystemError.do" + "?type=ER013");
		}
		if (userId != null) {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
				UserInfor userInfor = new UserInfor();
				userInfor = tblUserLogicImpl.getUserInforByUserId(userId);
				request.setAttribute("userInfor", userInfor);
			if (userInfor != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.ADM005);
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("SystemError.do?type=ER013");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
