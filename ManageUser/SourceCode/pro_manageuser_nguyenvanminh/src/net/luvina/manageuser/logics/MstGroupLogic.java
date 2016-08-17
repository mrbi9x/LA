/**
 * Copyright(C) @2016 Luvina Software Company
 * MstGroupLogic.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics;

import java.util.List;

import net.luvina.manageuser.entities.MstGroup;

/**
 * MstGroupLogic - Xử lý logic cho bảng MstGroup
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface MstGroupLogic {

	/**
	 * getAllGroup - Lấy tất cả group có trong bảng MstGroup
	 *
	 * @return List<MstGroup> - Danh sách group
	 */
	public List<MstGroup> getAllMstGroup();

	/**
	 * checkExitedGroup - Kiểm tra group đã tồn tại trong bảng mst_group chưa?
	 * @param groupId - id nhóm
	 * @return True - Nếu đã tồn tại
	 * 		False - Nếu chưa tồn tại
	 */
	public boolean checkExitedGroup(int groupId);

	/**
	 * getMstGroupByGroupId - Lấy MstGroup theo groupId
	 *
	 * @param groupId - Mã nhóm
	 * @return MstGroup - đối tượng mstGroup
	 */
	public MstGroup getMstGroupByGroupId (int groupId);
}
