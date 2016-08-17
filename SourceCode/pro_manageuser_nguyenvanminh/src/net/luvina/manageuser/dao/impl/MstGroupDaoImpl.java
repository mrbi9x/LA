/**
 * Copyright(C) @2016 Luvina Software Company
 * MstGroupDaoImpl.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.MstGroupDao;
import net.luvina.manageuser.entities.MstGroup;

/**
 * MstGroupDaoImpl - Class xử lý các thao tác dữ liệu với bảng MstGroup
 *
 * @author Nguyễn Văn Minh
 *
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	/**
	 *
	 */
	public MstGroupDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.MstGroupDao#getAllMstGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() {
		// TODO Auto-generated method stub
		List<MstGroup> listGroup = new ArrayList<MstGroup>();
		if (connectToDB()) {
			try {
				StringBuilder sqlQuery = new StringBuilder();
				// Get all group
				sqlQuery.append("SELECT * FROM mst_group");
				// Sort by group_id ASC
				sqlQuery.append(" ORDER BY group_id ASC;");

				preparedStatement = connection.prepareStatement(sqlQuery
						.toString());
				// run sql query
				rs = preparedStatement.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						MstGroup mstGroup = new MstGroup(rs.getInt("group_id"),
								rs.getString("group_name"));
						listGroup.add(mstGroup);
					}
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("an exception occur: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return listGroup;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.MstGroupDao#getGroupByGroupID(int)
	 */
	@Override
	public MstGroup getGroupByGroupId(int groupId) {
		// TODO Auto-generated method stub
		MstGroup mstGroup = null;
		if (groupId <= 0) {
			return mstGroup;
		}
		try {
			connectToDB();
			StringBuilder sqlQuery = new StringBuilder();
			// Get group
			sqlQuery.append("SELECT * FROM mst_group");
			// WHERE
			sqlQuery.append(" WHERE mst_group.group_id = ?");

			preparedStatement = connection
					.prepareStatement(sqlQuery.toString());
			// run sql query
			preparedStatement.setInt(1, groupId);
			rs = preparedStatement.executeQuery();
			if (rs != null && rs.next()) {
				mstGroup = new MstGroup(rs.getInt("group_id"), rs.getString("group_name"));
				rs.close();
			}
		} catch (SQLException se) {
			// TODO: handle exception
			System.out.println("SQLException : " + se.getMessage());
		} finally {
			closeConnect();
		}
		return mstGroup;
	}

}
