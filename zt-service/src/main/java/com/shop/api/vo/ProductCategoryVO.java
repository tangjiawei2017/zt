package com.shop.api.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类 API VO
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-05 17:07:36
 */
public class ProductCategoryVO {
	private Integer id;

	// 分类名称
	private String name;

	// 分类图片
	private String image;

	// 商品集合
	private List<ProductApiVO> productList = new ArrayList<ProductApiVO>();

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
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ProductApiVO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductApiVO> productList) {
		this.productList = productList;
	}
}
