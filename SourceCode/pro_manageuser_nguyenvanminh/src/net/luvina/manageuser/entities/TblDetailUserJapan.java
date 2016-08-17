/**
 * Copyright(C) @2016 Luvina Software Company
 * TblDetailUserJapanHbn.java, Jun 22, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * TblDetailUserJapan - JavaBean ánh xạ bảng TblDetailUserJapan
 * @author Nguyễn Văn Minh
 *
 */
public class TblDetailUserJapan implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//
	private Integer detailUserJapanId;
	private Integer userId;
	private String codeLevel;
	private Date startDate;
	private Date endDate;
	private Integer total;

	/**
	 * Constructor mặc định
	 */
	public TblDetailUserJapan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * TblDetailUserJapanHbn - Contructor với 6 tham số
	 *
	 * @param detailUserJapanId
	 * @param userId
	 * @param codeLevel
	 * @param startDate
	 * @param endDate
	 * @param total
	 */
	public TblDetailUserJapan(Integer detailUserJapanId, Integer userId,
			String codeLevel, Date startDate, Date endDate, Integer total) {
		this.detailUserJapanId = detailUserJapanId;
		this.userId = userId;
		this.codeLevel = codeLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}


	/**
	 * Constructor không có detailUserJapanId
	 * @param userId
	 * @param codeLevel
	 * @param startDate
	 * @param endDate
	 * @param total
	 */
	public TblDetailUserJapan(Integer userId, String codeLevel, Date startDate,
			Date endDate, Integer total) {
		this.userId = userId;
		this.codeLevel = codeLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}

	/**
	 * @return the detailUserJapanId
	 */
	public Integer getDetailUserJapanId() {
		return detailUserJapanId;
	}

	/**
	 * @param detailUserJapanId the detailUserJapanId to set
	 */
	public void setDetailUserJapanId(Integer detailUserJapanId) {
		this.detailUserJapanId = detailUserJapanId;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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


}
