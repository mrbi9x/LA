/**
 * Copyright(C) 2016 Luvina Software Company
 * TblReportDao.java, Jul 15, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luvina.manageuser.entities.TblReport;
import net.luvina.manageuser.logics.impl.TblReportLogicImpl;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Constant;

/**
 * ViewReportController - Controller xử lý thống kê
 * Servlet implementation class ViewReportController
 */
public class ViewReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReportController() {
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
		TblReportLogicImpl reportLogicImpl = new TblReportLogicImpl();
		TblReport tblReport = new TblReport();
		tblReport = reportLogicImpl.getTblReport();
		if (tblReport != null) {
			request.setAttribute("tblReport", tblReport);
		} else {
			request.setAttribute("message", "Chưa có thống kê.");
		}
		request.getRequestDispatcher(Constant.VIEW_REPORT).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
