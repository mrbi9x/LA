/**
 * Copyright(C) @2016 Luvina Software Company
 * MstJapanLogicImpl.java, Jun 22, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.impl.MstJapanDaoImpl;
import net.luvina.manageuser.entities.MstJapan;
import net.luvina.manageuser.logics.MstJapanLogic;

/**
 * MstJapanLogicImpl - Xử lý logic liên quan đến thông tin của trình độ tiếng nhật
 *
 * @author Nguyễn Văn Minh
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {

	/**
	 *
	 */
	public MstJapanLogicImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstJapanLogic#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() {
		// TODO Auto-generated method stub
		List<MstJapan> listJapan = new ArrayList<MstJapan>();
		MstJapanDaoImpl mstJapanDaoImpl =  new MstJapanDaoImpl();
		listJapan = mstJapanDaoImpl.getAllMstJapan();
		return listJapan;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstJapanLogic#checkExitedJapan(java.lang.String)
	 */
	@Override
	public boolean checkExitedJapan(String codeLevel) {
		// TODO Auto-generated method stub
		MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl();
		MstJapan mstJapan = null;
		mstJapan = mstJapanDaoImpl.getJapanByCodeLevel(codeLevel);
		if (mstJapan != null) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstJapanLogic#getMstJapanByCodeLevel(java.lang.String)
	 */
	@Override
	public MstJapan getMstJapanByCodeLevel(String codeLevel) {
		// TODO Auto-generated method stub
		MstJapan mstJapan = new MstJapan();
		MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl();
		mstJapan = mstJapanDaoImpl.getJapanByCodeLevel(codeLevel);
		return mstJapan;
	}

}
