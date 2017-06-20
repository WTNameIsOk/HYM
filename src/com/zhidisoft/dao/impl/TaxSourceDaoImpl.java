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

	public List<Map<String, String>> getResultList(Map<String, String[]> params) {
		//��ʼ��SQL���
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( SELECT tts.*,ttp.`bizAddress`,ttp.`payerName`,ttp.`payerCode`,ttp.`bizScope`,ti.`id` industry,ti.industryName,DATEDIFF(NOW(), tts.`recordDate`) overDays, tto.`organName` FROM tb_tax_source tts LEFT JOIN tb_tax_payer ttp ON tts.payerId = ttp.id LEFT JOIN tb_industry ti ON ttp.`industryId`=ti.`id` LEFT JOIN tb_tax_organ tto ON tts.`taxOrganId`=tto.`id` )a  where 1=1");
		//��ʼ����ȷ��ѯ����
		HashMap<String, String> map = new HashMap<String, String>();
		//��ʼ��ģ����ѯ����
		HashMap<String, String> fuzzyMap = new HashMap<String, String>();
		//��ʼ�����ڲ�ѯ����
		HashMap<String, String> dateMap = new HashMap<String, String>();
		
		Set<String> keySet = params.keySet();
		//��ȡ��ҳ��ѯ����
		int pageNumber = Integer.parseInt(params.get("page")[0]);
		int pageSize = Integer.parseInt(params.get("rows")[0]);
		keySet.remove("rows");
		keySet.remove("page");
		if (keySet != null && !keySet.isEmpty()){
			//ѭ��������ȡ��ѯ����
			for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
				String name = (String) it.next();
				String value = params.get(name)[0];
				if (value != null) {
					if ("payerName".equals(name)){
						fuzzyMap.put(name, value);//ģ����ѯ
					} else if ("startTime".equals(name)|"endTime".equals(name)){
						dateMap.put(name, value);//���ڲ�ѯ
					} else {
						map.put(name, value);//��ȷ��ѯ
					}
				}
			}
		}
		
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
		//ƴ�����ڲ�ѯ����
		if (!dateMap.isEmpty()) {
			String startTime = dateMap.get("startTime");
			//����sqlƴ��
			if (startTime != null && startTime.trim().length() > 0 ) {
				sql.append(" and '"+ startTime.trim() +"'< recordDate");
			}
			String endTime = dateMap.get("endTime");
			//����sqlƴ��
			if (endTime != null && endTime.trim().length() > 0 ) {
				sql.append(" and '"+ endTime.trim() +"'> recordDate");
			}
		}
		//���ƴ��limit�Ӿ�
		sql.append(" limit ?,?");
		
		//��ѯ
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		
		return list;
	}

}
