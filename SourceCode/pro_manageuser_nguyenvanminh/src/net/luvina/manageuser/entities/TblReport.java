/**
 * Copyright(C) @2016 Luvina Software Company
 * TblReport.java, Jul 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Đối tượng TblReport
 * @author Nguyễn Văn Minh
 *
 */
public class TblReport implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer reportId;
	private Integer totalUser;
	private Integer totalUserN;
	private Integer totalUserN0;
	private Integer totalUserN1;
	private Integer totalUserN2;
	private Integer totalUserN3;
	private Integer totalUserN4;
	private Integer totalUserN5;
	private Date dateReport;

	/**
	 *
	 */
	public TblReport() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param totalUser
	 * @param totalUserN
	 * @param totalUserN1
	 * @param totalUserN2
	 * @param totalUserN3
	 * @param totalUserN4
	 * @param totalUserN5
	 * @param dateReport
	 */
	public TblReport(Integer totalUser, Integer totalUserN, Integer totalUserN0,
			Integer totalUserN1, Integer totalUserN2, Integer totalUserN3,
			Integer totalUserN4, Integer totalUserN5, Date dateReport) {
		this.totalUser = totalUser;
		this.totalUserN = totalUserN;
		this.totalUserN0 = totalUserN0;
		this.totalUserN1 = totalUserN1;
		this.totalUserN2 = totalUserN2;
		this.totalUserN3 = totalUserN3;
		this.totalUserN4 = totalUserN4;
		this.totalUserN5 = totalUserN5;
		this.dateReport = dateReport;
	}



	/**
	 * @return the reportId
	 */
	public Integer getReportId() {
		return reportId;
	}
	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	/**
	 * @return the totalUser
	 */
	public Integer getTotalUser() {
		return totalUser;
	}
	/**
	 * @param totalUser the totalUser to set
	 */
	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}
	/**
	 * @return the totalUserN
	 */
	public Integer getTotalUserN() {
		return totalUserN;
	}
	/**
	 * @param totalUserN the totalUserN to set
	 */
	public void setTotalUserN(Integer totalUserN) {
		this.totalUserN = totalUserN;
	}
	/**
	 * @param totalUserN0 the totalUserN0 to set
	 */
	public void setTotalUserN0(Integer totalUserN0) {
		this.totalUserN0 = totalUserN0;
	}

	/**
	 * @return the totalUserN0
	 */
	public Integer getTotalUserN0() {
		return totalUserN0;
	}

	/**
	 * @return the totalUserN1
	 */
	public Integer getTotalUserN1() {
		return totalUserN1;
	}
	/**
	 * @param totalUserN1 the totalUserN1 to set
	 */
	public void setTotalUserN1(Integer totalUserN1) {
		this.totalUserN1 = totalUserN1;
	}
	/**
	 * @return the totalUserN2
	 */
	public Integer getTotalUserN2() {
		return totalUserN2;
	}
	/**
	 * @param totalUserN2 the totalUserN2 to set
	 */
	public void setTotalUserN2(Integer totalUserN2) {
		this.totalUserN2 = totalUserN2;
	}
	/**
	 * @return the totalUserN3
	 */
	public Integer getTotalUserN3() {
		return totalUserN3;
	}
	/**
	 * @param totalUserN3 the totalUserN3 to set
	 */
	public void setTotalUserN3(Integer totalUserN3) {
		this.totalUserN3 = totalUserN3;
	}
	/**
	 * @return the totalUserN4
	 */
	public Integer getTotalUserN4() {
		return totalUserN4;
	}
	/**
	 * @param totalUserN4 the totalUserN4 to set
	 */
	public void setTotalUserN4(Integer totalUserN4) {
		this.totalUserN4 = totalUserN4;
	}
	/**
	 * @return the totalUserN5
	 */
	public Integer getTotalUserN5() {
		return totalUserN5;
	}
	/**
	 * @param totalUserN5 the totalUserN5 to set
	 */
	public void setTotalUserN5(Integer totalUserN5) {
		this.totalUserN5 = totalUserN5;
	}

	/**
	 * @param dateReport the dateReport to set
	 */
	public void setDateReport(Date dateReport) {
		this.dateReport = dateReport;
	}

	/**
	 * @return the dateReport
	 */
	public Date getDateReport() {
		return dateReport;
	}



}
