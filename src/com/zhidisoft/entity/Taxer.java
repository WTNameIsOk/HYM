package com.zhidisoft.entity;

/**
 * ˰����Ա��Ϣ��
 * @author ���쳽
 *
 */
public class Taxer {
	
	private Integer id;				// 0 ������ int ��������
	private String taxerCode;		// 1 ˰����Ա���� varchar(12) not null unique
	private String taxerName;		// 2 ˰����Ա���� Varchar(32)
	private String mobile;			// 3 ˰����Ա�绰 Varchar(12)
	private String address;			// 4 ˰����Ա��ַ Varchar(128)
	private String sex;				// 5 ˰����Ա�Ա� Varchar(2)
	private String birthday;			// 6 �������� Date
	private String email;			// 7 �����ʼ� Varchar(64)
	private Integer organId;		// 8 ����˰����� int ���� tb_tax_organ
	private Integer state;			// 9 ��Ч��־ int 1: ��Ч 0: ��Ч Ĭ�� 0
	private Integer mgr;			// 10 �ϼ��쵼 int tb_taxer ��˰������Ա
	private Integer admin;			// 11 ϵͳ����Ա��־ Int 1 ������ 0 ������ Ĭ��Ϊ 0
	private String recordDate;		// 12 ¼������ Date
	private Integer recordUserId;	// 13 ¼����Ա int

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
