package com.shop.vo;

/**
 * 会员搜索条件
 * 
 */
public class CustomerCondition {
	// 类型: account,name,phone
	private String type;
	// 值
	private String search;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
