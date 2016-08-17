/**
 * Copyright(C) @2016 Luvina Software Company
 * BaseDao.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * BaseDao - interface thể hiện cho lớp BaseDaoImpl - Xử lý kết nối db và đóng kết nối db
 * @author Nguyễn Văn Minh
 *
 */
public interface BaseDao {

	/**
     * connectToDB - Kiểm tra kết nối đến database
     *
     * @return true: connectToDB success
     *  false: connectToDB unsuccess
     */
    public boolean connectToDB();

    /**
     * closeConnect - Đóng kết nối database
     */
    public void closeConnect();

    /**
     * getConnection - Lấy về connection hiện tại để thực hiện transaction
     * @return Connection - Kết nối hiện tại
     * @throws SQLException - SQLException
     */
    public Connection getConn() throws SQLException;

}
