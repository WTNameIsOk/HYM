package com.zhidisoft.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;

public class UserDaoImpl extends BaseDao<User> {

	/**
	 * ��ѯ��¼��֤��Ϣ
	 * @param username
	 * @param password
	 * @return - ���û���������ƥ��ɹ����򷵻ش��û�����Ϣ��װ����User
	 * 	��֮��Ϊnull
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
	 * �޸�����
	 * @param user
	 * @return
	 */
	public boolean modifyPwd(User user) {
		String sql = "UPDATE tb_user SET PASSWORD = ? WHERE id = ?";
		Object[] args = {user.getPassword(),user.getId()};
		return DBUtil.update(sql, args);
	}
	
	/**
	 * �����û�id��ѯ�û���Ϣ
	 * @param id
	 * @return
	 */
	public Map<String, String> getMsgById(Integer id){
		String sql = "SELECT tu.taxerId,tu.permissionId,tu.`state` status,tt.*,tto.organName,tb.taxerName mgrName FROM tb_user tu LEFT JOIN tb_taxer tt ON tu.taxerId = tt.id LEFT JOIN tb_tax_organ tto ON tt.organId=tto.id LEFT JOIN tb_taxer tb ON tt.mgr=tb.id WHERE tu.id = ? ";
		return DBUtil.query(sql, id).get(0);
	}

	/**
	 * ��ѯ�����û���Ϣ
	 * @return - ���������û���Ϣ�����ݼ���
	 */	
	public List<User> getAll() {
		return super.getAll(User.class, "user");
	}

	public User getById(Integer id) {
		return super.getById(User.class, "user", id);
	}

	public boolean add(Map<String, String[]> params) {
		return super.add("user", params);
	}

	public boolean update(Map<String, String[]> params) {
		return super.update("user", params);
	}

	public boolean delete(Integer id) {
		return super.delete("user", id);
	}

}
