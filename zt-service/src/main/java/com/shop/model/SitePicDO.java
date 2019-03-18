package com.shop.model;

/**
 * 首页轮播图片do
 * 
 */
public class SitePicDO {
	private Integer id;
	private String path;

	// 手机图片
	private String mpath;

	public String getMpath() {
		return mpath;
	}

	public void setMpath(String mpath) {
		this.mpath = mpath;
	}

	private String link;

	// 序号
	private Integer number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "SitePicDO [id=" + id + ", path=" + path + ", link=" + link + ", number=" + number + "]";
	}
}
