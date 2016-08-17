/**
 * Copyright(C) 2016 Luvina Software Company
 * SearchData.java, Jul 9, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;

/**
 * SearchData.java - Đối tượng lưu giá trị Search màn hình ADM002
 * @author Nguyễn Văn Minh
 *
 */
public class SessionData implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String keySearchFullName;
	private Integer groupId;
	private String sortType;
	private String sortByFullName;
	private String sortByCodeLevel;
	private String sortByEndDate;
	private Integer currentPage;
	private UserInfor userInfor;
	/**
	 *
	 */
	public SessionData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor không có UserInfor
	 * @param keySearchFullName
	 * @param groupId
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @param currentPage
	 */
	public SessionData(String keySearchFullName, Integer groupId,
			String sortType, String sortByFullName, String sortByCodeLevel,
			String sortByEndDate, Integer currentPage) {
		this.keySearchFullName = keySearchFullName;
		this.groupId = groupId;
		this.sortType = sortType;
		this.sortByFullName = sortByFullName;
		this.sortByCodeLevel = sortByCodeLevel;
		this.sortByEndDate = sortByEndDate;
		this.currentPage = currentPage;
	}




	/**
	 * Constructor tất cả tham số
	 *
	 * @param keySearchFullName
	 * @param groupId
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @param currentPage
	 * @param userInfor
	 */
	public SessionData(String keySearchFullName, Integer groupId,
			String sortType, String sortByFullName, String sortByCodeLevel,
			String sortByEndDate, Integer currentPage, UserInfor userInfor) {
		this.keySearchFullName = keySearchFullName;
		this.groupId = groupId;
		this.sortType = sortType;
		this.sortByFullName = sortByFullName;
		this.sortByCodeLevel = sortByCodeLevel;
		this.sortByEndDate = sortByEndDate;
		this.currentPage = currentPage;
		this.userInfor = userInfor;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}



	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}



	/**
	 * @return the keySearchFullName
	 */
	public String getKeySearchFullName() {
		return keySearchFullName;
	}
	/**
	 * @param keySearchFullName the keySearchFullName to set
	 */
	public void setKeySearchFullName(String keySearchFullName) {
		this.keySearchFullName = keySearchFullName;
	}
	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the sortType
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	/**
	 * @return the sortByFullName
	 */
	public String getSortByFullName() {
		return sortByFullName;
	}
	/**
	 * @param sortByFullName the sortByFullName to set
	 */
	public void setSortByFullName(String sortByFullName) {
		this.sortByFullName = sortByFullName;
	}
	/**
	 * @return the sortByCodeLevel
	 */
	public String getSortByCodeLevel() {
		return sortByCodeLevel;
	}
	/**
	 * @param sortByCodeLevel the sortByCodeLevel to set
	 */
	public void setSortByCodeLevel(String sortByCodeLevel) {
		this.sortByCodeLevel = sortByCodeLevel;
	}
	/**
	 * @return the sortByEndDate
	 */
	public String getSortByEndDate() {
		return sortByEndDate;
	}
	/**
	 * @param sortByEndDate the sortByEndDate to set
	 */
	public void setSortByEndDate(String sortByEndDate) {
		this.sortByEndDate = sortByEndDate;
	}

	/**
	 * @param userInfor the userInfor to set
	 */
	public void setUserInfor(UserInfor userInfor) {
		this.userInfor = userInfor;
	}

	/**
	 * @return the userInfor
	 */
	public UserInfor getUserInfor() {
		return userInfor;
	}


}
