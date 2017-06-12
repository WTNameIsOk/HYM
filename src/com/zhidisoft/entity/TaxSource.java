package com.zhidisoft.entity;

/**
 * ˰Դ������Ϣ��
 * @author ���쳽
 *
 */
public class TaxSource {

	private Integer id; 			// 1 ������ int ����
	private Integer payerId; 		// 2 ��˰�� id int FK �� tb_tax_payer ��� id
	private String taskName; 		// 3 �������� varchar(128)
	private Integer taxOrganId;		// 4 �´ﲿ�� int ���� tb_tax_organ
	private Integer approverId;		// 5 ��׼�� int ���� tb_taxer
	private Integer executeId;		// 6 ִ����Ա int ���� tb_taxer ��
	private String executeTime; 		// 7 ִ��ʱ�� Date
	private String taskFrom; 		// 8 ������Դ Varchar(12)
	private String taskState; 		// 9 ����ִ����� Varchar(128)
	private String idea; 			// 10 ������ۻ���� Varchar(512)
	private Integer riskLevel; 		// 11 ���յȼ� int 0 �� 1 �� 2 �� 3 �� Ĭ��Ϊ 0
	private String recordDate; 		// 12 ¼������ Date
	private Integer recordUserId;	// 13 ¼����Ա int

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayerId() {
		return payerId;
	}

	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaxOrganId() {
		return taxOrganId;
	}

	public void setTaxOrganId(Integer taxOrganId) {
		this.taxOrganId = taxOrganId;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public Integer getExecuteId() {
		return executeId;
	}

	public void setExecuteId(Integer executeId) {
		this.executeId = executeId;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getTaskFrom() {
		return taskFrom;
	}

	public void setTaskFrom(String taskFrom) {
		this.taskFrom = taskFrom;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
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
