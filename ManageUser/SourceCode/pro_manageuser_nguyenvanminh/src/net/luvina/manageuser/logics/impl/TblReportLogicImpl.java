/**
 * Copyright(C) 2016 Luvina Software Company
 * TblReportLogicImpl.java, Jul 15, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import net.luvina.manageuser.dao.impl.TblReportDaoImpl;
import net.luvina.manageuser.entities.TblReport;
import net.luvina.manageuser.logics.TblReportLogic;

/**
 * TblReportLogicImpl.java
 * @author Nguyễn Văn Minh
 *
 */
public class TblReportLogicImpl implements TblReportLogic {

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.TblReportLogic#getTblReport()
	 */
	@Override
	public TblReport getTblReport() {
		// TODO Auto-generated method stub
		TblReportDaoImpl reportDaoImpl = new TblReportDaoImpl();
		return reportDaoImpl.getTblReport();
	}

}
