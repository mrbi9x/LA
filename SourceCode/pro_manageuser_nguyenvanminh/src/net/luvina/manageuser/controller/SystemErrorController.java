/**
 * Copyright(C) @2016 Luvina Software Company
 * SystemErrorController.java, Jun 28, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.utils.MessageErrorProperties;
import net.luvina.manageuser.utils.MessageProperties;

/**
 * SystemErrorController - Controller xử lý màn hình hiển thị lỗi SystemError
 *
 * Servlet implementation class SystemErrorController
 */
public class SystemErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemErrorController() {
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
		String msg = request.getParameter("type");
		String message = "";
		if ("ER013".equals(msg)) {
			message = MessageErrorProperties.getMessage(msg);
		} else if ("ER014".equals(msg)) {
			message = MessageErrorProperties.getMessage(msg);
		} else if ("ER015".equals(msg)) {
			message = MessageErrorProperties.getMessage(msg);
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
