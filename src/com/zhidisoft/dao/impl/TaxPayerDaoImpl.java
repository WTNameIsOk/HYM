package com.zhidisoft.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxPayer;
import com.zhidisoft.util.DBUtil;

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
	
	public Map<String, String> getPayerByCode(String payerCode) {
		String sql = "SELECT * FROM tb_tax_payer ttp LEFT JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id LEFT JOIN tb_industry ti ON ttp.industryId=ti.id LEFT JOIN tb_user tu ON tu.id=ttp.userId WHERE ttp.payerCode = ?";
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> list = DBUtil.query(sql, payerCode);
		if (list != null && !list.isEmpty()) {
			map = list.get(0);
		}
		return map;
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

	/**
	 * 存在子记录的条数
	 * @return
	 */
	public Integer isBusy(Integer id) {
		String sql = "SELECT * FROM tb_tax_payer ttp LEFT JOIN tb_tax_source tts ON ttp.id=tts.payerId WHERE ttp.id=?";
		List<Map<String, String>> list = DBUtil.query(sql, id);
		Integer count = 0;
		if (list != null && !list.isEmpty()) {
			count = list.size();
		}
		return count;
	}

}
