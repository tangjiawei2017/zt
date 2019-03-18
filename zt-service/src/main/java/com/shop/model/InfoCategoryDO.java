package com.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资讯分类
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-30 17:12:05
 */
public class InfoCategoryDO {
	private Integer id;

	private Integer parentid;

	private String name;

	private String image;

	private Date created;

	private Date modified;

	// 子节点集合
	private List<InfoCategoryDO> children = new ArrayList<InfoCategoryDO>();

	// 资讯集合
	private List<InfoContentDO> infoContentList = new ArrayList<InfoContentDO>();

	// 父节点
	private InfoCategoryDO parent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
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

	public List<InfoCategoryDO> getChildren() {
		return children;
	}

	public void setChildren(List<InfoCategoryDO> children) {
		this.children = children;
	}

	public InfoCategoryDO getParent() {
		return parent;
	}

	public void setParent(InfoCategoryDO parent) {
		this.parent = parent;
	}

	public List<InfoContentDO> getInfoContentList() {
		return infoContentList;
	}

	public void setInfoContentList(List<InfoContentDO> infoContentList) {
		this.infoContentList = infoContentList;
	}
}