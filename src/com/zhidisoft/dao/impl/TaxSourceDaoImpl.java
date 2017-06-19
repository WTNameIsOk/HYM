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
	 * ��ѯ���л���˰Դ��Ϣ
	 * @return - ��������˰Դ��Ϣ�����ݼ���
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
		//���徫ȷ��ѯ����
		HashMap<String, String> map = new HashMap<String, String>();
		//����ģ����ѯ����
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
		
		//��ȡ��ҳ��ѯ����
		int pageNumber = Integer.parseInt(params.get("page")[0]);
		int pageSize = Integer.parseInt(params.get("rows")[0]);
		
		//����SQL���
		StringBuilder sql = new StringBuilder();
		sql.append("select tts.*,ttp.`bizAddress`,ttp.`payerName`,ttp.`payerCode`,ttp.`bizScope`,ti.industryName,DATEDIFF(NOW(),tts.`recordDate`) overDays from "+ tables +" where 1=1");
		//ƴ�Ӿ�ȷ��ѯ����
		if (!map.isEmpty()) {
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				//��ȡmap���ϵļ�
				String key = it.next();
				//��ȡֵ
				String value = map.get(key);
				
				//����sqlƴ��
				if (value != null && value.trim().length() > 0 ) {
					sql.append(" and "+ key +"='"+ value.trim() +"'");
				}
			}
		}
		//ƴ��ģ����ѯ����
		if (!fuzzyMap.isEmpty()) {
			for (Iterator<String> it = fuzzyMap.keySet().iterator(); it.hasNext();) {
				//��ȡmap���ϵļ�
				String key = it.next();
				//��ȡֵ
				String value = fuzzyMap.get(key);
				
				//����sqlƴ��
				if (value != null && value.trim().length() > 0 ) {
					sql.append(" and "+ key +" like '%"+ value.trim() +"%'");
				}
			}
		}
		//���ƴ��limit�Ӿ�
		sql.append(" limit ?,?");
		
		//��ѯ
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		
		return list;
	}

}
