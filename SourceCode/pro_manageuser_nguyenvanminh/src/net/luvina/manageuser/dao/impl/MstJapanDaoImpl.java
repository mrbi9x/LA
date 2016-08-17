/**
 * Copyright(C) @2016 Luvina Software Company
 * MstJapanDaoImpl.java, Jun 22, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.MstJapanDao;
import net.luvina.manageuser.entities.MstJapan;

/**
 * MstJapanDaoImpl - Thao tác với database bảng MstJapan
 * @author Nguyễn Văn Minh
 *
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/**
	 *
	 */
	public MstJapanDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.MstJapanDao#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() {
		// TODO Auto-generated method stub
		List<MstJapan> listJapan = new ArrayList<MstJapan>();

		try {
			connectToDB();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT * FROM mst_japan");
			// Sort by name_level ASC
			sqlQuery.append(" ORDER BY code_level DESC;");

			preparedStatement = connection.prepareStatement(sqlQuery.toString());
			// Run Sql Query
			rs = preparedStatement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					MstJapan mstJapan = new MstJapan(rs.getString("code_level"),
													rs.getString("name_level"));
					listJapan.add(mstJapan);
				}
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("MstJapanDaoImpl exception : " + e.getMessage());
		} finally {
			closeConnect();
		}
		return listJapan;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.MstJapanDao#getJapanByCodeLevel(java.lang.String)
	 */
	@Override
	public MstJapan getJapanByCodeLevel(String codeLevel) {
		// TODO Auto-generated method stub
		MstJapan mstJapan = null;
		try {
			connectToDB();
			StringBuilder sqlQuery = new StringBuilder();
			// Get japan
			sqlQuery.append("SELECT * FROM mst_japan");
			// WHERE
			sqlQuery.append(" WHERE mst_japan.code_level = ?");
			// Prepare Query
			preparedStatement = connection.prepareStatement(sqlQuery.toString());
			preparedStatement.setString(1, codeLevel);
			//run sql query
			rs = preparedStatement.executeQuery();
			if (rs != null && rs.next()) {
				mstJapan = new MstJapan(rs.getString("code_level"),
										rs.getString("name_level"));
				rs.close();
			}
		} catch (SQLException se) {
			// TODO: handle exception
			System.out.println("SQLException : " + se.getMessage());
		} finally {
			closeConnect();
		}
		return mstJapan;
	}
}
