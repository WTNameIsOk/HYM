package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxOrgan;

public class TaxOrganDaoImpl extends BaseDao<TaxOrgan> {

	/**
	 * ��ѯ����˰��λ
	 * @return - ��������˰��λ�����ݼ���
	 */
	public List<TaxOrgan> getAll() {
		return super.getAll(TaxOrgan.class, "tax_organ");
	}

	public TaxOrgan getById(Integer id) {
		return super.getById(TaxOrgan.class, "tax_organ", id);
	}

	public boolean add(Map<String, String[]> params) {
		return super.add("tax_organ", params);
	}

	public boolean update(Map<String, String[]> params) {
		return super.update("tax_organ", params);
	}

	public boolean delete(Integer id) {
		return super.delete("tax_organ", id);
	}

}
