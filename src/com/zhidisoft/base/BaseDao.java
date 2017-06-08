package com.zhidisoft.base;

import java.util.List;

/**
 * 封装查询功能的接口
 * 
 * @author 贺天辰
 *
 * @param <T>
 *            -对应的实体类
 */
public interface BaseDao<T> {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Integer id);

	/**
	 * 新增数据
	 * 
	 * @param t
	 * @return
	 */
	public boolean add(T t);

	/**
	 * 更新操作
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	
}
