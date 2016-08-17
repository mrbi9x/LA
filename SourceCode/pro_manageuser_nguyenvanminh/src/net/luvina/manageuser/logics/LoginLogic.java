/**
 * Copyright(C) @2016 Luvina Software Company
 * LoginLogic.java, Jun 13, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics;

/**
 * LoginLogic - Xử lý logic Đăng nhập
 * @author Nguyễn Văn Minh
 *
 */
public interface LoginLogic {
	/**
     * Check existLoginId - Kiểm tra tên đăng nhập và mật khẩu người dùng nhập
     *
     * @param loginId
     *            String loginId
     * @param password
     *            String password
     * @return true: existed
     *  false: not exist
     */
    public Boolean existLoginId(String loginId, String password);
}
