package com.shop.utils;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.shop.framework.context.ContextHolder;

/**
 * 网站路径常量值
 */
public class WebSiteConstant {
	/**
	 * 产品部署目录 tomcat目录
	 */
	public static final String HOME;
	/**
	 * cluster目录。 /usr/local/apache-tomcat-8.5.32/cluster
	 */
	public static final String CLUSTER;

	/*
	 * siteCode 目录 /usr/local/apache-tomcat-8.5.32/cluster/siteCode
	 */
	public static final String SITECODE;

	/**
	 * 项目路径(相对路径) 比如: /manager
	 */
	public static final String PROJECT;

	/**
	 * 图片仓库地址
	 * /usr/local/apache-tomcat-8.5.32/cluster/ZH_CN_20180710224630/imageRepository
	 */
	public static final String REPOSITORY;

	/**
	 * 商品图片目录
	 */
	public static final String PRODUCT;

	/*
	 * 
	 * 资讯图片目录
	 */
	public static final String INFO;

	/*
	 * 每页大图
	 */
	public static final String GALLERY;

	/**
	 * 网站级目录(logo 目录路径)
	 */
	public static final String SITE;

	/**
	 * 会员图片目录
	 */
	public static final String CUSTOMER;

	/*
	 * 描述图片目录
	 */
	public static final String DESCRIPTION;

	/**
	 * 临时目录(cluster 子目录) /usr/local/apache-tomcat-8.5.32/cluster/temp
	 */
	public static final String TEMP;

	static {
		HOME = findAndStoreHome();
		CLUSTER = findAndStoreDir(IConstant.CLUSTER_KEY, HOME + File.separator + IConstant.CLUSTER);
		SITECODE = findSiteCodeDir();
		REPOSITORY = findRepositorDir();
		PROJECT = findProjectAndStorDir();
		TEMP = findAndStoreDir(IConstant.TEMP_KEY, CLUSTER + File.separator + IConstant.TEMP);
		PRODUCT = findSiteResourceDir(IConstant.PRODUCT);
		INFO = findSiteResourceDir(IConstant.INFO);
		GALLERY = findSiteResourceDir(IConstant.GALLERY);
		SITE = findSiteResourceDir(IConstant.SITE);
		CUSTOMER = findSiteResourceDir(IConstant.CUSTOMER);
		DESCRIPTION = findSiteResourceDir(IConstant.DESCRIPTION);
	}

	private static String findAndStoreHome() {
		String p = System.getProperty(IConstant.HOME_KEY);
		if (p == null) {
			p = System.getProperty("catalina.home");
		}
		if (p == null) {
			p = System.getProperty("user.dir", "/");
		}
		System.setProperty(IConstant.HOME_KEY, p);
		return p;
	}

	private static String findSiteCodeDir() {
		String siteId = ContextHolder.getContext().getSiteId();
		if (StringUtils.isNotEmpty(siteId)) {
			return CLUSTER + File.separator + siteId;
		} else {
			return null;
		}
	}

	private static String findRepositorDir() {
		String siteId = ContextHolder.getContext().getSiteId();
		if (StringUtils.isNotEmpty(siteId)) {
			return CLUSTER + File.separator + siteId + File.separator + IConstant.REPOSITORY;
		} else {
			return null;
		}
	}

	/**
	 * 获取项目根路径(相对路径) /manager
	 */
	private static String findProjectAndStorDir() {
		// 项目根路径(绝对路径)
		String p = System.getProperty("webApp.root");
		return File.separator
				+ p.substring(p.substring(0, p.length() - 1).lastIndexOf(File.separator) + 1, p.length() - 1);
	}

	private static String findAndStoreDir(String key, String defaultPath) {
		String p = System.getProperty(key);
		if (p == null) {
			p = defaultPath;
			System.setProperty(key, p);
		}
		return p;
	}

	/**
	 * 获取网站级 资源目录
	 * 
	 * @param module
	 * @return
	 */
	private static String findSiteResourceDir(String module) {
		return REPOSITORY + File.separator + module;
	}
}
