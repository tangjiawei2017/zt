package com.shop.api.vo;

/**
 * 通用内容
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-18 16:40:28
 */
public class ComContentApiVO {

	private String categoryid;

	private String title;

	private String ctime;

	private String content;

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
		this.title = title;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
