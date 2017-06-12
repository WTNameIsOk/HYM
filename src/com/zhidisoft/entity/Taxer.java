package com.zhidisoft.entity;

/**
 * 税务人员信息表
 * @author 贺天辰
 *
 */
public class Taxer {
	
	private Integer id;				// 0 表主键 int 主键自增
	private String taxerCode;		// 1 税务人员工号 varchar(12) not null unique
	private String taxerName;		// 2 税务人员名称 Varchar(32)
	private String mobile;			// 3 税务人员电话 Varchar(12)
	private String address;			// 4 税务人员地址 Varchar(128)
	private String sex;				// 5 税务人员性别 Varchar(2)
	private String birthday;			// 6 出生日期 Date
	private String email;			// 7 电子邮件 Varchar(64)
	private Integer organId;		// 8 所属税务机关 int 关联 tb_tax_organ
	private Integer state;			// 9 有效标志 int 1: 有效 0: 无效 默认 0
	private Integer mgr;			// 10 上级领导 int tb_taxer 中税务工作人员
	private Integer admin;			// 11 系统管理员标志 Int 1 代表是 0 代表不是 默认为 0
	private String recordDate;		// 12 录入日期 Date
	private Integer recordUserId;	// 13 录入人员 int

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaxerCode() {
		return taxerCode;
	}

	public void setTaxerCode(String taxerCode) {
		this.taxerCode = taxerCode;
	}

	public String getTaxerName() {
		return taxerName;
	}

	public void setTaxerName(String taxerName) {
		this.taxerName = taxerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(Integer recordUserId) {
		this.recordUserId = recordUserId;
	}

}
