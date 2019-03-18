package com.shop.vo;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerVO {
	private Integer id;
	private String account;
	private String name;
	// 性别 0保密 1为女,2为男
	private Integer sex = new Integer(0);
	// 图像路径
	private String imagePath;
	private String cellphone;
	private String email;
	// 注册时间
	private Date regtime;
	// 上一次登录时间
	private Date logintime;
	// 登录次数
	private Integer loginCount;
	// 1为正常,0为关停
	private Integer status = new Integer(1);
	// 来源 0为前台注册,1为后台手动添加
	private Integer source;
	// 默认收货地址
	private String address;
	// 详细地址
	private String detailAdrr;
	// 微信实体类
	private Integer wxId;
	// 评价次数
	private Integer reviewCount = new Integer(0);
	// 消费次数
	private Integer consumeCount = new Integer(0);
	// 累计消费金额
	private BigDecimal consumeTotal = new BigDecimal(0.00);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAdrr() {
		return detailAdrr;
	}

	public void setDetailAdrr(String detailAdrr) {
		this.detailAdrr = detailAdrr;
	}

	public Integer getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(Integer consumeCount) {
		this.consumeCount = consumeCount;
	}

	public BigDecimal getConsumeTotal() {
		return consumeTotal;
	}

	public void setConsumeTotal(BigDecimal consumeTotal) {
		this.consumeTotal = consumeTotal;
	}

	public Integer getWxId() {
		return wxId;
	}

	public void setWxId(Integer wxId) {
		this.wxId = wxId;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}
}
