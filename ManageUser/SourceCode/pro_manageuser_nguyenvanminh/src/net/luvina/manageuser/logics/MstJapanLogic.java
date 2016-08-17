/**
 * Copyright(C) @2016 Luvina Software Company
 * MstJapanLogic.java, Jun 22, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics;

import java.util.List;

import net.luvina.manageuser.entities.MstJapan;

/**
 * MstJapanLogic - Xử lý logic với bảng MstJapan
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface MstJapanLogic {

	/**
	 * getAllMstJapan - Hàm lấy về tất cả tên trình độ tiếng nhật
	 *
	 * @return listJapan - Danh sách trình độ tiếng nhật
	 */
	public List<MstJapan> getAllMstJapan();

	/**
	 * checkExitedJapan - Kiểm tra trình độ tiếng nhật có tồn tại hay không theo mã trình độ tiếng nhật
	 *
	 * @param codeLevel - mã trình độ tiếng nhật
	 * @return True - Nếu tồn tại
	 * False - Nếu không tồn tại
	 */
	public boolean checkExitedJapan(String codeLevel);

	/**
	 * getMstJapanByCodeLevel - Hàm lấy MstJapan theo codeLevel
	 *
	 * @param codeLevel
	 * @return MstJapan - Đối tượng MstJapan
	 */
	public MstJapan getMstJapanByCodeLevel (String codeLevel);
}
