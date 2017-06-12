package com.zhidisoft.entity;

/**
 * ��˰����Ϣ��
 * @author ���쳽
 *
 */
public class TaxPayer {

	private Integer id;					// 0 int ����
	private String payerCode;			// 1 ��˰��ʶ��� varchar(32) not null unique
	private String payerName;			// 2 ��˰������ varchar (64) not null
	private String bizAddress; 			// 3 ������Ӫ��ַ varchar(128) not null
	private Integer taxOrganId; 		// 4 ����˰����� int not null ���� tb_ ta x_organ
	private Integer industryId; 		// 5 ��ҵ int
	private String bizScope; 			// 6 ��Ӫ��Χ Varchar(128)
	private String invoiceType;  		/* 7 Ʊ�ֺ˶� varchar(12) ��Ʊ���ࣺ��ֵ ˰ר�÷�Ʊ����ֵ˰��ͨ��Ʊ��
									 		 ����������ͳһ ��Ʊ����ֵ˰�� ����ͨ��Ʊ���� �� ( ��· ) �ѷ�Ʊ��
									 		 ���ֳ�����ͳһ ��Ʊ�����Ʊ �����˷�Ʊ*/
	private String legalPerson; 		// 8 ���˴��� varchar(32)
	private String legalIdCard; 		// 9 ��ݺ��� Varchar(20)
	private String legalMobile; 		// 10 �ֻ����� Varchar(12)
	private String legalIdCardImageURL; // 11 ���˴������֤ɨ��ͼƬ Varchar(64)
	private String finaceName; 			// 12 ������Ա varchar(32)
	private String finaceIdCard; 		// 13 ������Ա��ݺ��� Varchar(20)
	private String finaceMobile; 		// 14 ������Ա�ֻ����� Varchar(12)
	private String finaceIdCardImageURL;// 15 ������Ա���֤ɨ��ͼƬ Varchar(64)
	private String taxerName; 			// 16 ��˰��Ա varchar(32)
	private String taxerIdCard; 		// 17 ��˰��Ա��ݺ��� Varchar(20)
	private String taxerMobile; 		// 18 ��˰��Ա�ֻ����� Varchar(12)
	private String taxerIdCardImageURL; // 19 ��˰��Ա���֤ɨ��ͼƬ Varchar(64)
	private String bizAddressPhone; 	// 20 ������Ӫ�ص绰 Varchar(12)
	private String recordDate; 			// 21 ¼������ Date
	private Integer userId; 			// 22 ¼����Ա int
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getBizAddress() {
		return bizAddress;
	}
	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress;
	}
	public Integer getTaxOrganid() {
		return taxOrganId;
	}
	public void setTaxOrganid(Integer taxOrganId) {
		this.taxOrganId = taxOrganId;
	}
	public Integer getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}
	public String getBizScope() {
		return bizScope;
	}
	public void setBizScope(String bizScope) {
		this.bizScope = bizScope;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalIdCard() {
		return legalIdCard;
	}
	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}
	public String getLegalMobile() {
		return legalMobile;
	}
	public void setLegalMobile(String legalMobile) {
		this.legalMobile = legalMobile;
	}
	public String getLegalIdCardImageURL() {
		return legalIdCardImageURL;
	}
	public void setLegalIdCardImageURL(String legalIdCardImageURL) {
		this.legalIdCardImageURL = legalIdCardImageURL;
	}
	public String getFinaceName() {
		return finaceName;
	}
	public void setFinaceName(String finaceName) {
		this.finaceName = finaceName;
	}
	public String getFinaceIdCard() {
		return finaceIdCard;
	}
	public void setFinaceIdCard(String finaceIdCard) {
		this.finaceIdCard = finaceIdCard;
	}
	public String getFinaceMobile() {
		return finaceMobile;
	}
	public void setFinaceMobile(String finaceMobile) {
		this.finaceMobile = finaceMobile;
	}
	public String getFinaceIdCardImageURL() {
		return finaceIdCardImageURL;
	}
	public void setFinaceIdCardImageURL(String finaceIdCardImageURL) {
		this.finaceIdCardImageURL = finaceIdCardImageURL;
	}
	public String getTaxerName() {
		return taxerName;
	}
	public void setTaxerName(String taxerName) {
		this.taxerName = taxerName;
	}
	public String getTaxerIdCard() {
		return taxerIdCard;
	}
	public void setTaxerIdCard(String taxerIdCard) {
		this.taxerIdCard = taxerIdCard;
	}
	public String getTaxerMobile() {
		return taxerMobile;
	}
	public void setTaxerMobile(String taxerMobile) {
		this.taxerMobile = taxerMobile;
	}
	public String getTaxerIdCardImageURL() {
		return taxerIdCardImageURL;
	}
	public void setTaxerIdCardImageURL(String taxerIdCardImageURL) {
		this.taxerIdCardImageURL = taxerIdCardImageURL;
	}
	public String getBizAddressPhone() {
		return bizAddressPhone;
	}
	public void setBizAddressPhone(String bizAddressPhone) {
		this.bizAddressPhone = bizAddressPhone;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
