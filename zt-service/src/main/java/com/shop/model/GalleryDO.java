package com.shop.model;

import java.util.Date;

/**
 * 其他广告,各个页面的大图
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-03 13:43:05
 */
public class GalleryDO {
	private String id;

	private String name;

	private String path;

	// 手机小图
	private String mpath;

	private Date ctime;

	private String content;

	// 修改时间
	private Date modify;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getModify() {
		return modify;
	}

	public void setModify(Date modify) {
		this.modify = modify;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMpath() {
		return mpath;
	}

	public void setMpath(String mpath) {
		this.mpath = mpath;
	}

	@Override
	public String toString() {
		return "GalleryDO [id=" + id + ", name=" + name + ", path=" + path + ", ctime=" + ctime + ", modify=" + modify
				+ "]";
	}
}