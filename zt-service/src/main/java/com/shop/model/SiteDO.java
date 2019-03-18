package com.shop.model;

import java.util.Date;

/**
 * 网站名称,logo实体类
 * 
 */
public class SiteDO {

	private Integer id;

	private String siteName;

	// 网站开通时间
	private Date ctime;

	private String logo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
}
