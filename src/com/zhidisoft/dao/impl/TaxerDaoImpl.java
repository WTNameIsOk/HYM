package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Taxer;
import com.zhidisoft.util.DBUtil;

public class TaxerDaoImpl extends BaseDao<Taxer> {

	@Override
	public List<Taxer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taxer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 获取数据的总条数
	 * 
	 * @return
	 */
	public int getCount() {
		List<Map<String, String>> list = DBUtil.query("select count(1) c from tb_taxer");
		int count = 0;
		if (list != null && list.size() == 1) {
			count = Integer.parseInt(list.get(0).get("c"));
		}
		return count;
	}

	/**
	 * 分页sql
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param taxerName
	 * @return
	 */
	public List<Map<String, String>> getListByMap(String page, String rows, String taxerName) {
		int pageNumber = Integer.parseInt(page);
		int pageSize = Integer.parseInt(rows);
		
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT tb.*,tto.organName FROM tb_taxer tb LEFT JOIN tb_tax_organ tto ON tb.organId=tto.id where 1=1");
		if (taxerName != null && taxerName.length() > 0) {
			// 办税专员名字用模糊查询
			sb.append(" AND tb.taxerName  LIKE '%" + taxerName + "%'");
		}

		sb.append(" limit ?,?");
		List<Map<String, String>> list = DBUtil.query(sb.toString(), (pageNumber - 1) * pageSize, pageSize);
		return list;
	};
}
