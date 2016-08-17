/**
 * Copyright(C) @2016 Luvina Software Company
 * TblUserLogic.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics;

import java.util.List;

import net.luvina.manageuser.entities.UserInfor;

/**
 * TblUserLogic - Xử lý các logic với bảng TblUser
 * @author Nguyễn Văn Minh
 *
 */
public interface TblUserLogic {

	/**
	 * getListUser - Lấy danh sách UserInfor theo groupId, fullName, page và theo sắp xếp
	 *
	 * @param offset - Vị trí bắt đầu lấy dữ liệu
	 * @param limit - Số bản ghi cần lấy
	 * @param groupId -  Id nhóm
	 * @param fullName - fullName
	 * @param sortType - Cột ưu tiên sắp xếp
	 * @param sortByFullName - Giá trị sắp xếp theo fullName ASC hoặc DESC
	 * @param sortByCodeLevel - Giá trị sắp xếp theo codeLevel ASC hoặc DESC
	 * @param sortByEndDate - Giá trị sắp xếp theo endDate ASC hoặc DESC
	 * @return List UserInfor - Danh sách UserInfor
	 */
	public List<UserInfor> getListUser(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate);

	/**
	 * getTotalUsers - Lấy tổng User ghi theo groupId và fullName
	 *
	 * @param groupId
	 * @param fullName
	 * @return int totalUser - Tổng số User
	 */
	public int countTotalUsers(int groupId, String fullName);

	/**
	 * getListUserByCodeLevel - Hàm xử lý lấy về danh sách UserInfor theo trình độ tiếng nhật
	 *
	 * @param isGetAllUserInfor - Có lấy tất cả hay không
	 * @param japanCodeLevel - Các trình độ tiếng Nhật cần lấy
	 * @return List<UserInfor> - Danh sách UserInfor
	 */
	public List<UserInfor> getListUserByCodeLevel(boolean isGetAllUserInfor, String[] japanCodeLevel);

	/**
	 * checkExistedLoginName - Kiểm tra loginName đã tồn tại trong bảng tbl_user chưa?
	 *
	 * @param userId - ID User
	 * @param loginName - tên đăng nhập của User
	 * @return true if exited - return true - loginName đã tồn tại
	 * 			false if not exited - Return false - loginName chưa tồn tại
	 */
	public boolean checkExistedLoginName (final Integer userId, final String loginName);

	/**
	 * checkExistedEmail - Kiểm tra email đã tồn tại trong bảng tbl_user chưa?
	 *
	 * @param userId - Integer userId
	 * @param email - String email
	 * @return True: email đã tồn tại
	 *			False: email không tồn tại
	 */
	public boolean checkExistedEmail (final Integer userId, final String email);

	/**
	 * createUser - Insert user vào bảng tbl_user và tbl_detail_user_japan
	 *
	 * @param userInfor - Đối tượng UserInfor
	 * @return - True:  insert thành công
	 * 	-	False: insert không thành công
	 */
	public boolean createUser(UserInfor userInfor);

	/**
	 * getUserInforByUserId - Hàm lấy về ViewUserInforId theo userId
	 * @param userId - id của user
	 * @return ViewUserInforId - Đối tượng ViewUserInforId
	 */
	public UserInfor getUserInforByUserId (int userId);

	/**
	 * updateUser - Update user vào bảng tbl_user và tbl_detail_user_japan
	 * @param userInfor - Đối tượng UserInfor
	 * @return - True:  update thành công
	 * False : update không thành công
	 */
	public boolean updateUser(UserInfor userInfor);

	/**
	 * deleteUser - Xóa user
	 *
	 * @param userId - id của user
	 * @return True - Xóa thành công
	 * False - Xóa không thành công
	 */
	public boolean deleteUser(int userId);
}
