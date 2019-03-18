package com.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性模板
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-07-16 00:00:07
 */
public class ProductTemplateDO {
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
	private List<ProductCategoryDO> categoryList = new ArrayList<ProductCategoryDO>();

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
		this.name = name == null ? null : name.trim();
	}

	public String getBasicattr() {
		return basicattr;
	}

	public void setBasicattr(String basicattr) {
		this.basicattr = basicattr == null ? null : basicattr.trim();
	}

	public String getExtendattr() {
		return extendattr;
	}

	public void setExtendattr(String extendattr) {
		this.extendattr = extendattr == null ? null : extendattr.trim();
	}


	public List<ProductCategoryDO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<ProductCategoryDO> categoryList) {
		this.categoryList = categoryList;
	}

	public Boolean getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}

}