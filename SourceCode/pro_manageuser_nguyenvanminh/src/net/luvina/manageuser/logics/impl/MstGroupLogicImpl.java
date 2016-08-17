/**
 * Copyright(C) @2016 Luvina Software Company
 * MstGroupLogicImpl.java, Jun 14, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.logics.impl;

import java.util.List;

import net.luvina.manageuser.dao.impl.MstGroupDaoImpl;
import net.luvina.manageuser.entities.MstGroup;
import net.luvina.manageuser.logics.MstGroupLogic;

/**
 * MstGroupLogicImpl - Xử lý logic thao tác dữ liệu db với bảng MstGroup
 *
 * @author Nguyễn Văn Minh
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	/**
	 *
	 */
	public MstGroupLogicImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstGroupLogic#getAllGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() {
		// TODO Auto-generated method stub
		MstGroupDaoImpl mstGroupDaoImpl = new MstGroupDaoImpl();
		List<MstGroup> listMstGroup = mstGroupDaoImpl.getAllMstGroup();
		return listMstGroup;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstGroupLogic#checkExitedGroup(int)
	 */
	@Override
	public boolean checkExitedGroup(int groupId) {
		// TODO Auto-generated method stub
		MstGroup mstGroup = null;
		boolean isExitedGroup = false;
		MstGroupDaoImpl mstGroupDaoImpl = new MstGroupDaoImpl();
		mstGroup = mstGroupDaoImpl.getGroupByGroupId(groupId);
		if (mstGroup != null) {
			isExitedGroup = true;
		} else {
			isExitedGroup = false;
		}
		return isExitedGroup;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstGroupLogic#getMstGroupByGroupId(int)
	 */
	@Override
	public MstGroup getMstGroupByGroupId(int groupId) {
		// TODO Auto-generated method stub
		MstGroup mstGroup = new MstGroup();
		MstGroupDaoImpl mstGroupDaoImpl = new MstGroupDaoImpl();
		mstGroup = mstGroupDaoImpl.getGroupByGroupId(groupId);
		return mstGroup;
	}

}
