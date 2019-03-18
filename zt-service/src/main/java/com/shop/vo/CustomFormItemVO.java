package com.shop.vo;

public class CustomFormItemVO {
	private Integer id;

	// 表单项名称
	private String name;

	// 表单项code
	private String code;

	// 后台列表项名称(用于后台列表展示)
	private String itemName;

	/*
	 * 类型 0 单行文本 1 多行文本 2单选 3复选 4下拉框 5 日期 6地址 7 图片上传
	 * 
	 */
	private Integer type;

	// 表单值类型 0为任意字符 1为手机号码 2为邮箱地址 3为价格 4为自然数 5 为 dataValue中规定值
	private Integer dataType;

	// 表单项值约束 数据之间 ;分号隔开
	private String dataValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
