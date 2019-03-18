package com.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品展示分类
 * 
 * @author xiaotang
 * @version 2.0
 * @date 2018-07-19 11:11:29
 */
public class ProductCategoryDO {

	private Integer id;

	// 父类id (0代表没有父节点)
	private Integer parentid;

	private String name;

	// 分类序列,展示分类和管理分类序列不能一样,会在分类中查找商品中用到
	private String sequence;

	// 创建时间
	private Date created;

	// 更新时间
	private Date modified;

	// 分类图片
	private String image;
	// 说明
	private String content;

	// 对应的属性模板id
	private Integer templateid;
	// 商品属性模板
	private ProductTemplateDO template = new ProductTemplateDO();

	// 子节点集合
	private List<ProductCategoryDO> children = new ArrayList<ProductCategoryDO>();

	// 父节点
	private ProductCategoryDO parent;

	// 商品集合
	private List<ProductDO> productList = new ArrayList<ProductDO>();

	public Integer getId() {
		return id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public List<ProductDO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDO> productList) {
		this.productList = productList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setContent(String content) {
		this.content = content;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Integer getTemplateid() {
		return templateid;
	}

	public void setTemplateid(Integer templateid) {
		this.templateid = templateid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ProductCategoryDO> getChildren() {
		return children;
	}

	public void setChildren(List<ProductCategoryDO> children) {
		this.children = children;
	}

	public ProductCategoryDO getParent() {
		return parent;
	}

	public void setParent(ProductCategoryDO parent) {
		this.parent = parent;
	}

	public ProductTemplateDO getTemplate() {
		return template;
	}

	public void setTemplate(ProductTemplateDO template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "ProductCategoryDO [id=" + id + ", parentid=" + parentid + ", name=" + name + ", sequence=" + sequence
				+ ", created=" + created + ", modified=" + modified + ", image=" + image + ", content=" + content
				+ ", templateid=" + templateid + "]";
	}
}
