package com.shop.model;

/**
 * 关联商品
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-01 15:59:40
 */
public class ProductRelateDO {
	private Integer id;
	// 商品id
	private Integer productId;

	// 关联id
	private Integer relateId;

	// 关联商品
	private ProductDO relateProduct;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getRelateId() {
		return relateId;
	}

	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}

	public ProductDO getRelateProduct() {
		return relateProduct;
	}

	public void setRelateProduct(ProductDO relateProduct) {
		this.relateProduct = relateProduct;
	}

}