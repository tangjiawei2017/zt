package com.shop.model;

import java.util.Date;

/**
 * 资讯内容
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-30 17:12:20
 */
public class InfoContentDO {
	private Integer id;

	private Integer categoryId;

	private String image;

	private String name;

	// 作者
	private String author;

	// 来源
	private String source;

	private Date created;

	private Date modified;

	// 概要
	private String summary;

	private String content;

	// 关联资讯分类
	private InfoCategoryDO category;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getContent() {
		return content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public InfoCategoryDO getCategory() {
		return category;
	}

	public void setCategory(InfoCategoryDO category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}