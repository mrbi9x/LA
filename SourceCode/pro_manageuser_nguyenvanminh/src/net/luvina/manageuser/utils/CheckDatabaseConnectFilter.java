package net.luvina.manageuser.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.luvina.manageuser.logics.impl.DatabaseConnectLogicImpl;

/**
 * CheckDatabaseConnectFilter - Filter xử lý kiểm tra kết nối database
 *
 * Servlet Filter implementation class CheckDatabaseConnectFilter
 */
public class CheckDatabaseConnectFilter implements Filter {

    /**
     * Default constructor.
     */
    public CheckDatabaseConnectFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		DatabaseConnectLogicImpl databaseConnectLogicImpl = new DatabaseConnectLogicImpl();
		// Nếu kết nối db thành công -> chuyển sang kiểm tra filter tiếp theo
		if (databaseConnectLogicImpl.CheckDatabaseConnect()) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
			// Nếu không kết nối thành công -> chuyển sang màn hình SYSTEM_ERROR.jsp
		} else {
			// Start fix bug id 31 - MinhNV 2016/06/21
			request.setAttribute("message", MessageErrorProperties.getMessage("ER015"));
			// End fix bug id 31 - MinhNV 2016/06/21
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
