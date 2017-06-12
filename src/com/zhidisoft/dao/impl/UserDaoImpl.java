package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class UserDaoImpl implements BaseDao<User> {

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
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User t) {
		String sql = "UPDATE tb_user SET PASSWORD = '' WHERE id = 1";
		
		
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
