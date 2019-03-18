package com.shop.model;

/**
 * 自定义表单项
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-21 15:32:46
 */
public class CustomFormItemDO {
	private Integer id;

	// 表单id
	private String fromId;

	// 表单项名称
	private String name;

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

	// 是否作为列表项(在后台table是否显示该项)
	private Boolean isItem;

	// 表单项输入提示
	private String itemPrompt;

	// 是否必须
	private Boolean isRequried;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId == null ? null : fromId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue == null ? null : dataValue.trim();
	}

	public Boolean getIsItem() {
		return isItem;
	}

	public void setIsItem(Boolean isItem) {
		this.isItem = isItem;
	}

	public String getItemPrompt() {
		return itemPrompt;
	}

	public void setItemPrompt(String itemPrompt) {
		this.itemPrompt = itemPrompt == null ? null : itemPrompt.trim();
	}

	public Boolean getIsRequried() {
		return isRequried;
	}

	public void setIsRequried(Boolean isRequried) {
		this.isRequried = isRequried;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "CustomFormItemDO [id=" + id + ", fromId=" + fromId + ", name=" + name + ", type=" + type + ", dataType="
				+ dataType + ", dataValue=" + dataValue + ", isItem=" + isItem + ", itemPrompt=" + itemPrompt
				+ ", isRequried=" + isRequried + "]";
	}
}