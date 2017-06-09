package com.zhidisoft.entity;

/**
 * 用户表
 * @author 贺天辰
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
	 * salt varchar(32) not null 用户盐值 md5 加密时的盐值 permissionId Int Not null 默认 1
	 * 用户权限 1. 超级管理员。 2. 普通管理员。 3. 普通员工 state int not null 默认 1 用户状态 1 表示正常使用 0
	 * 表示禁用 -1 表示离职 -2 表示退休 -3 表示休假 email varchar(50) not null unique
	 */

}
