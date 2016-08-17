/**
 * Copyright(C) @2016 Luvina Software Company
 * SuccessController.java, Jun 28, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.utils.MessageErrorProperties;
import net.luvina.manageuser.utils.MessageProperties;

/**
 * SuccessController - Controller xử lý màn hình ADM006
 *
 * Servlet implementation class SuccessController
 */
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessController() {
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
		request.setCharacterEncoding("UTF-8");
		String msgType = request.getParameter("type");
		String message = "";
		if ("MSG001".equals(msgType)) {
			message = MessageProperties.getMessage(msgType);
		} else if ("MSG002".equals(msgType)) {
			message = MessageProperties.getMessage(msgType);
		} else if ("MSG003".equals(msgType)) {
			message = MessageProperties.getMessage(msgType);
		}else if ("ER015".equals(msgType)) {
			message = MessageErrorProperties.getMessage(msgType);
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.ADM006);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
