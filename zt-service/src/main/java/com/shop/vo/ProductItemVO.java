package com.shop.vo;

import java.math.BigDecimal;

public class ProductItemVO {
	private Integer id;
	private Integer productId;
	private String spec0;
	private String finalSepc0;
	private String spec1;
	private String finalSepc1;
	private String spec2;
	private String finalSepc2;
	private BigDecimal price = new BigDecimal(0);
	private String unit;
	// 默认启用 货品
	private Integer status = new Integer(1);

	// 货品是否是默认商品的货品
	private Integer isDefault = new Integer(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getSpec0() {
		return spec0;
	}

	public void setSpec0(String spec0) {
		this.spec0 = spec0;
	}

	public String getSpec1() {
		return spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}

	public String getSpec2() {
		return spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFinalSepc0() {
		return finalSepc0;
	}

	public void setFinalSepc0(String finalSepc0) {
		this.finalSepc0 = finalSepc0;
	}

	public String getFinalSepc1() {
		return finalSepc1;
	}

	public void setFinalSepc1(String finalSepc1) {
		this.finalSepc1 = finalSepc1;
	}

	public String getFinalSepc2() {
		return finalSepc2;
	}

	public void setFinalSepc2(String finalSepc2) {
		this.finalSepc2 = finalSepc2;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}
