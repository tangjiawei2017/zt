package com.shop.model;

/**
 * 网站配置类
 * 
 */
public class SiteConfigDO {
	private Integer id;

	/**
	 * 服务级配置key
	 */
	private String identifier;

	/**
	 * 服务级配置 value
	 */
	private String value;

	/**
	 * 服务级json串的值
	 */
	private String jsonvalue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getJsonvalue() {
		return jsonvalue;
	}

	public void setJsonvalue(String jsonvalue) {
		this.jsonvalue = jsonvalue;
	}
}
