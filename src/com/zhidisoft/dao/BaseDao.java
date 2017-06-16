package com.zhidisoft.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

/**
 * ��װ��ѯ���ܵĳ�����
 * 
 * @author ���쳽
 *
 * @param <T>
 *            -��Ӧ��ʵ����
 */
public abstract class BaseDao<T> {

	/**
	 * ��ѯ����
	 * @param clz - ��Ӧ��ʵ�����class
	 * @param table - Ҫ��ѯ�ı���
	 * @return - ���в�ѯ�������ݼ���
	 */
	public List<T> getAll(Class<T> clz,String table){
		String sql = "select * from tb_"+table;
		List<Map<String, String>> mapList = DBUtil.query(sql);
		List<T> list = null;
		if (mapList != null && !mapList.isEmpty()) {
			list = new ArrayList<T>();
			for (Map<String, String> map : mapList) {
				T t = null;
				try {
					t = clz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				BeanUtil.mapToBean(t, map);
				list.add(t);
			}
		}
		return list;
	}

	/**
	 * ����ID��ѯ
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Class<T> clz, String table, Integer id){
		String sql = "select * from tb_" + table + " where id = ?";
		List<Map<String, String>> mapList = DBUtil.query(sql, id);
		T t = null;
		if (mapList != null && !mapList.isEmpty()) {
			try {
				t = clz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			BeanUtil.mapToBean(t, mapList.get(0));
		}
		return t;
	}

	/**
	 * ��������
	 * 
	 * @param t
	 * @return
	 */
	public abstract boolean add(T t);

	/**
	 * ���²���
	 * 
	 * @param t
	 * @return
	 */
	public abstract boolean update(T t);

	/**
	 * ����IDɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String table, Integer id) {
		String sql = "DELETE FROM tb_" + table + " WHERE id = ?";
		return DBUtil.update(sql, id);
	}
	

	/**
	 * ��ȡ���ݵ�������
	 * 
	 * @return
	 */
	public int getCount(String table) {
		List<Map<String, String>> list = DBUtil.query("select count(1) c from tb_"+table);
		int count = 0;
		if (list != null && list.size() == 1) {
			count = Integer.parseInt(list.get(0).get("c"));
		}
		return count;
	}

	/**
	 * ͨ�÷�ҳ��ѯ
	 * @param tables ��ѯ��(�����������)
	 * @param pageNumber ��ǰҳ��
	 * @param pageSize һҳ��ʾ����
	 * @param map ��ѯ������map����
	 * @return map���ݵ�list����
	 */
	public List<Map<String, String>> getResultList(String tables, String page, String rows, Map<String, String> map) {
		int pageNumber = Integer.parseInt(page);
		int pageSize = Integer.parseInt(rows);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from "+ tables +" where 1=1");
		//ƴ�Ӳ�ѯ����
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
		//���ƴ��limit�Ӿ�
		sql.append(" limit ?,?");
		
		//��ѯ
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		
		return list;
	}
	
}
