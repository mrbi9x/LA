/**
 * Copyright(C) @2016 Luvina Software Company
 * LoginController.java, Jun 14, 2016, Nguyễn Văn Minh
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

import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.validates.ValidateUser;

/**
 * LoginController - Class xử lý login - Đăng nhập
 * @author Nguyễn Văn Minh
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM001);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		List<String> lsErrorMessage = new ArrayList<String>();
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String template = "";
		// Get data from request
		if (loginId != null && password != null) {
			loginId = loginId.trim().toString();
			password = password.trim().toString();
		}
		// Set data to request
		request.setAttribute("loginId", loginId);
        // Validates loginID && password
        lsErrorMessage = ValidateUser.validateLogin(loginId, password);
        // If has errors go to ADM001
        if (lsErrorMessage.size() > 0) {
            template = Constant.ADM001;
            // Set lsErrMessage to request
            request.setAttribute("lsErrorMessage", lsErrorMessage);
            // forward to Login page
            RequestDispatcher req = request.getRequestDispatcher(template);
            req.forward(request, response);
        } else {
        	// set data into session
            session.setAttribute("loginId", loginId);
            session.setMaxInactiveInterval(5*60);
            template = Constant.ADM002;
        	response.sendRedirect(contextPath + "/ListUser.do");
        }
	}

}
