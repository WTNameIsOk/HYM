package com.zhidisoft.base;

import java.util.List;

/**
 * ��װ��ѯ���ܵĽӿ�
 * 
 * @author ���쳽
 *
 * @param <T>
 *            -��Ӧ��ʵ����
 */
public interface BaseDao<T> {

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * ����ID��ѯ
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Integer id);

	/**
	 * ��������
	 * 
	 * @param t
	 * @return
	 */
	public boolean add(T t);

	/**
	 * ���²���
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t);

	/**
	 * ����IDɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	
}
