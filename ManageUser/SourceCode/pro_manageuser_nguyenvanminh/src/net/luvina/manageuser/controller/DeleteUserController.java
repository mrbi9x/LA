/**
 * Copyright(C) @2016 Luvina Software Company
 * DeleteUserController.java, Jul 05, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Constant;

/**
 * DeleteUserController - Controller xử lý xóa User
 * @author Nguyễn Văn Minh
 * Servlet implementation class DeleteUserController
 */
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strUserId = request.getParameter("userId");
		Integer userId = null;
		try {
			userId = Integer.parseInt(strUserId);
		} catch (NumberFormatException nfe) {
			// TODO: handle exception
			response.sendRedirect(Constant.ERROR + "?type=ER014");
		}
		TblUserLogicImpl userLogicImpl = new TblUserLogicImpl();
		if (userLogicImpl.deleteUser(userId)) {
			response.sendRedirect(Constant.SUCCESS + "?type=" + Constant.DELETE_SUCCESS);
		} else {
			response.sendRedirect(Constant.ERROR + "?type=ER014");
		}
	}

}
