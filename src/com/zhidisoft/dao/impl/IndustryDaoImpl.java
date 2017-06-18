package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Industry;

public class IndustryDaoImpl extends BaseDao<Industry> {

	/**
	 * ��ѯ������ҵ
	 * @return - ����������ҵ�����ݼ���
	 */
	public List<Industry> getAll() {
		return super.getAll(Industry.class, "industry");
	}

	public Industry getById(Integer id) {
		return super.getById(Industry.class, "industry", id);
	}

	public boolean add(Map<String, String[]> params) {
		return super.add("industry", params);
	}

	public boolean update(Map<String, String[]> params) {
		return super.update("industry", params);
	}

	public boolean delete(Integer id) {
		return super.delete("industry", id);
	}

}
