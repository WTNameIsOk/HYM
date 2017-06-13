package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.util.DBUtil;

/**
 * 封装查询功能的接口
 * 
 * @author 贺天辰
 *
 * @param <T>
 *            -对应的实体类
 */
public abstract class BaseDao<T> {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public abstract List<T> getAll();

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public abstract T getById(Integer id);

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
	public abstract boolean delete(Integer id);
	
	
	/**
	 * 通用分页查询
	 * @param clz 对应对象的class
	 * @param tableName 表名
	 * @param pageNumber 当前页码
	 * @param pageSize 一页显示条数
	 * @param map 查询条件的map集合
	 * @return 数据的list集合
	 */
	public List<T> getResultList(Class<T> clz, String tableName, String page, String rows, Map<String, String> map) {
		int pageNumber = Integer.parseInt(page);
		int pageSize = Integer.parseInt(rows);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from "+ tableName +" where 1=1");
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
		
		//不可确定对象
		T t = null;
		//查询
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		List<T> resultList = new ArrayList<T>();
		 
		if (list != null && !list.isEmpty()) {
			for (Map<String, String > resultMap : list) {
				try {
					t = clz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					BeanUtils.populate(t, resultMap);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resultList.add(t);
			}
		}
		
		return resultList;
	}
	
}
