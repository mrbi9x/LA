/**
 * Copyright(C) @2016 Luvina Software Company
 * MstGroup.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;

/**
 * MstGroup - JavaBean ánh xạ bảng MstGroup
 * @author Nguyễn Văn Minh
 *
 */
public class MstGroup implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
	private String groupName;


	/**
	 * Constructor MstGroup with two field
	 * @param groupId
	 * @param groupName
	 */
	public MstGroup(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	/**
	 *
	 */
	public MstGroup() {
		// TODO Auto-generated constructor stub
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


}
