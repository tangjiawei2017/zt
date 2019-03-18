package com.shop.framework.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.framework.context.ContextHolder;

/**
 * 网站配置系统 登录filter
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-20 16:54:09
 */
public class SiteMakeLoginFilter extends OncePerRequestFilter {
	private static final String LOGIN_KEY = "sitemaketool_token";
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String pass[] = new String[] { "/404.jsp", ".css" };
		boolean isPass = false;
		for (String p : pass) {
			if (uri.endsWith(p)) {
				isPass = true;
			}
		}
		if (!isPass && !isLoginCookie(req)) {
			boolean isLogin = false;
			String encodedSiteId = request.getParameter("encodedSiteID");
			if (!StringUtils.isEmpty(encodedSiteId)) {
				String token = new String(Base64Utils.decode(encodedSiteId.getBytes()));
				String siteId = ContextHolder.getContext().getSiteId();
				if (token.equals(siteId)) {
					isLogin = true;
					Cookie cookie = new Cookie(LOGIN_KEY, encodedSiteId);
					cookie.setPath("/");
					cookie.setMaxAge(-1);
					rep.addCookie(cookie);
				}
			}

			if (!isLogin) {
				String context = req.getContextPath();
				String forward = context + "/pages/404.jsp";
				this.logger.info("forward==========>" + forward);
				rep.sendRedirect(forward);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isLoginCookie(HttpServletRequest request) {
		boolean isLogin = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(LOGIN_KEY)) {
					String value = cookie.getValue();
					String token = new String(Base64Utils.decode(value.getBytes()));
					String siteId = ContextHolder.getContext().getSiteId();
					if (token.equals(siteId))
						isLogin = true;
					break;
				}
			}
		}
		return isLogin;
	}
}
