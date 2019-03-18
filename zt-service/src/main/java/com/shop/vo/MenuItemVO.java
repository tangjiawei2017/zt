package com.shop.vo;

import java.util.List;

/**
 * 菜单
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-16 16:47:37
 */
public class MenuItemVO {
	private String name;
	private String service;
	private String value;
	private List<SubMenuItemVO> subMenuList;

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

	public List<SubMenuItemVO> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<SubMenuItemVO> subMenuList) {
		this.subMenuList = subMenuList;
	}

	@Override
	public String toString() {
		return "MenuItemVO [name=" + name + ", service=" + service + ", value=" + value + ", subMenuList=" + subMenuList
				+ "]";
	}
}
