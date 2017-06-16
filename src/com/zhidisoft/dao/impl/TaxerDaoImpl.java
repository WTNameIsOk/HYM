package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Taxer;
import com.zhidisoft.util.DBUtil;

public class TaxerDaoImpl extends BaseDao<Taxer> {

	/**
	 * 查询所有税务人员
	 * @return - 返回所有税务人员的数据集合
	 */
	public List<Taxer> getAll() {
		return super.getAll(Taxer.class, "taxer");
	}

	public Taxer getById(Integer id) {
		return super.getById(Taxer.class, "taxer", id);
	}

	@Override
	public boolean add(Taxer t) {
		String sql = "INSERT INTO tb_taxer (taxerCode, taxerName, mobile, address, sex, birthday, email, organId, state, mgr, admin, recordDate, recordUserId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DATE(NOW()), ?);";
		Object[] args = {t.getTaxerCode(), t.getTaxerName(), t.getMobile(), t.getAddress(), t.getSex(), t.getBirthday(), t.getEmail(), t.getOrganId(), t.getState(), t.getMgr(), t.getAdmin(), t.getRecordUserId()};
		return DBUtil.update(sql, args);
	}

	@Override
	public boolean update(Taxer t) {
		String sql = "UPDATE tb_taxer SET mobile=?,address=?,sex=?, birthday = ? , email = ? , organId = ? , state = ? , mgr = ? , admin = ? , recordUserId = ?  WHERE id = ?";
		Object[] args = {t.getMobile(),t.getAddress(),t.getSex(), t.getBirthday() , t.getEmail() , t.getOrganId() , t.getState() , t.getMgr(), t.getAdmin(), t.getRecordUserId(), t.getId()};
		return DBUtil.update(sql, args);
	}

	public boolean delete(Integer id) {
		return super.delete("taxer", id);
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
				"SELECT tb.*,tto.organName,tu.username,tt.taxerName mgrName FROM tb_taxer tb LEFT JOIN tb_tax_organ tto ON tb.organId=tto.id LEFT JOIN tb_user tu ON tb.recordUserId=tu.id LEFT JOIN tb_taxer tt ON tb.mgr=tt.id WHERE 1=1");
		if (taxerName != null && taxerName.length() > 0) {
			// 办税专员名字用模糊查询
			sb.append(" AND tb.taxerName  LIKE '%" + taxerName + "%'");
		}

		sb.append(" limit ?,?");
		List<Map<String, String>> list = DBUtil.query(sb.toString(), (pageNumber - 1) * pageSize, pageSize);
		return list;
	};
	
	/**
	 * 存在子记录的条数
	 * @return
	 */
	public Integer isBusy(Integer id) {
		String sql = "SELECT * FROM tb_taxer tt RIGHT JOIN tb_tax_source tts ON tt.id=tts.approverId WHERE tt.id=? UNION SELECT * FROM tb_taxer tt RIGHT JOIN tb_taxer t1 ON tt.id = t1.mgr  WHERE tt.id=? ;";
		List<Map<String, String>> list = DBUtil.query(sql, id, id);
		Integer count = 0;
		if (list != null && !list.isEmpty()) {
			count = list.size();
		}
		return count;
	}

}
