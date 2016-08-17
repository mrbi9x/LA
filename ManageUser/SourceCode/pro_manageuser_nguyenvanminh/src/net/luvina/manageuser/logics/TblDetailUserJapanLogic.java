/**
 * Copyright(C) @2016 Luvina Software Company
 * TblDetailUserJapanLogic.java, Jul 6, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TblDetailUserJapanLogic - Xử lý các logic với bảng TblDetailUserJapan
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface TblDetailUserJapanLogic {

	/**
	 * checkExictDetailUserJapan - Kiểm tra xem đã tồn tại DetailUserJapan theo userId
	 *
	 * @param userId - Id của User
	 * @return True - Nếu tồn tại
	 * False - Nếu không tồn tại
	 */
	public boolean checkExictDetailUserJapan(int userId, Connection conn) throws SQLException;


}
