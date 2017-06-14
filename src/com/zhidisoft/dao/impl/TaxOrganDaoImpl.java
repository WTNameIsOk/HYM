package com.zhidisoft.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxOrgan;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class TaxOrganDaoImpl extends BaseDao<TaxOrgan> {

	@Override
	public List<TaxOrgan> getAll() {
		String sql = "select * from tb_tax_organ";
		List<Map<String, String>> mapList = DBUtil.query(sql);
		List<TaxOrgan> list = null;
		if (mapList != null && !mapList.isEmpty()) {
			list = new ArrayList<TaxOrgan>();
			for (Map<String, String> map : mapList) {
				TaxOrgan taxOrgan = new TaxOrgan();
				BeanUtil.mapToBean(taxOrgan, map);
				list.add(taxOrgan);
			}
		}
		return list;
	}

	@Override
	public TaxOrgan getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
