package com.zhidisoft.system.entity;

import java.util.Date;

/**
 * 行业代码表
 * @author 贺天辰
 *
 */
public class Industry {

	private Integer id;				//1 行业编号 id Int	主键
	private String industryName;	//	2 行业名称  Varchar(64)
	private Date recordDate;		//	3 录入日期  Date
	private Integer recordUserId;	//	4 录入人员  int
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Integer getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(Integer recordUserId) {
		this.recordUserId = recordUserId;
	}
	
}
