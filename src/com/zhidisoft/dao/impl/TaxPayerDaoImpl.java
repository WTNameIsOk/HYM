package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxPayer;

public class TaxPayerDaoImpl extends BaseDao<TaxPayer> {

	/**
	 * ��ѯ������˰����Ϣ
	 * @return - ����������˰����Ϣ�����ݼ���
	 */
	public List<TaxPayer> getAll() {
		return super.getAll(TaxPayer.class, "tax_payer");
	}

	public TaxPayer getById(Integer id) {
		return super.getById(TaxPayer.class, "tax_payer", id);
	}
	
	public boolean add(Map<String, String[]> params) {
		return super.add("tax_payer", params);
	}

	public boolean update(Map<String, String[]> params) {
		return super.update("tax_payer", params);
	}

	public boolean delete(Integer id) {
		return super.delete("tax_payer", id);
	}

}
