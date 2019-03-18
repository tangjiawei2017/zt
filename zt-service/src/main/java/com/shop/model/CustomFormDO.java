package com.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自定义表单
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-21 15:32:01
 */
public class CustomFormDO {
	private String id;

	// 表单名称
	private String name;
	// 表单创建时间
	private Date ctime;

	// 表单项
	private List<CustomFormItemDO> itemList = new ArrayList<CustomFormItemDO>();

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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public List<CustomFormItemDO> getItemList() {
		return itemList;
	}

	public void setItemList(List<CustomFormItemDO> itemList) {
		this.itemList = itemList;
	}
}