package com.shop.controller;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.shop.utils.IConstant;

public class BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	public Logger getLogger() {
		return logger;
	}

	/**
	 * 获取登录用户名
	 * 
	 * @param session
	 * @return
	 */
	public String getLoginCustomerAccount(HttpSession session) {
		String account = null;
		Object obj = session.getAttribute(IConstant.LOGIN_MEMBER_ACCOUNT_SESSION);
		if (obj != null && obj instanceof String) {
			account = (String) obj;
		}
		return account;
	}

	/**
	 * 获取登录用户id
	 * 
	 * @param session
	 * @return
	 */
	public Integer getLoginCustomerId(HttpSession session) {
		if (session.getAttribute(IConstant.LOGIN_MEMBER_ACCOUNT_SESSION) != null
				&& session.getAttribute(IConstant.LOGIN_MEMEBER_ID_SESSION) != null) {
			return (Integer) session.getAttribute(IConstant.LOGIN_MEMEBER_ID_SESSION);
		} else {
			return null;
		}
	}

	/**
	 * 设置登录用户名
	 * 
	 * @param session
	 * @param account
	 */
	public void setLoginCustomerAccount(HttpSession session, String account) {
		session.setAttribute(IConstant.LOGIN_MEMBER_ACCOUNT_SESSION, account);
	}

	/**
	 * 设置登录用户id
	 * 
	 * @param session
	 * @param id
	 */
	public void setLoginCustomerId(HttpSession session, Integer id) {
		session.setAttribute(IConstant.LOGIN_MEMEBER_ID_SESSION, id);
	}

	/**
	 * 注销当前用户
	 * 
	 * @param session
	 */
	public void logoutLoginCustomer(HttpSession session) {
		session.removeAttribute(IConstant.LOGIN_MEMBER_ACCOUNT_SESSION);
	}

	/**
	 * 获取Cookie中指定的value
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public String getCookieValueByKey(HttpServletRequest request, String key) {
		Cookie cookies[] = request.getCookies();
		String value = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}

	/**
	 * 删除Cookie中指定的key
	 * 
	 * @param request
	 * @param key
	 */
	public void removeCoookieByKey(HttpServletResponse response, String key) {
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 获取网站域名
	 * 
	 * @param request
	 */
	public String getContextDomain(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String domain = url.substring(0, url.indexOf(uri)).toString();
		logger.info("domain:" + domain);
		return domain;
	}

	/**
	 * 支付结果页面
	 * 
	 * @param response
	 * @param show_url
	 */
	protected void showJsp(HttpServletResponse response, String show_url) {
		response.setContentType("text/html;charset=" + IConstant.ALIPAY_CHARSET_UTF8);
		String strHtml = "<html><head>\r\n" + "<meta name=\"TENCENT_ONLINE_PAYMENT\" content=\"China TENCENT\">\r\n"
				+ "<result>1</result>\r\n<redirecturl>" + show_url + "</redirecturl>"
				+ "<script language=\"javascript\">\r\nwindow.location.href='" + show_url + "';\r\n" + "</script>\r\n"
				+ "</head><body></body></html>";
		try {
			PrintWriter out = response.getWriter();
			out.println(strHtml);
			out.flush();
			out.close();
		} catch (Exception e) {
			this.logger.error(e);
		}
	}
}
