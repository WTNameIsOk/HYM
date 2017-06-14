package com.zhidisoft.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Taxer;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class TaxerDaoImpl extends BaseDao<Taxer> {

	@Override
	public List<Taxer> getAll() {
		String sql = "select * from tb_taxer";
		List<Map<String, String>> mapList = DBUtil.query(sql);
		List<Taxer> list = null;
		if (mapList != null && !mapList.isEmpty()) {
			list = new ArrayList<Taxer>();
			for (Map<String, String> map : mapList) {
				Taxer taxer = new Taxer();
				BeanUtil.mapToBean(taxer, map);
				list.add(taxer);
			}
		}
		
		return list;
	}

	@Override
	public Taxer getById(Integer id) {
		String sql = "select * from tb_taxer where id = ?";
		List<Map<String, String>> mapList = DBUtil.query(sql, id);
		Taxer taxer = null;
		if (mapList != null && !mapList.isEmpty()) {
			taxer = new Taxer();
			BeanUtil.mapToBean(taxer, mapList.get(0));
		}
		return taxer;
	}

	@Override
	public boolean add(Taxer t) {
		String sql = "INSERT INTO tb_taxer (taxerCode, taxerName, mobile, address, sex, birthday, email, organId, state, mgr, admin, recordDate, recordUserId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DATE(NOW()), ?);";
		Object[] args = {t.getTaxerCode(), t.getTaxerName(), t.getMobile(), t.getAddress(), t.getSex(), t.getBirthday(), t.getEmail(), t.getOrganId(), t.getState(), t.getMgr(), t.getAdmin(), t.getRecordUserId()};
		return DBUtil.update(sql, args);
	}

	@Override
	public boolean update(Taxer t) {
		String sql = "UPDATE tb_taxer SET mobile=?,address=?,sex=?, birthday = ? , email = ? , organId = ? , state = ? , mgr = ? , admin = ? WHERE id = ?";
		Object[] args = {t.getMobile(),t.getAddress(),t.getSex(), t.getBirthday() , t.getEmail() , t.getOrganId() , t.getState() , t.getMgr(), t.getAdmin(), t.getId()};
		return DBUtil.update(sql, args);
	}

	@Override
	public boolean delete(Integer id) {
		String sql = "DELETE FROM tb_taxer WHERE id = ?";
		return DBUtil.update(sql, id);
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
