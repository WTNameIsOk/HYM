package com.zhidisoft.entity;

/**
 * �û���
 * @author ���쳽
 *
 */
public class User {

	private Integer id;
	private String username;
	private String password;
	private Integer taxerId;
	private String salt;
	private Integer permissionId;
	private Integer state;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTaxerId() {
		return taxerId;
	}

	public void setTaxerId(Integer taxerId) {
		this.taxerId = taxerId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * salt varchar(32) not null �û���ֵ md5 ����ʱ����ֵ permissionId Int Not null Ĭ�� 1
	 * �û�Ȩ�� 1. ��������Ա�� 2. ��ͨ����Ա�� 3. ��ͨԱ�� state int not null Ĭ�� 1 �û�״̬ 1 ��ʾ����ʹ�� 0
	 * ��ʾ���� -1 ��ʾ��ְ -2 ��ʾ���� -3 ��ʾ�ݼ� email varchar(50) not null unique
	 */

}
