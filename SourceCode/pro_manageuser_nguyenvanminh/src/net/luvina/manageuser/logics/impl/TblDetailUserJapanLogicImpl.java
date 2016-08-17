/**
 * Copyright(C) @2016 Luvina Software Company
 * TblDetailUserJapanLogicImpl.java, Jul 6, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import java.sql.Connection;
import java.sql.SQLException;

import net.luvina.manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import net.luvina.manageuser.entities.TblDetailUserJapan;
import net.luvina.manageuser.logics.TblDetailUserJapanLogic;

/**
 * TblDetailUserJapanLogicImpl - Xử lý logic với bảng TblDetailUserJapan
 * @author Nguyễn Văn Minh
 *
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {

	/**
	 *
	 */
	public TblDetailUserJapanLogicImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblDetailUserJapanLogic#checkExictDetailUserJapan(int)
	 */
	@Override
	public boolean checkExictDetailUserJapan(int userId, Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		TblDetailUserJapan tblDetailUserJapan = null;
		TblDetailUserJapanDaoImpl tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
		tblDetailUserJapan = tblDetailUserJapanDaoImpl.getDetailUserJapanByUserId(userId, connection);
		if (tblDetailUserJapan == null) {
			return false;
		} else {
			return true;
		}
	}

}
