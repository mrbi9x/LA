/**
 * Copyright(C) @2016 Luvina Software Company
 * TblUserLogicImpl.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.impl.BaseDaoImpl;
import net.luvina.manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import net.luvina.manageuser.dao.impl.TblUserDaoImpl;
import net.luvina.manageuser.entities.TblDetailUserJapan;
import net.luvina.manageuser.entities.TblUser;
import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.TblUserLogic;
import net.luvina.manageuser.utils.Common;

/**
 * TblUserLogicImpl - Xử lý logic với bảng TblUser
 *
 * @author Nguyễn Văn Minh
 *
 */
public class TblUserLogicImpl implements TblUserLogic {

	/**
	 *
	 */
	public TblUserLogicImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#getListUser(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUser(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate) {
		// TODO Auto-generated method stub
		if (fullName != null) {
			fullName = Common.escapeInjection(fullName);
		}
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		List<UserInfor> listUserInfor = tblUserDaoImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);
		return listUserInfor;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int countTotalUsers(int groupId, String fullName) {
		// TODO Auto-generated method stub
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		if (fullName != null) {
			fullName = Common.escapeInjection(fullName.toString());
		}
		int totalUsers = tblUserDaoImpl.countTotalUsers(groupId, fullName);
		return totalUsers;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#getListUserByCodeLevel(boolean, java.lang.String[])
	 */
	@Override
	public List<UserInfor> getListUserByCodeLevel(boolean isGetAllUserInfor,
			String[] japanCodeLevel) {
		// TODO Auto-generated method stub
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();

		return tblUserDaoImpl.getListUserByCodeLevel(isGetAllUserInfor, japanCodeLevel);
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#checkExistedLoginName(java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean checkExistedLoginName(final Integer userId,final String loginName) {
		// TODO Auto-generated method stub
		TblUser tblUser = null;
		boolean isExistedLoginName = false;

		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		tblUser = tblUserDaoImpl.checkExistedLoginName(userId, loginName);
		if (tblUser != null) {
			isExistedLoginName = true;
		} else {
			isExistedLoginName = false;
		}
		return isExistedLoginName;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#checkExistedEmail(java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean checkExistedEmail(final Integer userId,final String email) {
		// TODO Auto-generated method stub
		TblUser tblUser = null;
		boolean isExistedEmail = false;
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		tblUser = tblUserDaoImpl.getUserByEmail(userId, email);
		if (tblUser != null) {
			isExistedEmail = true;
		} else {
			isExistedEmail = false;
		}
		return isExistedEmail;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#createUser(net.luvina.manageuser.entities.UserInfor)
	 */
	@Override
	public boolean createUser(UserInfor userInfor) {
		// TODO Auto-generated method stub
		Boolean isCreateUser = false;
		Connection conn = null;
		BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		// Prepare data to insert
		TblDetailUserJapan tblDetailUserJapan = null;
		String codeLevel = userInfor.getCodeLevel();
		//Create obj tblUser
		TblUser tblUser = new TblUser(
				userInfor.getUserId(),
				userInfor.getGroupId(),
				userInfor.getLoginName(),
				userInfor.getPassword(),
				userInfor.getFullName(),
				userInfor.getFullNameKana(),
				userInfor.getEmail(),
				userInfor.getTel(),
				userInfor.getBirthday()
				);
		// Nếu tồn tại code Level -> Create obj TblDetailUserJapan
		if (!"".equals(codeLevel)) {
			tblDetailUserJapan = new TblDetailUserJapan(
					userInfor.getUserId(),
					codeLevel,
					userInfor.getStartDate(),
					userInfor.getEndDate(),
					userInfor.getTotal()
					);
		}
		// Start insert
		try {
			conn = baseDaoImpl.getConn();
			// Begin transaction
			conn.setAutoCommit(false);
			// Insert tblUser
			boolean isInsertUserSuccess = tblUserDaoImpl.insertUser(tblUser, conn);
			if (isInsertUserSuccess && tblDetailUserJapan != null ) {
				// Get inserted UserId from tblUser and set to tblDetailUserJapan
				tblDetailUserJapan.setUserId(tblUser.getUserId());
				TblDetailUserJapanDaoImpl tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
				// Insert tblDetailUserJapan
				tblDetailUserJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan, conn);
			}
			// End transaction commit
			conn.commit();
			isCreateUser = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				System.out.println("SQLException rollback : " + se.getMessage());
			}
			System.out.println("SQLException : " + e.getMessage());
		} finally {
				baseDaoImpl.closeConnect();
		}
		return isCreateUser;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#getUserByUserId(java.lang.Integer)
	 */
	@Override
	public UserInfor getUserInforByUserId(int userId) {
		// TODO Auto-generated method stub
		UserInfor userInfor = new UserInfor();
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		userInfor = tblUserDaoImpl.getUserInforByUserId(userId);
		return userInfor;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#updateUser(net.luvina.manageuser.entities.UserInfor)
	 */
	@Override
	public boolean updateUser(UserInfor userInfor) {
		// TODO Auto-generated method stub
		boolean isUpdateUserSuccess = false;
		Connection conn = null;
		BaseDaoImpl baseDaoImpl = null;
		// Prepare data to update
		TblDetailUserJapan tblDetailUserJapan = null;
		int userId = userInfor.getUserId();
		String codeLevel = userInfor.getCodeLevel();
		//Create obj tblUser
		TblUser tblUser = new TblUser(
				userId,
				userInfor.getGroupId(),
				userInfor.getLoginName(),
				userInfor.getPassword(),
				userInfor.getFullName(),
				userInfor.getFullNameKana(),
				userInfor.getEmail(),
				userInfor.getTel(),
				userInfor.getBirthday()
				);
		// Nếu tồn tại code Level -> Create obj TblDetailUserJapan
		if (!"".equals(codeLevel)) {
			tblDetailUserJapan = new TblDetailUserJapan(
					userId,
					codeLevel,
					userInfor.getStartDate(),
					userInfor.getEndDate(),
					userInfor.getTotal()
					);
		}
		try {
			baseDaoImpl = new BaseDaoImpl();
			// bắt đầu Transaction
			conn = baseDaoImpl.getConn();
			conn.setAutoCommit(false);
			// update User
			TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
			TblDetailUserJapanLogicImpl detailUserJapanLogicImpl = new TblDetailUserJapanLogicImpl();
			TblDetailUserJapanDaoImpl detailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
			tblUserDaoImpl.updateUser(tblUser, conn);
			boolean isExictDetailUser = detailUserJapanLogicImpl.checkExictDetailUserJapan(userId, conn);
			// Nếu chọn trình độ tiếng Nhật -> Insert hoặc Update DetailUser
			if (tblDetailUserJapan != null) {
				if (isExictDetailUser) {
					detailUserJapanDaoImpl.updateDetailUserJapan(tblDetailUserJapan, conn);
				} else {
					detailUserJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan, conn);
				}
			} else {
				if (isExictDetailUser) {
					detailUserJapanDaoImpl.deleteDetailUserJapan(userId, conn);
				}
			}
			conn.commit();
			isUpdateUserSuccess = true;
		} catch (SQLException se) {
			// TODO: handle exception
			try {
				conn.rollback();
			} catch (SQLException eRollback) {
				// TODO Auto-generated catch block
				System.out.println("SQLException : " + eRollback.getMessage());
			}
			System.out.println("SQLException : " + se.getMessage());
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException eRollback) {
				// TODO Auto-generated catch block
				System.out.println("SQLException : " + eRollback.getMessage());
			}
			System.out.println("Exception : " + e.getMessage());
		} finally {
			baseDaoImpl.closeConnect();
		}
		return isUpdateUserSuccess;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblUserLogic#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		boolean isDeleteUserSuccess = false;
		BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
		Connection conn = null;
		try {
			conn = baseDaoImpl.getConn();
			conn.setAutoCommit(false);
			// Begin delete
			TblUserDaoImpl userDaoImpl = new TblUserDaoImpl();
			TblDetailUserJapanLogicImpl detailUserJapanLogicImpl = new TblDetailUserJapanLogicImpl();
			TblDetailUserJapanDaoImpl detailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
			boolean isExictDetailUser = detailUserJapanLogicImpl.checkExictDetailUserJapan(userId, conn);
			if (isExictDetailUser) {
				detailUserJapanDaoImpl.deleteDetailUserJapan(userId, conn);
			}
			userDaoImpl.deleteUser(userId, conn);
			conn.commit();
			isDeleteUserSuccess = true;
		} catch (SQLException e) {
			// TODO: handle exception
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("SQLException - Rollback Exception : " + e1.getMessage());
			}
		} finally {
			baseDaoImpl.closeConnect();
		}
		return isDeleteUserSuccess;
	}


}
