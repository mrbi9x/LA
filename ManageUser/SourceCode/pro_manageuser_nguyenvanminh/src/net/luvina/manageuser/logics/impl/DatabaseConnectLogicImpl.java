/**
 * Copyright(C) @2016 Luvina Software Company
 * DatabaseConnectLogicImpl.java, Jun 20, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import net.luvina.manageuser.dao.impl.BaseDaoImpl;
import net.luvina.manageuser.logics.DatabaseConnectLogic;

/**
 * DatabaseConnectLogicImpl - Xử lý logic kiểm tra kết nối database
 *
 * @author Nguyễn Văn Minh
 *
 */
public class DatabaseConnectLogicImpl implements DatabaseConnectLogic {

	/**
	 *
	 */
	public DatabaseConnectLogicImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.DatabaseConnectLogic#CheckDatabaseConnect()
	 */
	@Override
	public boolean CheckDatabaseConnect() {
		// TODO Auto-generated method stub
		boolean checkDatabaseConnect = false;
		BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
		if (baseDaoImpl.connectToDB()) {
			checkDatabaseConnect = true;
		} else {
			checkDatabaseConnect = false;
		}
		return checkDatabaseConnect;
	}

}
