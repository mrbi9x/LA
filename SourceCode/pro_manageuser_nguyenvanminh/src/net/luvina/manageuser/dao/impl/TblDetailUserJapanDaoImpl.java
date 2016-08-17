/**
 * Copyright(C) @2016 Luvina Software Company
 * TblDetailUserJapanDaoImpl.java, Jun 28, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.luvina.manageuser.dao.TblDetailUserJapanDao;
import net.luvina.manageuser.entities.TblDetailUserJapan;
import net.luvina.manageuser.utils.Common;

/**
 * TblDetailUserJapanDaoImpl - Thao tác database với bảng TblDetailUserJapanHbn
 * @author Nguyễn Văn Minh
 *
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/**
	 *
	 */
	public TblDetailUserJapanDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblDetailUserJapanDao#insertDetailUserJapan(net.luvina.manageuser.entities.TblDetailUserJapan)
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		boolean isInsertDetailUserJapan = false;
		java.sql.Date startDate = Common.convertDateToSqlDate(tblDetailUserJapan.getStartDate());
		java.sql.Date endDate = Common.convertDateToSqlDate(tblDetailUserJapan.getEndDate());
		StringBuilder insertQuery = new StringBuilder();
		insertQuery.append("INSERT INTO tbl_detail_user_japan");
		insertQuery.append(" (user_id, code_level, start_date, end_date, total)");
		insertQuery.append(" VALUES");
		insertQuery.append(" (?, ?, ?, ?, ?);");
		PreparedStatement preparedStatement = conn.prepareStatement(insertQuery.toString());
		int i = 1;
		preparedStatement.setInt(i++, tblDetailUserJapan.getUserId());
		preparedStatement.setString(i++, tblDetailUserJapan.getCodeLevel());
		preparedStatement.setDate(i++, startDate);
		preparedStatement.setDate(i++, endDate);
		preparedStatement.setInt(i++, tblDetailUserJapan.getTotal());
		// run Insert
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			isInsertDetailUserJapan = true;
		} else {
			isInsertDetailUserJapan = false;
		}
		return isInsertDetailUserJapan;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblDetailUserJapanDao#deleteDetailUserJapan(org.hibernate.Session, net.luvina.manageuser.entities.TblDetailUserJapan)
	 */
	@Override
	public boolean deleteDetailUserJapan(int userId, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		boolean isDeleteSuccess = false;
		StringBuilder deleteQuery = new StringBuilder();
		deleteQuery.append("DELETE FROM tbl_detail_user_japan");
		deleteQuery.append(" WHERE tbl_detail_user_japan.user_id = ?;");
		PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery.toString());
		int i =1;
		preparedStatement.setInt(i++, userId);
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			isDeleteSuccess = true;
		} else {
			isDeleteSuccess = false;
		}
		return isDeleteSuccess;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblDetailUserJapanDao#updateDetailUserJapan(net.luvina.manageuser.entities.TblDetailUserJapan)
	 */
	@Override
	public boolean updateDetailUserJapan(TblDetailUserJapan tblDetailUserJapan, Connection conn)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		boolean isUpdateSuccess = false;
		java.sql.Date startDate = Common.convertDateToSqlDate(tblDetailUserJapan.getStartDate());
		java.sql.Date endDate = Common.convertDateToSqlDate(tblDetailUserJapan.getEndDate());
		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("UPDATE tbl_detail_user_japan");
		updateQuery.append(" SET");
		updateQuery.append(" tbl_detail_user_japan.code_level = ?,");
		updateQuery.append(" tbl_detail_user_japan.start_date = ?,");
		updateQuery.append(" tbl_detail_user_japan.end_date = ?,");
		updateQuery.append(" tbl_detail_user_japan.total = ?");
		updateQuery.append(" WHERE");
		updateQuery.append(" tbl_detail_user_japan.user_id = ?;");
		PreparedStatement preparedStatement = conn.prepareStatement(updateQuery.toString());
		int i = 1;
		preparedStatement.setString(i++, tblDetailUserJapan.getCodeLevel());
		preparedStatement.setDate(i++, startDate);
		preparedStatement.setDate(i++, endDate);
		preparedStatement.setInt(i++, tblDetailUserJapan.getTotal());
		preparedStatement.setInt(i++, tblDetailUserJapan.getUserId());
		// run Insert
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			isUpdateSuccess = true;
		} else {
			isUpdateSuccess = false;
		}
		return isUpdateSuccess;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblDetailUserJapanDao#getDetailUserJapanByUserId(int)
	 */
	@Override
	public TblDetailUserJapan getDetailUserJapanByUserId(int userId, Connection conn) throws SQLException{
		// TODO Auto-generated method stub
		TblDetailUserJapan tblDetailUserJapan = null;
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT * FROM tbl_detail_user_japan");
			sqlQuery.append(" WHERE");
			sqlQuery.append(" tbl_detail_user_japan.user_id = ?;");
			try {
				PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery.toString());
				int i = 1;
				preparedStatement.setInt(i++, userId);
				ResultSet rs = preparedStatement.executeQuery();
				if (rs != null && rs.next()) {
					tblDetailUserJapan = new TblDetailUserJapan(
							rs.getInt("detail_user_japan_id"),
							rs.getInt("user_id"),
							rs.getString("code_level"),
							rs.getDate("start_date"),
							rs.getDate("end_date"),
							rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("getDetailUserJapanByUserId - SQLException : " + e.getMessage());
			}
		return tblDetailUserJapan;
	}

}
