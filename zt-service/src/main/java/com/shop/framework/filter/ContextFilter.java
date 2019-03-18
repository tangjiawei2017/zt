package com.shop.framework.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.framework.context.Context;
import com.shop.framework.context.ContextHolder;

/**
 * Context 拦截器
 * 
 */
public class ContextFilter extends OncePerRequestFilter {
	private Properties properties = null;
	private Logger logger = Logger.getLogger(this.getClass());

	/*
	 * 拦截初始化 加载VHOST.properties文件,根据域名返回对应siteID
	 */
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		loadingVHOST();
	}

	private void loadingVHOST() {
		if (StringUtils.isEmpty(ContextHolder.getContext().getSiteId())) {
			String basePath = this.getServletContext().getRealPath("/");
			File vhost = new File(basePath + "../../cluster/VHOST.properties");
			properties = new Properties();
			try {
				properties.load(new FileInputStream(vhost));
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	/*
	 * 读取 VHOST数据,并创建 Context对象,存储到 ThreadLocal中
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain china)
			throws ServletException, IOException {
		String siteId = null;
		Context context = new Context();
		// 获取网站域名 在nginx.conf 中配置 传递域名
		String domain = request.getHeader("Host");
		String value = properties.getProperty(domain);
		// 需要说明: 由于nginx原因,在properties中读取的编号都会加上一个分号,需要去掉
		if (StringUtils.isNotEmpty(value)) {
			siteId = value.substring(0, value.length() - 1);
		}
		context.setServerName(domain);
		context.setSiteId(siteId);
		ContextHolder.setContext(context);
		china.doFilter(request, response);
	}
}
