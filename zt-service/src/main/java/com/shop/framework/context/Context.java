package com.shop.framework.context;

/**
 * 应用上下文
 * 
 */
public class Context {
	/** 网站id **/
	private String siteId;
	/** 网站域名 **/
	private String serverName;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}
