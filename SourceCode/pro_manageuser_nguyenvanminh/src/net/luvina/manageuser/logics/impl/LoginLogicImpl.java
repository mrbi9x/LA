/**
 * Copyright(C) @2016 Luvina Software Company
 * LoginLogicImpl.java, Jun 13, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import net.luvina.manageuser.logics.LoginLogic;
import net.luvina.manageuser.utils.ConfigProperties;

/**
 * LoginLogicImpl - Xử lý logic Login - Đăng nhập
 * @author Nguyễn Văn Minh
 *
 */
public class LoginLogicImpl implements LoginLogic {

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.LoginLogic#existLoginId(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean existLoginId(String loginId, String password) {
		// TODO Auto-generated method stub
		Boolean result = false;
		if (loginId.equals(ConfigProperties.getMessage("loginId"))  && password.equals(ConfigProperties.getMessage("password"))) {
			result = true;
		}
		return result;
	}

}
