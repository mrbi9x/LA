/**
 * Copyright(C) 2016 Luvina Software Company
 * TblReportDao.java, Jul 15, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao;

import net.luvina.manageuser.entities.TblReport;

/**
 * TblReportDao.java - Xử lý thao tác db với bảng TblReport
 * @author Nguyễn Văn Minh
 *
 */
public interface TblReportDao {

	/**
	 * Hàm lấy về đối tượng TblReport
	 * @return đối tượng TblReport
	 */
	public TblReport getTblReport();
}
