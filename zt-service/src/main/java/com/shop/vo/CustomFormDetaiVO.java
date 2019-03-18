package com.shop.vo;

/**
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-13 01:17:55
 */
public class CustomFormDetaiVO {
	// 标签名称
	private String name;
	// 标签值
	private String value;
	/*
	 * 类型 0 单行文本 1 多行文本 2单选 3复选 4下拉框 5 日期 6地址 7 图片上传
	 * 
	 */
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
