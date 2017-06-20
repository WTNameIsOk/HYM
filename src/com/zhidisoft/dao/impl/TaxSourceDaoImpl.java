package com.zhidisoft.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public List<Map<String, String>> getResultList(Map<String, String[]> params) {
		//初始化SQL语句
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( SELECT tts.*,ttp.`bizAddress`,ttp.`payerName`,ttp.`payerCode`,ttp.`bizScope`,ti.`id` industry,ti.industryName,DATEDIFF(NOW(), tts.`recordDate`) overDays, tto.`organName` FROM tb_tax_source tts LEFT JOIN tb_tax_payer ttp ON tts.payerId = ttp.id LEFT JOIN tb_industry ti ON ttp.`industryId`=ti.`id` LEFT JOIN tb_tax_organ tto ON tts.`taxOrganId`=tto.`id` )a  where 1=1");
		//初始化精确查询参数
		HashMap<String, String> map = new HashMap<String, String>();
		//初始化模糊查询参数
		HashMap<String, String> fuzzyMap = new HashMap<String, String>();
		//初始化日期查询参数
		HashMap<String, String> dateMap = new HashMap<String, String>();
		
		Set<String> keySet = params.keySet();
		//获取分页查询参数
		int pageNumber = Integer.parseInt(params.get("page")[0]);
		int pageSize = Integer.parseInt(params.get("rows")[0]);
		keySet.remove("rows");
		keySet.remove("page");
		if (keySet != null && !keySet.isEmpty()){
			//循环遍历获取查询参数
			for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
				String name = (String) it.next();
				String value = params.get(name)[0];
				if (value != null) {
					if ("payerName".equals(name)){
						fuzzyMap.put(name, value);//模糊查询
					} else if ("startTime".equals(name)|"endTime".equals(name)){
						dateMap.put(name, value);//日期查询
					} else {
						map.put(name, value);//精确查询
					}
				}
			}
		}
		
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
		//拼接日期查询参数
		if (!dateMap.isEmpty()) {
			String startTime = dateMap.get("startTime");
			//进行sql拼接
			if (startTime != null && startTime.trim().length() > 0 ) {
				sql.append(" and '"+ startTime.trim() +"'< recordDate");
			}
			String endTime = dateMap.get("endTime");
			//进行sql拼接
			if (endTime != null && endTime.trim().length() > 0 ) {
				sql.append(" and '"+ endTime.trim() +"'> recordDate");
			}
		}
		//最后拼接limit子句
		sql.append(" limit ?,?");
		
		//查询
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		
		return list;
	}

}
