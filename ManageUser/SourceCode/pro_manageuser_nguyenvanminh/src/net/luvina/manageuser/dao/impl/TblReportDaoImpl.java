/**
 * Copyright(C) 2016 Luvina Software Company
 * TblReportDaoImpl.java, Jul 15, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao.impl;

import java.sql.SQLException;

import net.luvina.manageuser.dao.TblReportDao;
import net.luvina.manageuser.entities.TblReport;

/**
 * TblReportDaoImpl - Xử lý thao tác db với bảng TblReport
 * @author Nguyễn Văn Minh
 *
 */
public class TblReportDaoImpl extends BaseDaoImpl implements TblReportDao {

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblReportDao#getTblReport()
	 */
	@Override
	public TblReport getTblReport() {
		// TODO Auto-generated method stub
		TblReport tblReport = null;
		if (connectToDB()) {
			try {
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT * FROM tbl_report ");
				sqlQuery.append(" WHERE tbl_report.report_id =");
				sqlQuery.append(" ( SELECT MAX(tbl_report.report_id) FROM tbl_report );");
				preparedStatement = connection.prepareStatement(sqlQuery.toString());
				rs = preparedStatement.executeQuery();
				if (rs != null && rs.next()) {
					tblReport = new TblReport(
											rs.getInt("total_user"),
											rs.getInt("total_user_n"),
											rs.getInt("total_user_n0"),
											rs.getInt("total_user_n1"),
											rs.getInt("total_user_n2"),
											rs.getInt("total_user_n3"),
											rs.getInt("total_user_n4"),
											rs.getInt("total_user_n5"),
											rs.getDate("date_report"));
					rs.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("SQLException : " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return tblReport;
	}

}
