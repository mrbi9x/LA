package net.luvina.manageuser.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.luvina.manageuser.logics.impl.LoginLogicImpl;

/**
 * CheckLoginFilter - Filter kiểm tra session login
 * Nếu đã login thì chuyển sang filter tiếp theo
 * Nếu chưa login chuyển về màn hình ADM001 - Login
 *
 * Servlet Filter implementation class CheckLoginFilter
 */
public class CheckLoginFilter implements Filter {
	private static final String DEFAULT_IGNORE_URL = "/Login.do";
	private String ignoreUrl = DEFAULT_IGNORE_URL;
    /**
     * Default constructor.
     */
    public CheckLoginFilter() {
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
		// Ép kiểu ServletRequest sang HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		// Ép kiểu ServletResponse sang HttpServletResponse
		HttpServletResponse res =  (HttpServletResponse) response;
		// req.getSession(false) - Nếu tồn tại Session thì không khởi tạo lại session
		HttpSession session = req.getSession(false);
		String fullPathIgnoreURI = req.getContextPath() + ignoreUrl;

		boolean checkIgnoreUri = req.getRequestURI().equals(fullPathIgnoreURI);

		boolean checkLoginSession =  false;

		if (session != null && "".equals(Common.checkLogin(session))) {
			// pass the request along the filter chain
			checkLoginSession = true;
		}
		if (checkLoginSession || checkIgnoreUri) {
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(fullPathIgnoreURI);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String initParamIgnoreUrl = fConfig.getInitParameter("ignoreUrl");
		if (initParamIgnoreUrl != null) {
			ignoreUrl = initParamIgnoreUrl;
		}
	}

}
