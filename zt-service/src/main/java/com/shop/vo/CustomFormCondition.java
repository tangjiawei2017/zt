package com.shop.vo;

/**
 * 表单项条件
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-12 23:03:55
 */
public class CustomFormCondition {
	// 表单id
	private String formId;
	// 处理状态 0为未处理 1为已处理 2为全部
	private Integer status = new Integer(0);

	// 订购时间--开始时间
	private String startTime;
	// 订购时间--结束时间
	private String endTime;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
}
