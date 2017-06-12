package com.zhidisoft.entity;

/**
 * 税务机关表
 * @author 贺天辰
 *
 */
public class TaxOrgan {
	
	private Integer id; 			// 1 税务机关代码 int 主键
	private String organName; 		// 2 税务机关名称 varchar(64)
	private Integer parentId; 		// 3 上级税务机关代码 int
	private String address; 		// 4 税务机关地址 Varchar(128)
	private String phone; 			// 5 税务机关联系电话 Varchar(12)
	private String faxPhone; 		// 6 传真电话 Varchar(12)
	private String email;		 	// 7 电子信箱 Varchar(32)
	private Integer leaderId; 		// 8 负责人 int 关联 tb_taxer
	private String taxTypeCode; 	// 9 国地税类型代码 Varchar(12) 国税 1000210 地税 1000215
	private Integer state; 			// 10 有效标志 int 1: 有效 0: 无效 默认 0
	private String recordDate; 		// 11 录入日期 Date
	private Integer recordUserId; 	// 12 录入人员 int

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public String getTaxTypeCode() {
		return taxTypeCode;
	}

	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
