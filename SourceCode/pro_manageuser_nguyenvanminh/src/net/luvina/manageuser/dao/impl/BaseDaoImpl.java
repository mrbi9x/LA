/**
 * Copyright(C) @2016 Luvina Software Company
 * BaseDaoImpl.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.luvina.manageuser.dao.BaseDao;
import net.luvina.manageuser.utils.DatabaseProperties;

/**
 * BaseDaoImpl - Class xử lý các thao tác với database : Mở kết nối, đóng kết
 * nối
 *
 * @author Nguyễn Văn Minh
 *
 */
public class BaseDaoImpl implements BaseDao {

	protected Connection connection = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet rs = null;

	/**
	 *
	 */
	public BaseDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.BaseDao#closeConnect()
	 */
	@Override
	public void closeConnect() {
		// TODO Auto-generated method stub
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("Connect database exception occur: " + e.getMessage());
			}
			connection = null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.BaseDao#connectToDB()
	 */
	@Override
	public boolean connectToDB() {
		// TODO Auto-generated method stub
		boolean result = true;
		try {
			// load mysql driver
			Class.forName(DatabaseProperties.getData("driver"));
			connection = DriverManager.getConnection(DatabaseProperties
					.getData("url"), DatabaseProperties.getData("user"),
					DatabaseProperties.getData("password"));
		} catch (Exception e) {
			System.out.println("Connect database exception occur: " + e.getMessage());
			result = false;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.BaseDao#getConn()
	 */
	@Override
	public Connection getConn() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection(DatabaseProperties.getData("url"),
				DatabaseProperties.getData("user"),
				DatabaseProperties.getData("password"));
	}
}
