/**
 * Copyright(C) @2016 Luvina Software Company
 * ValidateUser.java, Jun 13, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.validates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.impl.LoginLogicImpl;
import net.luvina.manageuser.logics.impl.MstGroupLogicImpl;
import net.luvina.manageuser.logics.impl.MstJapanLogicImpl;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.utils.MessageErrorProperties;
import net.luvina.manageuser.utils.StringUtil;

/**
 * ValidateUser - Thực hiện check dữ liệu nhập vào
 * @author Nguyễn Văn Minh
 *
 */
public class ValidateUser {

	/**
	 * validateUserInfor - Thực hiện validate thông tin user nhập từ màn hình ADM003
	 *
	 * @param userInfor - đối tượng userInfor cần validate
	 * @return lsError - Danh sách lỗi
	 */
	public static List<String> validateUserInfor(UserInfor userInfor) {
		List<String> lsError = new ArrayList<String>();
		String codeLevel = userInfor.getCodeLevel();
		Date startDate = userInfor.getStartDate();
		Date endDate = userInfor.getEndDate();
		Integer userId = userInfor.getUserId();
		addErrorMessage(validateLoginName(userInfor.getLoginName(),userId), lsError);
		addErrorMessage(validateGroup(userInfor.getGroupId()), lsError);
		addErrorMessage(validateFullName(userInfor.getFullName()), lsError);
		addErrorMessage(validateFullNameKana(userInfor.getFullNameKana()), lsError);
		addErrorMessage(validateBirthday(userInfor.getBirthday()), lsError);
		addErrorMessage(validateEmail(userInfor.getEmail(), userId), lsError);
		addErrorMessage(validateTel(userInfor.getTel()), lsError);
		addErrorMessage(validatePassword(userInfor.getPassword(), userId), lsError);
		addErrorMessage(validatePasswordConfirm(userInfor.getPassword(), userInfor.getPasswordConfirm(), userId), lsError);
		if (!"".equals(codeLevel)) {
			addErrorMessage(validateJapanLevel(codeLevel), lsError);
			addErrorMessage(validateStartDate(startDate), lsError);
			addErrorMessage(validateEndDate(startDate, endDate), lsError);
			addErrorMessage(validateTotal(userInfor.getStrTotal()), lsError);
		}
		return lsError;
	}

	/**
	 * validateLoginName - Kiểm tra tên đăng nhập - loginName
	 *
	 * @param loginName
	 * @return strErrorLoginName - Thông báo lỗi
	 */
	private static String validateLoginName (String loginName,Integer userId) {
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		if (loginName.length() <= 0) {
			return MessageErrorProperties.getMessage("ER001_loginName");
		}
		// Start fix bug ID 96 - MinhNV 2016/06/30
		if (loginName.length() < 4 || loginName.length() > 15 ) {
		// End fix bug ID 96 - MinhNV 2016/06/30
			return MessageErrorProperties.getMessage("ER007_loginName");
		}
		if (!StringUtil.checkMatcherPattern(loginName, Constant.LOGIN_NAME_PATTERN)) {
			return MessageErrorProperties.getMessage("ER019");
		}
		if (tblUserLogicImpl.checkExistedLoginName(userId, loginName)) {
			return MessageErrorProperties.getMessage("ER003_loginName");
		}
		return "";
	}

	/**
	 * validateGroup - Kiểm tra nhóm
	 *
	 * @param groupId - Id nhóm
	 * @return strErrorGroup -  Thông báo lỗi nếu có
	 */
	private static String validateGroup(int groupId) {
		String strErrorGroup = "";
		MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
		if (groupId <= 0) {
			return MessageErrorProperties.getMessage("ER002_group");
		}
		if (!mstGroupLogicImpl.checkExitedGroup(groupId)) {
			return MessageErrorProperties.getMessage("ER004_group");
		}
		return strErrorGroup;
	}

	/**
	 * validateFullName - Kiểm tra fullName
	 *
	 * @param fullName - fullName
	 * @return strError - Thông báo lỗi, nếu có
	 */
	private static String validateFullName(String fullName) {
		if (fullName.length() <= 0) {
			return MessageErrorProperties.getMessage("ER001_fullName");
		}
		if (fullName.length() >= 256) {
			return MessageErrorProperties.getMessage("ER006_fullName");
		}
		return "";
	}

	/**
	 * validateFullNameKana - Kiểm tra fullNameKana
	 * @param fullNameKana
	 * @return - Thông báo lỗi, nếu có
	 */
	private static String  validateFullNameKana(String fullNameKana) {
		if (fullNameKana.length() > 0) {
			if (!StringUtil.checkKanakata(fullNameKana)) {
				return MessageErrorProperties.getMessage("ER009_fullNameKana");
			}
			if (fullNameKana.length() >= 256) {
				return MessageErrorProperties.getMessage("ER006_fullNameKana");
			}
		}
		return "";
	}

	/**
	 * validateBirthday - Kiểm tra Birthday
	 * @param birthday - ngày sinh
	 * @return String - Thông báo lỗi, nếu có
	 */
	private static String  validateBirthday(Date birthday) {
		if (!Common.checkExitedDate(birthday)) {
			return MessageErrorProperties.getMessage("ER011_birthday");
		}
		return "";
	}

	/**
	 * validateEmail - Kiểm tra Email
	 *
	 * @param email - User Email
	 * @return String - Thông báo lỗi, nếu có
	 */
	private static String validateEmail(String email, Integer userId) {
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();

		if (email.trim().length() <= 0) {
			return MessageErrorProperties.getMessage("ER001_email");
		}
		if (email.trim().length() >= 256) {
			return MessageErrorProperties.getMessage("ER006_email");
		}
		if (!StringUtil.checkMatcherPattern(email, Constant.EMAIL_PATTERN)) {
			return MessageErrorProperties.getMessage("ER005_email");
		}
		if (tblUserLogicImpl.checkExistedEmail(userId, email)) {
			return MessageErrorProperties.getMessage("ER003_email");
		}
		return "";
	}

	/**
	 * validateTel - Kiểm tra số điện thoại
	 *
	 * @param tel - số điện thoại
	 * @return String - Thông báo lỗi, nếu có
	 */
	private static String validateTel(String tel) {
		if (tel.trim().length() <= 0) {
			return MessageErrorProperties.getMessage("ER001_tel");
		}
		// Start fix bug ID 98 - MinhNV - 2016/30/06
		if (tel.trim().length() > 14) {
			return MessageErrorProperties.getMessage("ER006_tel");
		}
		if (!StringUtil.checkMatcherPattern(tel, Constant.TEL_PATTERN)) {
			return MessageErrorProperties.getMessage("ER005_tel");
		}
		// End fix bug ID 98 - MinhNV - 2016/30/06
		return "";
	}

	/**
	 * validatePassword - Kiểm tra mật khẩu
	 *
	 * @param password - Mật khẩu
	 * @return String - Thông báo lỗi nếu có
	 */
	private static String validatePassword(String password, Integer userId) {
		if ((userId != null && password.length() > 0) || userId == null) {
			if (password.trim().length() <= 0) {
				return MessageErrorProperties.getMessage("ER001_password");
			}
			// Start fix bug ID 104, 105, 106, 107 - MinhNV - 2016/07/01
			if (password.trim().length() < 5 || password.trim().length() > 15) {
				return MessageErrorProperties.getMessage("ER007_password");
			}
			if (!StringUtil.isEnglish(password)) {
				return MessageErrorProperties.getMessage("ER008_password");
			}
			// End fix bug ID 104, 105, 106, 107 - MinhNV - 2016/07/01
		}
		return "";
	}

	/**
	 * validatePasswordConfirm - Kiểm tra mật khẩu xác nhận
	 * @param password - Mật khẩu
	 * @param passwordConfirm - Mật khẩu xác nhận
	 * @return String - Thông báo lỗi nếu có
	 */
	private static String validatePasswordConfirm(String password, String passwordConfirm, Integer userId) {
		if ((userId != null && password.length() > 0) || userId == null) {
			if (!passwordConfirm.equals(password)) {
				return MessageErrorProperties.getMessage("ER017");
			}
		}
		return "";
	}

	/**
	 * validateJapanLevel - Kiểm tra trình độ tiếng nhật
	 *
	 * @param codeLevel
	 * @return String - Thông báo lỗi nếu có
	 */
	private static String validateJapanLevel(String codeLevel) {
		if (!"".equals(codeLevel)) {
			MstJapanLogicImpl mstJapanLogicImpl = new MstJapanLogicImpl();
			if (!mstJapanLogicImpl.checkExitedJapan(codeLevel)) {
				return MessageErrorProperties.getMessage("ER004_codeLevel");
			}
		}
		return "";
	}

	/**
	 * validateStartDate - Kiểm tra ngày cấp chứng chỉ
	 *
	 * @param startDate - ngày cấp chứng chỉ
	 * @return String - Thông báo lỗi nếu có
	 */
	private static String validateStartDate(Date startDate) {
		if (!Common.checkExitedDate(startDate)) {
			return MessageErrorProperties.getMessage("ER011_startDate");
		}
		return "";
	}

	/**
	 * validateEndDate - Kiểm tra ngày hết hạn
	 *
	 * @param startDate - Ngày cấp
	 * @param endDate - Ngày hết hạn
	 * @return String - Thông báo lỗi nếu có
	 */
	private static String validateEndDate(Date startDate, Date endDate) {
		if (!Common.checkExitedDate(endDate)) {
			return MessageErrorProperties.getMessage("ER011_endDate");
		}
		if (!Common.compareDate(endDate, startDate)) {
			return MessageErrorProperties.getMessage("ER012");
		}
		return "";
	}

	/**
	 * validateTotal - kiểm tra tổng điểm
	 *
	 * @param total - tổng điểm
	 * @return String - Thông báo lỗi nếu có
	 */
	private static String validateTotal(String  strTotal) {
		if (strTotal.length() <= 0) {
			return MessageErrorProperties.getMessage("ER001_total");
		}
		if ((!StringUtil.checkMatcherPattern(strTotal, Constant.HALFSIZE_PATTERN))) {
			return MessageErrorProperties.getMessage("ER018_total");
		}
		return "";
	}

	/**
	 * validateLogin - check dữ liệu đăng nhập
	 *
	 * @param loginId
	 * @param password
	 * @return lsErrorMess - danh sách lỗi
	 */
	public static List<String> validateLogin(String loginId, String password) {
		List<String> lsErrorMess = new ArrayList<String>();
		if (loginId.length() == 0) {
			lsErrorMess.add(MessageErrorProperties.getMessage("ER001_loginId"));
		}
		if (password.length() == 0) {
			lsErrorMess.add(MessageErrorProperties.getMessage("ER001_password"));
		}
		LoginLogicImpl loginLogic =  new LoginLogicImpl();
		if (loginId.length() != 0 && password.length() != 0) {
			if (!loginLogic.existLoginId(loginId, password)) {
				lsErrorMess.add(MessageErrorProperties.getMessage("ER016"));
			}
		}
		return lsErrorMess;
	}


	/**
	 * addErrorMessage - ADD thông báo lỗi vào danh sách thông báo lỗi
	 *
	 * @param strError - Thông báo lỗi
	 * @param lsError - Danh sách thông báo lỗi
	 */
	public static void addErrorMessage(String strError, List<String> lsError) {
		if (!"".equals(strError)) {
			lsError.add(strError);
		}
	}
}
