package com.shop.model;

import java.util.Date;

/**
 * 页面分类
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-17 01:25:58
 */
public class CommonCategoryDO {
	private String id;

	private String name;

	private Date ctime;

	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	@Override
	public String toString() {
		return "CommonCategoryDO [id=" + id + ", name=" + name + ", ctime=" + ctime + ", description=" + description
				+ "]";
	}

}