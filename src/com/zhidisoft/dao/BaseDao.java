package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

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
			try {
				BeanUtils.populate(t, mapList.get(0));
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * ��������
	 * @param table - �����ı���
	 * @param params - �޸Ĳ�����map����
	 * @return
	 */
	public boolean add(String table, Map<String, String[]> params){
		StringBuilder sql = new StringBuilder("INSERT INTO tb_"+table+" (");
		StringBuilder field = new StringBuilder();
		StringBuilder valLength = new StringBuilder();
		List<Object> values = new ArrayList<Object>();
		if (!params.isEmpty()) {
			for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String val = params.get(key)[0];
				if (val != null && val.trim().length() > 0 ) {
					field.append(key + ",");
					valLength.append("?,");
					values.add(val);
				}
			}
		}
		sql.append(field.substring(0, field.length()-1) + ") values (" + valLength.substring(0, valLength.length()-1) + ");");
		boolean state = DBUtil.update(sql.toString(), values.toArray());
		return state;
	}

	/**
	 * ���²���
	 * @param table - �����ı���
	 * @param params - �޸Ĳ�����map����
	 * @return
	 */
	public boolean update(String table, Map<String, String[]> params){
		StringBuilder sql = new StringBuilder("UPDATE tb_"+table+" set ");
		StringBuilder field = new StringBuilder();//�ֶ����õĿɱ��ַ���
		String fields = null;//�ֶ����õ��ַ���
		List<Object> values = new ArrayList<Object>();//��������
		
		String id = params.get("id")[0];//��ȡid����
		if (!params.isEmpty()) {
			for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (!"id".equals(key)) {//����id���ַ���ƴ��
					String val = params.get(key)[0];
					if (val != null && val.trim().length() > 0 ) {
						field.append(key + "=?,");//ƴ���ֶ�����
						values.add(val);//ƴ�Ӳ���
					}
				}
			}
			fields = field.substring(0, field.length()-1);
		}
		values.add(id);//���ƴ��id����
		sql.append(fields + " where id = ?");//ƴ��SQL�������
		boolean state = DBUtil.update(sql.toString(), values.toArray());
		return state;
	}

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
	 * @param map ��ȷ��ѯ��map����
	 * @param map ģ����ѯ��map����
	 * @return map���ݵ�list����
	 */
	public List<Map<String, String>> getResultList(String tables, String page, String rows, Map<String, String> map, Map<String, String> fuzzyMap) {
		int pageNumber = Integer.parseInt(page);
		int pageSize = Integer.parseInt(rows);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from "+ tables +" where 1=1");
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
