package com.shop.vo;

/**
 * 商品属性模板字段 VO
 * 
 * @author xiaotang
 *
 * @date 2018-07-18 11:35:13
 */
public class TemplateFieldsVO {
	// 字段名
	private String code;
	// 字段名称
	private String name;

	// 显示名称
	private String showName;

	// 单位
	private String unit;

	// 是否显示
	private Boolean isShow;

	// 是否必填
	private Boolean isNeed;

	// 是否是基本属性(区分 基本属性和扩展属性)
	private Boolean isBasic;

	// 是不是不可更改项,主要用于 基本属性中编号和商品名称,必须显示,必须必填
	private Boolean isRead;

	// 类型(扩展属性) 1为文本项 2为下拉项 3为复选项
	private Integer type;

	// 用于扩展属性的 属性值
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsNeed() {
		return isNeed;
	}

	public Boolean getIsBasic() {
		return isBasic;
	}

	public void setIsBasic(Boolean isBasic) {
		this.isBasic = isBasic;
	}

	public void setIsNeed(Boolean isNeed) {
		this.isNeed = isNeed;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
