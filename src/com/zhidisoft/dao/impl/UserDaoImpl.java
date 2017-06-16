package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class UserDaoImpl extends BaseDao<User> {

	/**
	 * 查询登录验证信息
	 * @param username
	 * @param password
	 * @return - 若用户名、密码匹配成功，则返回此用户的信息封装对象User
	 * 	反之则为null
	 */
	public User login(String username,String password) {
		User user = null;
		String sql = "select * from tb_user where username=? and password=?";
		Object[] args = {username,password};
		
		List<Map<String, String>> mapList = DBUtil.query(sql, args);
		if (!mapList.isEmpty()) {
			user = new User();
			BeanUtil.mapToBean(user, mapList.get(0));
		}
		return user;
	}

	/**
	 * 查询所有用户信息
	 * @return - 返回所有用户信息的数据集合
	 */	
	public List<User> getAll() {
		return super.getAll(User.class, "user");
	}

	public User getById(Integer id) {
		return super.getById(User.class, "user", id);
	}

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User user) {
		String sql = "UPDATE tb_user SET PASSWORD = ? WHERE id = ?";
		Object[] args = {user.getPassword(),user.getId()};
		return DBUtil.update(sql, args);
	}

	public boolean delete(Integer id) {
		return super.delete("user", id);
	}

}
