/**
 * Copyright(C) @2016 Luvina Software Company
 * MstJapan.java, Jun 22, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;

/**
 * MstJapan - Javabean ánh xạ bảng MstJapan
 * @author Nguyễn Văn Minh
 *
 */
public class MstJapan implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String codeLevel;
	private String nameLevel;

	/**
	 * Constructor mặc định
	 */
	public MstJapan() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Constructor có tham số
	 * @param codeLevel
	 * @param nameLevel
	 */
	public MstJapan(String codeLevel, String nameLevel) {
		this.codeLevel = codeLevel;
		this.nameLevel = nameLevel;
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



}
