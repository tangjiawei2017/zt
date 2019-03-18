package com.shop.vo;

/**
 * @author xiaotang
 *
 * @date 2018-07-16 12:24:44
 */
public class ProductTemplateVO {
	private Integer id;

	// 模板名称
	private String name;

	// 基本属性
	private String basicattr;

	// 扩展属性
	private String extendattr;

	// 是否为默认模板
	private Boolean isdefault = new Boolean(false);

	// 关联展示分类
	private String categorys;

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

	public String getBasicattr() {
		return basicattr;
	}

	public void setBasicattr(String basicattr) {
		this.basicattr = basicattr;
	}

	public String getExtendattr() {
		return extendattr;
	}

	public void setExtendattr(String extendattr) {
		this.extendattr = extendattr;
	}

	public Boolean getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

}
