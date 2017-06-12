package com.zhidisoft.entity;

/**
 * 税源基础信息表
 * @author 贺天辰
 *
 */
public class TaxSource {

	private Integer id; 			// 1 任务编号 int 主键
	private Integer payerId; 		// 2 纳税人 id int FK ， tb_tax_payer 表的 id
	private String taskName; 		// 3 任务名称 varchar(128)
	private Integer taxOrganId;		// 4 下达部门 int 关联 tb_tax_organ
	private Integer approverId;		// 5 批准人 int 关联 tb_taxer
	private Integer executeId;		// 6 执行人员 int 关联 tb_taxer 的
	private String executeTime; 		// 7 执行时间 Date
	private String taskFrom; 		// 8 任务来源 Varchar(12)
	private String taskState; 		// 9 任务执行情况 Varchar(128)
	private String idea; 			// 10 调查结论或意见 Varchar(512)
	private Integer riskLevel; 		// 11 风险等级 int 0 无 1 低 2 中 3 高 默认为 0
	private String recordDate; 		// 12 录入日期 Date
	private Integer recordUserId;	// 13 录入人员 int

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
