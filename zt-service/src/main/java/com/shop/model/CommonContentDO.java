package com.shop.model;

import java.util.Date;

/**
 * 页面内容
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-17 01:25:45
 */
public class CommonContentDO {
	private String id;

	private String categoryid;

	private String title;

	private Date ctime;

	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}