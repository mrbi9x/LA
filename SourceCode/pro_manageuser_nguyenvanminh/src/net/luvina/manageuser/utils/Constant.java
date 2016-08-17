/**
 * Copyright(C) @2016 Luvina Software Company
 * Constant.java, Jun 13, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.utils;

/**
 * Constant - Class chứa các Constant của project
 * @author Nguyễn Văn Minh
 *
 */
public class Constant {
	// Constant JSP page
	public static String ADM001 = "jsp/ADM001.jsp";
    public static String ADM002 = "jsp/ADM002.jsp";
    public static String ADM003 = "jsp/ADM003.jsp";
    public static String ADM004 = "jsp/ADM004.jsp";
    public static String ADM005 = "jsp/ADM005.jsp";
    public static String ADM006 = "jsp/ADM006.jsp";
    public static String VIEW_REPORT = "jsp/ViewReport.jsp";
    public static String SYSTEM_ERROR = "jsp/System_Error.jsp";
    // Constant Default Sort
    public static String DFAULT_SORT_BY_FULL_NAME = "ASC";
    public static String DFAULT_SORT_BY_CODE_LEVEL = "DESC";
    public static String DFAULT_SORT_BY_END_DATE = "DESC";
    // Constant Pattern loginName
    public static final String LOGIN_NAME_PATTERN = "^[a-zA-Z_][a-zA-Z0-9_]{3,14}";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String TEL_PATTERN = "\\d{1,4}-\\d{1,4}-\\d{1,4}";
    public static final String HALFSIZE_PATTERN = "[0-9]+";
    // Constant Servlet context Path
    public static String ADD_USER_CONFIRM = "AddUserConfirm.do";
    public static String SUCCESS = "Success.do";
    public static String ERROR = "SystemError.do";
    // Constant message
    public static String INSERT_SUCCESS = "MSG001";
    public static String UPDATE_SUCCESS = "MSG002";
    public static String DELETE_SUCCESS = "MSG003";
    public static String MSG_SYSTEM_ERROR = "ER015";
}
