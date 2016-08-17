/**
 * Copyright(C) @2016 Luvina Software Company
 * TblUserDao.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.luvina.manageuser.entities.TblUser;
import net.luvina.manageuser.entities.UserInfor;

/**
 * TblUserDao - xử lý dữ liệu với bảng TblUser
 *
 * @author Nguyễn Văn Minh
 *
 */
public interface TblUserDao {

	/**
	 * getTotalUsers - Hàm lấy tổng số User theo groupId và fullName
	 *
	 * @param groupId - ID nhóm của User
	 * @param fullName - fullName của User
	 * @return int - Tổng số User theo điều kiện tìm kiếm
	 */
	public int countTotalUsers(int groupId, String fullName);

	/**
	 * getListUser - Lấy 1 danh sách User theo điều kiện tìm kiếm
	 * groupId, fullName, paging, sortType
	 *
	 * @param offset -  Vị trí bắt đầu lấy
	 * @param limit -  Số bản ghi cần lấy
	 * @param groupId - Id nhóm của User
	 * @param fullName - fullName của user
	 * @param sortType - Sắp xếp theo cột nào
	 * @param sortByFullName - Kiểu sắp xếp của cột fullName
	 * @param sortByCodeLevel - Kiểu sắp xếp của cột CodeLevel
	 * @param sortByEndDate - Kiểu sắp xếp của cột EndDate
	 * @return List<UserInfor> - Danh sách UserInfor
	 */
	public List<UserInfor> getListUser(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate);

	/**
	 * getListUserByCodeLevel - Lấy danh sách UserInfor theo trình độ tiếng Nhật
	 * @param isGetAllUserInfor
	 * @param japanCodeLevel
	 * @return listUserInfor - danh sách UserInfor theo trình độ tiếng Nhật
	 */
	public List<UserInfor> getListUserByCodeLevel(boolean isGetAllUserInfor, String[] japanCodeLevel);

	/**
	 * getUserByLoginName - Kiểm tra loginName đã tồn tại trong bảng tbl_user chưa?
	 * @param userId - ID User
	 * @param loginName - tên đăng nhập của User
	 * @return TblUser tblUser - obj User
	 */
	public TblUser checkExistedLoginName (final Integer userId, final String loginName);

	/**
	 * email - Lấy thông tin của user theo Email
	 *
	 * @param userId - ID User
	 * @param email - tên đăng nhập của User
	 * @return TblUser tblUser đối tượng TblUsser
	 */
	public TblUser getUserByEmail (final Integer userId, final String email);

	/**
	 * insertUser - Thực hiện thêm mới 1 user vào DB
	 *
	 * @param tblUser - Đối tượng chứa thông tin của user
	 * @return true: thành công
	 *	false: không thành công
	 * @throws SQLException
	 */
	public boolean insertUser (TblUser tblUser, Connection connection) throws SQLException;

	/**
	 * getUserInforByUserId - Hàm lấy về UserInfor theo Id
	 * @param userId - Id của user
	 * @return UserInforId - Đối tượng UserInforId
	 */
	public UserInfor getUserInforByUserId(int userId);

	/**
	 * updateUser -  Thực hiện cập nhật 1 user vào db
	 *
	 * @param tblUser - Đối tượng cần cập nhật
	 * @return - true Nếu cập nhật thành công
	 * False - Nếu cập nhật không thành công
	 * @throws SQLException
	 */
	public boolean updateUser(TblUser tblUser, Connection connection) throws SQLException;

	/**
	 * deleteUser - Xóa User từ bảng TblUser
	 *
	 * @param userId - id của User
	 * @return True - Nếu delete User thành công
	 * False - Nếu delete User không thành công
	 * @throws SQLException
	 */
	public boolean deleteUser(int userId, Connection connection) throws SQLException;

}
