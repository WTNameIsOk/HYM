package com.zhidisoft.dao.impl;

import java.util.List;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxOrgan;

public class TaxOrganDaoImpl extends BaseDao<TaxOrgan> {

	/**
	 * 查询所有税务单位
	 * @return - 返回所有税务单位的数据集合
	 */
	public List<TaxOrgan> getAll() {
		return super.getAll(TaxOrgan.class, "tax_organ");
	}

	public TaxOrgan getById(Integer id) {
		return super.getById(TaxOrgan.class, "tax_organ", id);
	}

	@Override
	public boolean add(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Integer id) {
		return super.delete("tax_organ", id);
	}

}
