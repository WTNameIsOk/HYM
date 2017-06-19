package com.zhidisoft.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxSource;
import com.zhidisoft.util.DBUtil;

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

	public List<Map<String, String>> getResultList(String tables, Map<String, String[]> params) {
		//定义精确查询参数
		HashMap<String, String> map = new HashMap<String, String>();
		//定义模糊查询参数
		HashMap<String, String> fuzzyMap = new HashMap<String, String>();
		
		String[] payerName = params.get("payerName");
		if (payerName!=null) {
			fuzzyMap.put("payerName", payerName[0]);
		}
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String name = (String) it.next();
			if ((!"page".equals(name))&&(!"rows".equals(name))&&(!"payerName".equals(name))) {
				map.put(name, params.get(name)[0]);
			}
		}
		
		//获取分页查询参数
		int pageNumber = Integer.parseInt(params.get("page")[0]);
		int pageSize = Integer.parseInt(params.get("rows")[0]);
		
		//定义SQL语句
		StringBuilder sql = new StringBuilder();
		sql.append("select tts.*,ttp.`bizAddress`,ttp.`payerName`,ttp.`payerCode`,ttp.`bizScope`,ti.industryName,DATEDIFF(NOW(),tts.`recordDate`) overDays from "+ tables +" where 1=1");
		//拼接精确查询参数
		if (!map.isEmpty()) {
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				//获取map集合的键
				String key = it.next();
				//获取值
				String value = map.get(key);
				
				//进行sql拼接
				if (value != null && value.trim().length() > 0 ) {
					sql.append(" and "+ key +"='"+ value.trim() +"'");
				}
			}
		}
		//拼接模糊查询参数
		if (!fuzzyMap.isEmpty()) {
			for (Iterator<String> it = fuzzyMap.keySet().iterator(); it.hasNext();) {
				//获取map集合的键
				String key = it.next();
				//获取值
				String value = fuzzyMap.get(key);
				
				//进行sql拼接
				if (value != null && value.trim().length() > 0 ) {
					sql.append(" and "+ key +" like '%"+ value.trim() +"%'");
				}
			}
		}
		//最后拼接limit子句
		sql.append(" limit ?,?");
		
		//查询
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		
		return list;
	}

}
