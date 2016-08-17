/**
 * Copyright(C) @2016 Luvina Software Company
 * UserInfor.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * UserInfor - JavaBean chứa các thuộc tính của đối tượng UserInfor
 * @author Nguyễn Văn Minh
 *
 */
public class UserInfor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String loginName;
	private String password;
	private String passwordConfirm;
	private String fullName;
	private String fullNameKana;
	private Date birthday;
	private int groupId;
	private String groupName;
	private String email;
	private String tel;
	private String codeLevel;
	private String nameLevel;
	private Date startDate;
	private Date endDate;
	private Integer total;
	private String strTotal;
	/**
	 *
	 */
	public UserInfor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor for ADM002
	 * @param userId
	 * @param fullName
	 * @param birthday
	 * @param groupName
	 * @param email
	 * @param tel
	 * @param nameLevel
	 * @param endDate
	 * @param total
	 */
	public UserInfor(int userId, String fullName, Date birthday,
			String groupName, String email, String tel, String nameLevel,
			Date endDate, Integer total) {
		this.userId = userId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.groupName = groupName;
		this.email = email;
		this.tel = tel;
		this.nameLevel = nameLevel;
		this.endDate = endDate;
		this.total = total;
	}




	/**
	 * Constructor with 15 param - Use for export CSV and ListUser
	 * @param loginName
	 * @param password
	 * @param passwordConfirm
	 * @param fullName
	 * @param fullNameKana
	 * @param birthday
	 * @param groupId
	 * @param groupName
	 * @param email
	 * @param tel
	 * @param codeLevel
	 * @param nameLevel
	 * @param startDate
	 * @param endDate
	 * @param total
	 * @param strTotal
	 */
	public UserInfor(Integer userId, String loginName, String password, String passwordConfirm,
			String fullName, String fullNameKana, Date birthday, int groupId,
			String groupName, String email, String tel, String codeLevel,
			String nameLevel, Date startDate, Date endDate, Integer total, String strTotal) {
		this.userId = userId;
		this.loginName = loginName;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.birthday = birthday;
		this.groupId = groupId;
		this.groupName = groupName;
		this.email = email;
		this.tel = tel;
		if (codeLevel == null || "".equals(codeLevel)) {
			this.codeLevel = "";
			this.nameLevel = "";
			this.startDate = null;
			this.endDate = null;
			this.total = null;
		} else {
			this.codeLevel = codeLevel;
			this.nameLevel = nameLevel;
			this.startDate = startDate;
			this.endDate = endDate;
			this.total = total;
		}
		this.strTotal = strTotal;
	}

	/**
	 * Constructor for ADM005 - ADM003 edit
	 *
	 * @param userId
	 * @param groupId
	 * @param groupName
	 * @param loginName
	 * @param fullName
	 * @param fullNameKana
	 * @param email
	 * @param tel
	 * @param birthday
	 * @param codeLevel
	 * @param nameLevel
	 * @param startDate
	 * @param endDate
	 * @param total
	 */
	public UserInfor(Integer userId, int groupId, String groupName,
			String loginName, String fullName, String fullNameKana,
			String email, String tel, Date birthday, String codeLevel,
			String nameLevel, Date startDate, Date endDate, Integer total) {
		this.userId = userId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.loginName = loginName;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.email = email;
		this.tel = tel;
		this.birthday = birthday;
		this.codeLevel = codeLevel;
		this.nameLevel = nameLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKana the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginId() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginId(String loginId) {
		this.loginName = loginId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}
	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the strTotal
	 */
	public String getStrTotal() {
		return strTotal;
	}

	/**
	 * @param strTotal the strTotal to set
	 */
	public void setStrTotal(String strTotal) {
		this.strTotal = strTotal;
	}



}
