package com.zhidisoft.dao.impl;

import java.util.List;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Industry;

public class IndustryDaoImpl extends BaseDao<Industry> {

	/**
	 * 查询所有行业
	 * @return - 返回所有行业的数据集合
	 */
	public List<Industry> getAll() {
		return super.getAll(Industry.class, "industry");
	}

	public Industry getById(Integer id) {
		return super.getById(Industry.class, "industry", id);
	}

	@Override
	public boolean add(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Integer id) {
		return super.delete("industry", id);
	}

}
