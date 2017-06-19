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
 * 封装查询功能的抽象类
 * 
 * @author 贺天辰
 *
 * @param <T>
 *            -对应的实体类
 */
public abstract class BaseDao<T> {

	/**
	 * 查询所有
	 * @param clz - 对应的实体类的class
	 * @param table - 要查询的表名
	 * @return - 所有查询出的数据集合
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
	 * 根据ID查询
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
	 * 新增数据
	 * @param table - 操作的表名
	 * @param params - 修改参数的map集合
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
	 * 更新操作
	 * @param table - 操作的表名
	 * @param params - 修改参数的map集合
	 * @return
	 */
	public boolean update(String table, Map<String, String[]> params){
		StringBuilder sql = new StringBuilder("UPDATE tb_"+table+" set ");
		StringBuilder field = new StringBuilder();//字段设置的可变字符串
		String fields = null;//字段设置的字符串
		List<Object> values = new ArrayList<Object>();//参数集合
		
		String id = params.get("id")[0];//获取id参数
		if (!params.isEmpty()) {
			for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (!"id".equals(key)) {//跳过id的字符串拼接
					String val = params.get(key)[0];
					if (val != null && val.trim().length() > 0 ) {
						field.append(key + "=?,");//拼接字段设置
						values.add(val);//拼接参数
					}
				}
			}
			fields = field.substring(0, field.length()-1);
		}
		values.add(id);//最后拼接id参数
		sql.append(fields + " where id = ?");//拼接SQL语句条件
		boolean state = DBUtil.update(sql.toString(), values.toArray());
		return state;
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String table, Integer id) {
		String sql = "DELETE FROM tb_" + table + " WHERE id = ?";
		return DBUtil.update(sql, id);
	}
	

	/**
	 * 获取数据的总条数
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
	 * 通用分页查询
	 * @param tables 查询表(含表连接语句)
	 * @param pageNumber 当前页码
	 * @param pageSize 一页显示条数
	 * @param map 精确查询的map集合
	 * @param map 模糊查询的map集合
	 * @return map数据的list集合
	 */
	public List<Map<String, String>> getResultList(String tables, String page, String rows, Map<String, String> map, Map<String, String> fuzzyMap) {
		int pageNumber = Integer.parseInt(page);
		int pageSize = Integer.parseInt(rows);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from "+ tables +" where 1=1");
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
