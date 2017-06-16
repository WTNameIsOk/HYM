package com.zhidisoft.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
			BeanUtil.mapToBean(t, mapList.get(0));
		}
		return t;
	}

	/**
	 * 新增数据
	 * 
	 * @param t
	 * @return
	 */
	public abstract boolean add(T t);

	/**
	 * 更新操作
	 * 
	 * @param t
	 * @return
	 */
	public abstract boolean update(T t);

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
	 * @param map 查询条件的map集合
	 * @return map数据的list集合
	 */
	public List<Map<String, String>> getResultList(String tables, String page, String rows, Map<String, String> map) {
		int pageNumber = Integer.parseInt(page);
		int pageSize = Integer.parseInt(rows);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from "+ tables +" where 1=1");
		//拼接查询参数
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
		//最后拼接limit子句
		sql.append(" limit ?,?");
		
		//查询
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		
		return list;
	}
	
}
