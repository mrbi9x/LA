/**
 * Copyright(C) @2016 Luvina Software Company
 * MstGroupDao.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao;

import java.util.List;

import net.luvina.manageuser.entities.MstGroup;

/**
 * MstGroupDao - interface của lớp MstGroupDaoImpl - xử lý dữ liệu với bảng MstGroup
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface MstGroupDao {

	/**
	 * getAllMstGroup - Hàm lấy về tất các các nhóm trong bảng MstGroup
	 *
	 * @return List<MstGroup> - danh sách tất cả group trong bảng MstGroup
	 */
	public List<MstGroup> getAllMstGroup();

	/**
	 * getGroupByGroupID - Hàm lấy về group theo groupId
	 *
	 * @param groupId - Id nhóm
	 * @return MstGroup đối tượng MstGroup
	 */
	public MstGroup getGroupByGroupId(final int groupId);
}
