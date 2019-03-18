package com.shop.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotang
 * @version 2.0
 * @date 2018-07-23 23:58:01
 */
public class ProductRelateVO {
	private Integer id;
	// layui 标识是否选中
	@JsonProperty("LAY_CHECKED")
	private Boolean LAY_CHECKED = new Boolean(false);
	// 商品编码
	private String code;

	private String name;
	// 市场价
	private BigDecimal marketprice = new BigDecimal(0);
	// 实际价格
	private BigDecimal price = new BigDecimal(0);
	// 上下架状态 0表示下架,1表示上架
	private Integer sale;
	// 排序code,主要用于展示分类中所有分类下的商品显示次序,数值越小,显示次序越靠前
	private Integer sortCode = new Integer(100);
	// 商品库存
	private Integer stock;
	// 计量单位
	private String unit;
	// 商品缩略图
	private String image;
	// 商品图片
	private String images;
	// 商品收藏图片路径
	private String collectImg;
	// 商品规格
	private String spec;
	// 商品描述
	private String description;

	// 分类名称
	private String category;

	// 商品品牌名称
	private String brand;
	// 创建时间
	private String created;

	// 更新时间
	private String modified;

	// 扩展属性
	private String ext0;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private String ext6;
	private String ext7;
	private String ext8;
	private String ext9;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImages() {
		return images;
	}

	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCollectImg() {
		return collectImg;
	}

	public void setCollectImg(String collectImg) {
		this.collectImg = collectImg;
	}

	public BigDecimal getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(BigDecimal marketprice) {
		this.marketprice = marketprice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getExt0() {
		return ext0;
	}

	public void setExt0(String ext0) {
		this.ext0 = ext0;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	public String getExt6() {
		return ext6;
	}

	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}

	public String getExt7() {
		return ext7;
	}

	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}

	public String getExt8() {
		return ext8;
	}

	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}

	public String getExt9() {
		return ext9;
	}

	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}

	public Boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}

	public void setLAY_CHECKED(Boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}

	@Override
	public String toString() {
		return "ProductRelateVO [id=" + id + ", LAY_CHECKED=" + LAY_CHECKED + ", code=" + code + ", name=" + name
				+ ", marketprice=" + marketprice + ", price=" + price + ", sale=" + sale + ", sortCode=" + sortCode
				+ ", stock=" + stock + ", unit=" + unit + ", image=" + image + ", images=" + images + ", collectImg="
				+ collectImg + ", spec=" + spec + ", description=" + description + ", category=" + category + ", brand="
				+ brand + ", created=" + created + ", modified=" + modified + ", ext0=" + ext0 + ", ext1=" + ext1
				+ ", ext2=" + ext2 + ", ext3=" + ext3 + ", ext4=" + ext4 + ", ext5=" + ext5 + ", ext6=" + ext6
				+ ", ext7=" + ext7 + ", ext8=" + ext8 + ", ext9=" + ext9 + "]";
	}
}
