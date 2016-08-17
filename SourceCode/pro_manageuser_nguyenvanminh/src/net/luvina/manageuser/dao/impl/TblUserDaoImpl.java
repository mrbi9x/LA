/**
 * Copyright(C) @2016 Luvina Software Company
 * TblUserDaoImpl.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import net.luvina.manageuser.dao.TblUserDao;
import net.luvina.manageuser.entities.TblUser;
import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.utils.Common;

/**
 * TblUserDaoImpl - Class xử lý các thao tác dữ liệu với bảng TblUser
 * @author Nguyễn Văn Minh
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	/**
	 *
	 */
	public TblUserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#getListUser(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUser(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate) {
		// TODO Auto-generated method stub
		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		if (connectToDB()) {
			try {
				StringBuilder sqlQuery = new StringBuilder();
				// SELECT table col
				sqlQuery.append("SELECT ");
				sqlQuery.append(" tbl_user.user_id,");
				sqlQuery.append(" tbl_user.full_name ,");
				sqlQuery.append(" tbl_user.birthday,");
				sqlQuery.append(" mst_group.group_name,");
				sqlQuery.append(" tbl_user.email,");
				sqlQuery.append(" tbl_user.tel,");
				sqlQuery.append(" mst_japan.name_level,");
				sqlQuery.append(" tbl_detail_user_japan.end_date,");
				sqlQuery.append(" tbl_detail_user_japan.total");
				// Join table
				sqlQuery.append(" FROM tbl_user");
				sqlQuery.append(" INNER JOIN  mst_group");
				sqlQuery.append(" ON tbl_user.group_id = mst_group.group_id");
				sqlQuery.append(" LEFT JOIN (tbl_detail_user_japan");
				sqlQuery.append(" INNER JOIN mst_japan");
				sqlQuery.append(" ON tbl_detail_user_japan.code_level = mst_japan.code_level)");
				sqlQuery.append(" ON tbl_user.user_id = tbl_detail_user_japan.user_id");
				// WHERE condition
				sqlQuery.append(" WHERE (tbl_user.full_name LIKE ? OR ? IS NULL)");
				sqlQuery.append(" AND (tbl_user.group_id = ? OR ? = 0)");
				//Order by
				sqlQuery.append(" ORDER BY ");
				if ("fullNameSort".equals(sortType)) {
					sqlQuery.append(" tbl_user.full_name " + sortByFullName + ",");
					sqlQuery.append(" (mst_japan.code_level IS NULL), mst_japan.code_level " + sortByCodeLevel + ",");
					sqlQuery.append(" (tbl_detail_user_japan.end_date IS NULL), tbl_detail_user_japan.end_date " + sortByEndDate);
				} else if ("codeLevelSort".equals(sortType)) {
					sqlQuery.append(" (mst_japan.code_level IS NULL), mst_japan.code_level " + sortByCodeLevel + ",");
					sqlQuery.append(" tbl_user.full_name " + sortByFullName + ",");
					sqlQuery.append(" (tbl_detail_user_japan.end_date IS NULL), tbl_detail_user_japan.end_date " + sortByEndDate);
				} else if ("endDateSort".equals(sortType)) {
					sqlQuery.append(" (tbl_detail_user_japan.end_date IS NULL), tbl_detail_user_japan.end_date " + sortByEndDate + ",");
					sqlQuery.append(" tbl_user.full_name " + sortByFullName + ",");
					sqlQuery.append(" (mst_japan.code_level IS NULL), mst_japan.code_level " + sortByCodeLevel);
				} else {
					sqlQuery.append(" tbl_user.full_name " + sortByFullName + ",");
					sqlQuery.append(" (mst_japan.code_level IS NULL), mst_japan.code_level " + sortByCodeLevel + ",");
					sqlQuery.append(" (tbl_detail_user_japan.end_date IS NULL), tbl_detail_user_japan.end_date " + sortByEndDate);
				}
				sqlQuery.append(" LIMIT ?");
				sqlQuery.append(" OFFSET ?");

				preparedStatement = connection.prepareStatement(sqlQuery.toString());
				// Prepared Statement for Search
				int i = 1;
				preparedStatement.setString(i++, "%" + fullName + "%");
				preparedStatement.setString(i++, fullName);
				preparedStatement.setInt(i++, groupId);
				preparedStatement.setInt(i++, groupId);
				preparedStatement.setInt(i++, limit);
				preparedStatement.setInt(i++, offset);
				// Execute Query
				rs = preparedStatement.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						UserInfor userInfor = new UserInfor(rs.getInt("user_id"),
															rs.getString("full_name"),
															rs.getDate("birthday"),
															rs.getString("group_name"),
															rs.getString("email"),
															rs.getString("tel"),
															rs.getString("name_level"),
															rs.getDate("end_date"),
															rs.getInt("total")
														);
						listUserInfor.add(userInfor);
					}
					rs.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Exception : " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return listUserInfor;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int countTotalUsers(int groupId, String fullName) {
		// TODO Auto-generated method stub
		int totalUsers = 0;
		if (connectToDB()) {
			try {
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT COUNT(*) AS TOTAL ");
				sqlQuery.append(" FROM TBL_USER");
				sqlQuery.append(" INNER JOIN  mst_group");
				sqlQuery.append(" ON tbl_user.group_id = mst_group.group_id");
				// WHERE Clause
				sqlQuery.append(" WHERE (tbl_user.full_name LIKE ? OR ? IS NULL)");
				sqlQuery.append(" AND (tbl_user.group_id = ? OR ? = 0)");
				preparedStatement = connection.prepareStatement(sqlQuery.toString());
				int i = 1;
				preparedStatement.setString(i++, "%" + fullName + "%");
				preparedStatement.setString(i++, fullName);
				preparedStatement.setInt(i++, groupId);
				preparedStatement.setInt(i++, groupId);

				rs = preparedStatement.executeQuery();
				if (rs != null && rs.next()) {
						totalUsers = rs.getInt("TOTAL");
					rs.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Exception : " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return totalUsers;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#getListUserByCodeLevel(boolean, java.lang.String[])
	 */
	@Override
	public List<UserInfor> getListUserByCodeLevel(boolean isGetAllUserInfor,
			String[] japanCodeLevel) {
		// TODO Auto-generated method stub
		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		connectToDB();
		try {
			StringBuilder sqlQuery = new StringBuilder();
			// SELECT table col
			sqlQuery.append("SELECT ");
			sqlQuery.append(" tbl_user.user_id,");
			sqlQuery.append(" tbl_user.full_name ,");
			sqlQuery.append(" tbl_user.birthday,");
			sqlQuery.append(" mst_group.group_name,");
			sqlQuery.append(" tbl_user.email,");
			sqlQuery.append(" tbl_user.tel,");
			sqlQuery.append(" mst_japan.name_level,");
			sqlQuery.append(" tbl_detail_user_japan.end_date,");
			sqlQuery.append(" tbl_detail_user_japan.total");
			// Join table
			sqlQuery.append(" FROM tbl_user");
			sqlQuery.append(" INNER JOIN  mst_group");
			sqlQuery.append(" ON tbl_user.group_id = mst_group.group_id");
			if (isGetAllUserInfor) {
				sqlQuery.append(" LEFT JOIN (tbl_detail_user_japan");
			} else {
				sqlQuery.append(" INNER JOIN (tbl_detail_user_japan");
			}
			sqlQuery.append(" INNER JOIN mst_japan");
			sqlQuery.append(" ON tbl_detail_user_japan.code_level = mst_japan.code_level)");
			sqlQuery.append(" ON tbl_user.user_id = tbl_detail_user_japan.user_id");
			// WHERE condition
			if (!isGetAllUserInfor && japanCodeLevel.length > 0) {
				sqlQuery.append(" WHERE");
				sqlQuery.append(" tbl_detail_user_japan.code_level IN (");
				for (int i = 1; i <= japanCodeLevel.length; i++) {
					if (i == japanCodeLevel.length) {
						sqlQuery.append("?)");
					} else {
						sqlQuery.append("?,");
					}
				}
			}
			sqlQuery.append(" ORDER BY (tbl_detail_user_japan.code_level IS NULL), tbl_detail_user_japan.code_level");
			sqlQuery.append(" LIMIT 1000");
			preparedStatement = connection.prepareStatement(sqlQuery.toString());
			// Prepared Statement for Search
			int indexParam = 1;
			if (!isGetAllUserInfor && japanCodeLevel.length > 0) {
				for (int i = 0; i < japanCodeLevel.length; i++) {
					preparedStatement.setString(indexParam++, japanCodeLevel[i]);
				}
			}
			// Execute Query
			rs = preparedStatement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					UserInfor userInfor = new UserInfor(rs.getInt("user_id"),
														rs.getString("full_name"),
														rs.getDate("birthday"),
														rs.getString("group_name"),
														rs.getString("email"),
														rs.getString("tel"),
														rs.getString("name_level"),
														rs.getDate("end_date"),
														rs.getInt("total")
													);
					listUserInfor.add(userInfor);
				}
				rs.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Exception : " + e.getMessage());
		} finally {
			closeConnect();
		}
		return listUserInfor;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#checkExistedLoginName(java.lang.Integer, java.lang.String)
	 */
	@Override
	public TblUser checkExistedLoginName(final Integer userId, final String loginName) {
		// TODO Auto-generated method stub
		TblUser tblUser = null;
		try {
			connectToDB();
			StringBuilder selectSqlQuery = new StringBuilder();
			selectSqlQuery.append("SELECT * FROM tbl_user");
			if (userId != null && loginName != null) {
				selectSqlQuery.append(" WHERE tbl_user.user_id != ?");
				selectSqlQuery.append(" AND tbl_user.login_name = ?");
			}else if (loginName != null) {
				selectSqlQuery.append(" WHERE tbl_user.login_name = ?");
			}
			preparedStatement = connection.prepareStatement(selectSqlQuery.toString());
			int indexParam = 1;
			if (userId != null && loginName != null) {
				preparedStatement.setInt(indexParam++, userId);
				preparedStatement.setString(indexParam++, loginName);
			}else if (loginName != null) {
				preparedStatement.setString(indexParam++, loginName);
			}
			rs = preparedStatement.executeQuery();
			if (rs != null && rs.next()) {
					tblUser = new TblUser(rs.getInt("user_id"),
							rs.getInt("group_id"),
							rs.getString("login_name"),
							rs.getString("pass"),
							rs.getString("full_name"),
							rs.getString("full_name_kana"),
							rs.getString("email"),
							rs.getString("tel"),
							rs.getDate("birthday"));
				rs.close();
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			System.out.println("TblUserDaoImpl - SQLException : " + se.getMessage());
		} finally {
			closeConnect();
		}

		return tblUser;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#getUserByEmail(java.lang.Integer, java.lang.String)
	 */
	@Override
	public TblUser getUserByEmail(final Integer userId, final String email) {
		// TODO Auto-generated method stub
		TblUser tblUser = null;
		connectToDB();
		StringBuilder selectSqlQuery = new StringBuilder();
		selectSqlQuery.append("SELECT * FROM tbl_user");
		if (userId != null && email != null) {
			selectSqlQuery.append(" WHERE tbl_user.user_id != ?");
			selectSqlQuery.append(" AND tbl_user.email = ?");
		}else if (email != null) {
			selectSqlQuery.append(" WHERE tbl_user.email = ?");
		}
		try {
			preparedStatement = connection.prepareStatement(selectSqlQuery.toString());
			int indexParam = 1;
			if (userId != null && email != null) {
				preparedStatement.setInt(indexParam++, userId);
				preparedStatement.setString(indexParam++, email);
			}else if (email != null) {
				preparedStatement.setString(indexParam++, email);
			}
			rs = preparedStatement.executeQuery();
			if (rs != null && rs.next()) {
					tblUser = new TblUser(rs.getInt("user_id"),
							rs.getInt("group_id"),
							rs.getString("login_name"),
							rs.getString("pass"),
							rs.getString("full_name"),
							rs.getString("full_name_kana"),
							rs.getString("email"),
							rs.getString("tel"),
							rs.getDate("birthday"));
				rs.close();
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			System.out.println("TblUserDaoImpl - getUserByEmail - SQLException : " + se.getMessage());
		} finally {
			closeConnect();
		}
		return tblUser;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#insertUser(net.luvina.manageuser.entities.TblUser)
	 */
	@Override
	public boolean insertUser(TblUser tblUser, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		boolean isInsertUserSuccess = false;
		java.sql.Date sqlDateBirthday = Common.convertDateToSqlDate(tblUser.getBirthday());
		String hashPassword = String.valueOf(tblUser.getPassword().hashCode());
		StringBuilder insertQuery = new StringBuilder();
		insertQuery.append("INSERT INTO tbl_user");
		insertQuery.append(" (group_id, login_name, pass, full_name, full_name_kana, email, tel, birthday)");
		insertQuery.append(" VALUES");
		insertQuery.append(" (?, ?, ?, ?, ?, ?, ?, ?);");
		PreparedStatement preparedStatement = conn.prepareStatement(insertQuery.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
		int i = 1;
		preparedStatement.setInt(i++, tblUser.getGroupId());
		preparedStatement.setString(i++, tblUser.getLoginName());
		preparedStatement.setString(i++, hashPassword);
		preparedStatement.setString(i++, tblUser.getFullName());
		preparedStatement.setString(i++, tblUser.getFullNameKana());
		preparedStatement.setString(i++, tblUser.getEmail());
		preparedStatement.setString(i++, tblUser.getTel());
		preparedStatement.setDate(i++, sqlDateBirthday);
		// run Insert
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				tblUser.setUserId(rs.getInt(1));
			}
			isInsertUserSuccess = true;
		} else {
			isInsertUserSuccess = false;
		}
		return isInsertUserSuccess;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#getUserInforByUserId(int)
	 */
	@Override
	public UserInfor getUserInforByUserId(int userId) {
		// TODO Auto-generated method stub
		UserInfor userInfor = null;
		if (connectToDB()) {
			StringBuilder sqlQuery = new StringBuilder();
			// SELECT table col
			sqlQuery.append("SELECT ");
			sqlQuery.append(" tbl_user.user_id,");
			sqlQuery.append(" tbl_user.group_id,");
			sqlQuery.append(" mst_group.group_name,");
			sqlQuery.append(" tbl_user.login_name,");
			sqlQuery.append(" tbl_user.full_name,");
			sqlQuery.append(" tbl_user.full_name_kana,");
			sqlQuery.append(" tbl_user.email,");
			sqlQuery.append(" tbl_user.tel,");
			sqlQuery.append(" tbl_user.birthday,");
			sqlQuery.append(" tbl_detail_user_japan.code_level,");
			sqlQuery.append(" mst_japan.name_level,");
			sqlQuery.append(" tbl_detail_user_japan.start_date,");
			sqlQuery.append(" tbl_detail_user_japan.end_date,");
			sqlQuery.append(" tbl_detail_user_japan.total");
			// Join table
			sqlQuery.append(" FROM tbl_user");
			sqlQuery.append(" INNER JOIN  mst_group");
			sqlQuery.append(" ON tbl_user.group_id = mst_group.group_id");
			sqlQuery.append(" LEFT JOIN (tbl_detail_user_japan");
			sqlQuery.append(" INNER JOIN mst_japan");
			sqlQuery.append(" ON tbl_detail_user_japan.code_level = mst_japan.code_level)");
			sqlQuery.append(" ON tbl_user.user_id = tbl_detail_user_japan.user_id");
			// WHERE condition
			sqlQuery.append(" WHERE");
			sqlQuery.append(" tbl_user.user_id = ?");
			// Prepared query
			try {
				preparedStatement = connection.prepareStatement(sqlQuery.toString());
				int i = 1;
				preparedStatement.setInt(i++, userId);
				rs = preparedStatement.executeQuery();
				if (rs != null && rs.next()) {
					userInfor = new UserInfor(
							rs.getInt("user_id"),
							rs.getInt("group_id"),
							rs.getString("group_name"),
							rs.getString("login_name"),
							rs.getString("full_name"),
							rs.getString("full_name_kana"),
							rs.getString("email"),
							rs.getString("tel"),
							rs.getDate("birthday"),
							rs.getString("code_level"),
							rs.getString("name_level"),
							rs.getDate("start_date"),
							rs.getDate("end_date"),
							rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error : " + e.getMessage());
			}
		}
		return userInfor;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#updateUser(net.luvina.manageuser.entities.TblUser)
	 */
	@Override
	public boolean updateUser(TblUser tblUser, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		boolean isUpdateUserSuccess = false;
		java.sql.Date sqlDateBirthday = Common.convertDateToSqlDate(tblUser.getBirthday());
		String password = tblUser.getPassword();
		String hashPassword = String.valueOf(password.hashCode());
		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("UPDATE tbl_user");
		updateQuery.append(" SET");
		updateQuery.append(" tbl_user.group_id = ?,");
		updateQuery.append(" tbl_user.login_name = ?,");
		if (password.length() > 0) {
			updateQuery.append(" tbl_user.pass = ?,");
		}
		updateQuery.append(" tbl_user.full_name = ?,");
		updateQuery.append(" tbl_user.full_name_kana = ?,");
		updateQuery.append(" tbl_user.email = ?,");
		updateQuery.append(" tbl_user.tel = ?,");
		updateQuery.append(" tbl_user.birthday = ?");
		updateQuery.append(" WHERE");
		updateQuery.append(" tbl_user.user_id = ?;");
		PreparedStatement preparedStatement = conn.prepareStatement(updateQuery.toString());
		int i = 1;
		preparedStatement.setInt(i++, tblUser.getGroupId());
		preparedStatement.setString(i++, tblUser.getLoginName());
		if (password.length() > 0) {
			preparedStatement.setString(i++, hashPassword);
		}
		preparedStatement.setString(i++, tblUser.getFullName());
		preparedStatement.setString(i++, tblUser.getFullNameKana());
		preparedStatement.setString(i++, tblUser.getEmail());
		preparedStatement.setString(i++, tblUser.getTel());
		preparedStatement.setDate(i++, sqlDateBirthday);
		preparedStatement.setInt(i++, tblUser.getUserId());
		// run Insert
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			isUpdateUserSuccess = true;
		} else {
			isUpdateUserSuccess = false;
		}
		return isUpdateUserSuccess;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.TblUserDao#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId, Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		boolean isDeleteUserSuccess = false;
		StringBuilder deleteQuery = new StringBuilder();
		deleteQuery.append("DELETE FROM tbl_user");
		deleteQuery.append(" WHERE tbl_user.user_id = ?;");
		PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery.toString());
		int i =1;
		preparedStatement.setInt(i++, userId);
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			isDeleteUserSuccess = true;
		} else {
			isDeleteUserSuccess = false;
		}
		return isDeleteUserSuccess;
	}

}
