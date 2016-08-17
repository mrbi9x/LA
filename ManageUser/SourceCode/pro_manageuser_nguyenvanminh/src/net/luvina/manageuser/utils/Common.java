/**
 * Copyright(C) @2016 Luvina Software Company
 * Common.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Common - Chứa các hàm common của dự án
 *
 * @author Nguyễn Văn Minh
 *
 */
public class Common {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 *
	 */
	public Common() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * getListPaging - Lấy danh sách các trang để hiển thị
	 *
	 * @param totalRecords - Tổng số bản ghi
	 * @param limit - Số bản ghi mỗi trang
	 * @param currentPage - Trang hiện tại
	 * @return listPaging - Danh sách các trang để hiển thị
	 */
	public static List<Integer> getListPaging(int totalUser, int limit,
			int currentPage) {
		List<Integer> listPaging = new ArrayList<Integer>();
		int totalPage = countTotalPage(totalUser, limit);
		int pageRange = getPageRange();
		int pageStart = 0;
		int pageEnd = 0;
		// If totalPage <= pageRange => startPage = 1 , endPage = totalPage
		// Nếu tổng số trang nhỏ hơn số trang hiển thị => trang đầu tiên = 1, trang cuối = tổng số trang
		if (pageRange == 0 || totalPage <= pageRange) {
			pageStart = 1;
			pageEnd = totalPage;
		} else {
			int totalListPaging = getTotalListPaging(totalPage, pageRange);
			int positionOfListPagingFromCurrentPage  = getPositionOfListPagingFromCurrentPage(totalListPaging, pageRange, currentPage);
			pageStart = positionOfListPagingFromCurrentPage * pageRange + 1;
			pageEnd = pageStart + pageRange - 1;
			if (pageEnd > totalPage) {
				pageEnd = totalPage;
			}
		}
		// add List Page to listPaging => from pageStart to pageEnd
		for (int i = pageStart; i <= pageEnd; i++) {
			listPaging.add(i);
		}
		return listPaging;
	}

	/**
	 * getPositionOfListPagingFromCurrentPage - Lấy vị trí của danh sách các trang hiển thị trong tổng số danh sách các trang hiển thị
	 * theo trang hiện tại
	 *
	 * @param totalListPaging - Tổng số danh sách phân trang theo tổng số bản ghi và số bản ghi mỗi trang
	 * @param pageRange - Số trang hiển thị trên màn hình
	 * @param currentPage - Trang hiện tại
	 * @return positionOfListPagingFromCurrentPage - vị trí của danh sách các trang hiển thị trong tổng số danh sách các trang hiển thị
	 * theo trang hiện tại
	 */
	public static int getPositionOfListPagingFromCurrentPage(int totalListPaging, int pageRange, int currentPage) {
		int positionOfListPagingFromCurrentPage = 0;
		for (int i = 0; i < totalListPaging; i++) {
			if (((i * pageRange + 1) <= currentPage) && (((i + 1) * pageRange) >= currentPage)) {
				positionOfListPagingFromCurrentPage = i;
				break;
			}
		}
		return positionOfListPagingFromCurrentPage;
	}

	/**
	 * getOffset - Lấy vị trí bắt đầu lấy bản ghi
	 *
	 * @param currentPage - Trang hiện tại
	 * @param limit - Số bản ghi mỗi trang
	 * @return offset - Vị trí bắt đầu lấy bản ghi
	 */
	public static int getOffset(int currentPage, int limit) {
		int offset = 0;
		if (currentPage > 0) {
			offset = limit * (currentPage - 1);
		}
		return offset;
	}

	/**
	 * getLimit - Lấy số bản ghi mỗi trang từ file config
	 *
	 * @return limit - Số bản ghi mỗi trang
	 */
	public static int getLimit() {
		int limit = 0;
		try {
			limit = Integer.parseInt(ConfigProperties.getMessage("limit"));
		} catch (NumberFormatException nfe) {
			// TODO: handle exception
			System.out.println("Common numberFormartException : "
					+ nfe.getMessage());
		}

		return limit;
	}

	/**
	 * getTotalPage - Lấy tổng số trang theo tổng số bản ghi và số bản ghi mỗi trang
	 *
	 * @param totalUser - Tổng số bản ghi
	 * @param limit - Số bản ghi mỗi trang
	 * @return totalPage - Tổng số trang
	 */
	public static int countTotalPage(int totalUser, int limit) {
		int totalPage = 0;
		totalPage = (int) Math.ceil((double) totalUser / limit);
		return totalPage;
	}

	/**
	 * getPageRange - Lấy số trang hiển thị trên màn hình
	 *
	 * @return pageRange - Số trang hiển thị trên màn hình
	 */
	public static int getPageRange() {
		int pageRange = 0;
		try {
			pageRange = Integer.parseInt(ConfigProperties.getMessage("pageRange"));
		} catch (NumberFormatException nfe) {
			// TODO: handle exception
			System.out.println("Common exception function getPageRange : " + nfe.getMessage());
		}
		return pageRange;
	}

	/**
	 * getTotalListPaging - Lấy tổng số danh sách trang theo tổng số trang và số trang hiển thị trên màn hình
	 *
	 * @param totalPage - Tổng số trang
	 * @param pageRange - Số trang hiển thị trên màn hình
	 * @return totalListPaging tổng số danh sách trang
	 */
	public static int getTotalListPaging(int totalPage, int pageRange) {
		int totalListPaging = 0;
		totalListPaging = (int) Math.ceil((double) totalPage / pageRange);
		return totalListPaging;
	}

	/**
	 * escapeInjection - Xử lý các ký tự đặc biệt Wild Card, SQL Injection
	 *
	 * @param str - Chuỗi đầu vào
	 *
	 * @return strEscapeInjection - Chuỗi đã được thay thế các ký tự SQL Injection
	 */
	public static String escapeInjection(String str) {
		String strEscapeInjection = str.replace("\\", "\\\\");
		strEscapeInjection = strEscapeInjection.replace("%", "\\%");
		strEscapeInjection = strEscapeInjection.replace("_", "\\_");
		return strEscapeInjection;
	}

	/**
	 * checkLogin - kiểm tra session đăng nhập
	 *
	 * @param session
	 *            HttpSession - Session
	 * @return "" - Nếu đã login
	 *  ADM001 - Nếu chưa login
	 */
	public static String checkLogin(HttpSession session) {
		String template = "";
		if (session.getAttribute("loginId") == null) {
			template = Constant.ADM001;
		}
		return template;

	}

	/**
	 * getYearNow - Lấy về năm hiện tại
	 * @return nowYear - Năm hiện tại
	 */
	public static int getYearNow() {
		int nowYear = 0;
		Calendar calendar = Calendar.getInstance();
		nowYear = calendar.get(calendar.YEAR);
		return nowYear;
	}

	/**
	 * getListYear - Hàm xử lý để lấy danh sách các năm từ fromYear đến toYear
	 *
	 * @param fromYear - từ năm
	 * @param toYear -  đến năm
	 * @return listYear - danh sách các năm từ fromYear đến toYear
	 */
	public static List<Integer> getListYear(int fromYear, int toYear) {
		List<Integer> listYear = new ArrayList<Integer>();
		if (fromYear == toYear) {
			listYear.add(fromYear);
		} else if (fromYear > toYear) {
			fromYear = toYear;
			toYear = fromYear;
		} else {
			for (int i = fromYear; i <= toYear; i++) {
				listYear.add(i);
			}
		}
		return listYear;
	}

	/**
	 * getListMonth - Lấy danh sách các tháng  từ 1->12
	 *
	 * @return listMonth - danh sách các tháng: từ 1->12
	 */
	public static List<Integer> getListMonth () {
		List<Integer> listMonth = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			listMonth.add(i);
		}
		return listMonth;
	}

	/**
	 * getListDay - Lấy danh sách các ngày từ  1->31
	 *
	 * @return listDay - danh sách các ngày từ  1->31
	 */
	public static List<Integer> getListDay() {
		List<Integer> listDay = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) {
			listDay.add(i);
		}
		return listDay;
	}

	/**
	 * convertToString - Convert các số năm. Tháng ngày thành 1 chuỗi ngày tháng có format yyyy/mm/dd
	 *
	 * @param year - năm
	 * @param month - tháng
	 * @param day - ngày
	 * @return tempStr - chuỗi năm/tháng/ngày
	 */
	public static String convertToString(int year, int month, int day) {
		String tempStr = year + "/" + month + "/" + day;
		return tempStr;
	}

	/**
	 * toDate - Convert các số năm. Tháng ngày thành 1 ngày tháng có format yyyy/MM/dd
	 *
	 * @param year - năm
	 * @param month - tháng
	 * @param day - ngày
	 * @return dateObj - ngày tháng từ year, month, day
	 */
	public static Date toDate(int year, int month, int day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = convertToString(year, month, day);
		Date dateObj = null;
		try {
			dateObj = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("Log, Common ParseException function toDate() : " + e.getMessage());
		}
		return dateObj;
	}

	/**
	 * dateToStringFormat chuyển 1 ngày kiểu Date sang chuỗi định dạng yyyy/M/d
	 * @param date - Ngày tháng kiểu Date
	 * @return chuỗi định dạng yyyy/M/d
	 */
	public static String dateToStringFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
		String strDate = "";
		strDate = sdf.format(date);
		return strDate;
	}

	/**
	 * checkExitedDate - Kiểm tra ngày tồn tại
	 * @param date - Đối tượng date cần kiểm tra
	 * @return True - Ngày tồn tại
	 * False - Ngày không tồn tại
	 */
	public static boolean checkExitedDate (Date date) {
		if (date == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * compareDate - So sánh hai ngày
	 *
	 * @param firstDate - Ngày thứ nhất cần so sánh
	 * @param secondDate - Ngày thứ hai cần so sánh
	 * @return True nếu firstDate > secondDate
	 * False nếu firstDate <= secondDate
	 */
	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate != null && secondDate != null) {
			long countCompareDate = firstDate.getTime() - secondDate.getTime();
			if (countCompareDate > 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * convertDateToSqlDate - Hàm chuyển Java Date sang sql Date
	 * @param date - ngày cần chuyển
	 * @return Long - sql Date
	 */
	public static java.sql.Date convertDateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * toArrayInteger - Convert date input to Year,Month,Day store in ArrayList
	 *	date[0] = Year, date[1]=Month , date[2]=Day
	 *
	 * @param date - Date ngày tháng cần chuyển về mảng
	 * @return alDate - mảng chứa năm, tháng ngày
	 */
	public static List<Integer> toArrayInteger(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		List<Integer> alDate = new ArrayList<Integer>();
		alDate.add(calendar.get(calendar.YEAR));
		alDate.add(calendar.get(calendar.MONTH));
		alDate.add(calendar.get(calendar.DAY_OF_MONTH));
		return alDate;
	}

	/**
	 * getSessionKey - Lấy giá trị duy nhất của tên session
	 * @return String SessionKey duy nhất
	 */
	public static String getSessionKey() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * exportCsv - Hàm xử lý export dữ liệu ra file CSV
	 * @param fileName -  Tên file export
	 * @param header - Header file CSV
	 * @param content - Nội dung file CSV
	 * @param response - HttpServletResponse
	 */
	public static void exportCsv(String fileName, StringBuilder header, StringBuilder content, HttpServletResponse response) {
		try {
			response.setContentType("text/csv; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			PrintWriter printWriter = response.getWriter();
			printWriter.write(header.toString());
			printWriter.write(content.toString());
			printWriter.flush();
			printWriter.close();
		} catch (IOException ioe) {
			// TODO: handle exception
			System.out.print("Export CSV Exception : " + ioe.getMessage());
		}
	}
}
