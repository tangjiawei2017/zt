package com.shop.model;

import java.util.Date;

/**
 * 商品品牌类
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-07-15 20:01:37
 */
public class ProductBrandDO {
	private Integer id;

	// 品牌名称
	private String name;

	// 品牌图片
	private String image;

	// 品牌创建时间
	private Date createtime;

	// 更新时间
	private Date updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}