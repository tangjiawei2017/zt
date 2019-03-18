package com.shop.vo;

/**
 * 商品搜索条件类 展示分类都根据对应序列值搜素
 * 
 */
public class Condition {
	// 商品名称搜索
	private String search;
	// 分类序列号
	private String csequence;
	// 上下架状态 all,0,1
	private String sale;
	// 商品品牌id
	private String brandId;
    // 分类id
	private Integer categoryId;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSale() {
		return sale;
	}

	public String getCsequence() {
		return csequence;
	}

	public void setCsequence(String csequence) {
		this.csequence = csequence;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}
