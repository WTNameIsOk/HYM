package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxSource;

public class TaxSourceDaoImpl extends BaseDao<TaxSource> {

	/**
	 * 查询所有基础税源信息
	 * @return - 返回所有税源信息的数据集合
	 */
	public List<TaxSource> getAll() {
		return super.getAll(TaxSource.class, "tax_source");
	}

	public TaxSource getById(Integer id) {
		return super.getById(TaxSource.class, "tax_source", id);
	}

	public boolean add(Map<String, String[]> params) {
		return super.add("tax_source", params);
	}

	public boolean update(Map<String, String[]> params) {
		return super.update("tax_source", params);
	}

	public boolean delete(Integer id) {
		return super.delete("tax_source", id);
	}

}
