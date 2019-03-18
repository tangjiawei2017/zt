package com.shop.vo;

/**
 * 子菜单
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-16 16:49:09
 */
public class SubMenuItemVO {
	private String url;
	private String name;
	private String service;
	private String value;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SubMenuItemVO [url=" + url + ", name=" + name + ", service=" + service + ", value=" + value + "]";
	}
}
