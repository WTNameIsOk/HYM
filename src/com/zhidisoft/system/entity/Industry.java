package com.zhidisoft.system.entity;

import java.util.Date;

/**
 * ��ҵ�����
 * @author ���쳽
 *
 */
public class Industry {

	private Integer id;				//1 ��ҵ��� id Int	����
	private String industryName;	//	2 ��ҵ����  Varchar(64)
	private Date recordDate;		//	3 ¼������  Date
	private Integer recordUserId;	//	4 ¼����Ա  int
	
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
