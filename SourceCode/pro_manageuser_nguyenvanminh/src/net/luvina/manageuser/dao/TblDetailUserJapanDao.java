/**
 * Copyright(C) @2016 Luvina Software Company
 * TblDetailUserJapanDao.java, Jun 28, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;


import net.luvina.manageuser.entities.TblDetailUserJapan;

/**
 * TblDetailUserJapanDao - Thao tác database với bảng TblDetailUserJapan
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface TblDetailUserJapanDao {

	/**
	 * insertDetailUserJapan - Thực hiện thêm mới 1 user vào bảng TblDetailUserJapan
	 *
	 * @param tblDetailUserJapan
	 * @return true: thành công
	 * false: không thành công
	 * @throws SQLException
	 */
	public boolean insertDetailUserJapan (TblDetailUserJapan tblDetailUserJapan, Connection connection) throws SQLException, Exception;

	/**
	 * deleteDetailUserJapan - Xóa trình độ tiếng Nhật của user
	 *
	 * @param tblDetailUserJapan - đối tượng TblDetailUserJapan
	 * @return True -> Xóa thành công
	 * False -> Xóa không thành công
	 */
	public boolean deleteDetailUserJapan(int userId, Connection connection) throws SQLException, Exception;

	/**
	 * updateDetailUserJapan - Cập nhật DetailUserJapan
	 *
	 * @param tblDetailUserJapan
	 * @return True - Nếu update thành công
	 * False - Nếu update không thành công
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean updateDetailUserJapan(TblDetailUserJapan tblDetailUserJapan, Connection connection) throws SQLException, Exception;

	/**
	 * getDetailUserJapanByUserId - Lấy DetailUserJapan theo UserId
	 *
	 * @param userId - Id của User
	 * @return TblDetailUserJapan - Đối tượng TblDetailUserJapan
	 */
	public TblDetailUserJapan getDetailUserJapanByUserId(int userId, Connection connection) throws SQLException, Exception;
}
