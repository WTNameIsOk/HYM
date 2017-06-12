package com.zhidisoft.entity;

/**
 * ˰����ر�
 * @author ���쳽
 *
 */
public class TaxOrgan {
	
	private Integer id; 			// 1 ˰����ش��� int ����
	private String organName; 		// 2 ˰��������� varchar(64)
	private Integer parentId; 		// 3 �ϼ�˰����ش��� int
	private String address; 		// 4 ˰����ص�ַ Varchar(128)
	private String phone; 			// 5 ˰�������ϵ�绰 Varchar(12)
	private String faxPhone; 		// 6 ����绰 Varchar(12)
	private String email;		 	// 7 �������� Varchar(32)
	private Integer leaderId; 		// 8 ������ int ���� tb_taxer
	private String taxTypeCode; 	// 9 ����˰���ʹ��� Varchar(12) ��˰ 1000210 ��˰ 1000215
	private Integer state; 			// 10 ��Ч��־ int 1: ��Ч 0: ��Ч Ĭ�� 0
	private String recordDate; 		// 11 ¼������ Date
	private Integer recordUserId; 	// 12 ¼����Ա int

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
