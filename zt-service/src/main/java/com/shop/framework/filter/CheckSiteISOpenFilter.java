package com.shop.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.shop.model.SiteConfigDO;
import com.shop.service.ISiteConfigService;
import com.shop.service.impl.SiteConfigServiceImpl;
import com.shop.utils.IConstant;

/**
 * 判断网店是否开启
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-17 19:02:25
 */
public class CheckSiteISOpenFilter implements Filter {

	private ISiteConfigService siteConfigService;

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		XmlWebApplicationContext ctx = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);
		if (siteConfigService == null && ctx != null) {
			siteConfigService = (ISiteConfigService) ctx.getBean(SiteConfigServiceImpl.class);
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		SiteConfigDO sysConfig = siteConfigService.getSiteConfigValue(IConstant.SYSTEM_SITE_RUN_STATE);
		SiteConfigDO siteConfig = siteConfigService.getSiteConfigValue(IConstant.SITE_RUN_STATE);
		// 闭店模式,跳转到闭店页面
		// 放行url
		String[] passUrl = new String[] { "/getSite.action", "/getSiteColse.action" };
		boolean pass = false;
		for (String url : passUrl) {
			if (request.getRequestURI().endsWith(url)) {
				pass = true;
				break;
			}
		}
		if ((sysConfig != null && sysConfig.getValue().equals("1"))
				|| (siteConfig != null && siteConfig.getValue().equals("0"))) {
			if (pass) {
				chain.doFilter(request, response);
			} else {
				// ajax重定向处理方式
				if (isAjax(request)) {
					// 重定向
					response.setHeader("REDIRECT", "REDIRECT");
					// 重定向的路径
					response.setHeader("CONTEXTPATH", "/tip_site_close.html");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				} else {
					response.sendRedirect("/tip_site_close.html");
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * 判断是否为ajax请求
	 * 
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
		String param = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(param))
			return true;
		else
			return false;
	}
}
