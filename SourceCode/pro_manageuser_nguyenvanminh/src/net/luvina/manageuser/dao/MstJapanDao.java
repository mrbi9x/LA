/**
 * Copyright(C) @2016 Luvina Software Company
 * MstJapanDao.java, Jun 22, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao;

import java.util.List;

import net.luvina.manageuser.entities.MstJapan;

/**
 * MstJapanDao - Thao tác với database bảng MstJapan
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface MstJapanDao {

	/**
	 * getAllMstJapan - Hàm lấy về danh sách tất cả trình độ tiếng nhật theo tên
	 * @return listJanpan - Danh sách trình dộ tiếng nhật
	 */
	public List<MstJapan> getAllMstJapan();

	/**
	 * getJapanByCodeLevel - Hàm lấy về mstJapan theo codeLevel
	 *
	 * @param codeLevel - Tên trình độ tiếng nhật
	 * @return MstJapan - Đối tượng MstJapan
	 */
	public MstJapan getJapanByCodeLevel(final String codeLevel);
}
