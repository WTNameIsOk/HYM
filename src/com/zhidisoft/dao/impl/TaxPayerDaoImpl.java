package com.zhidisoft.dao.impl;

import java.util.List;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxPayer;

public class TaxPayerDaoImpl extends BaseDao<TaxPayer> {

	/**
	 * 查询所有纳税人信息
	 * @return - 返回所有纳税人信息的数据集合
	 */
	public List<TaxPayer> getAll() {
		return super.getAll(TaxPayer.class, "tax_payer");
	}

	public TaxPayer getById(Integer id) {
		return super.getById(TaxPayer.class, "tax_payer", id);
	}

	@Override
	public boolean add(TaxPayer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TaxPayer t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Integer id) {
		return super.delete("tax_payer", id);
	}

}
