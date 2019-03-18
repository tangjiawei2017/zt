package com.shop.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/*
 * 认证失败,需要记录登录次数,并返回到失败页面
 */
public class AuthenticationFailureHandlerImpl implements
		AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		Integer count = 0;
		Object obj = session.getAttribute("loginFailureCount");
		if (obj != null && obj instanceof Integer) {
			count = (Integer) obj;
		}
		count++;
		session.setAttribute("loginFailureCount", count);
		StringBuffer url = new StringBuffer(request.getContextPath());
		// 1表示用户名密码错误,2表示验证码错误
		if (request.getAttribute("verifyCodeError") != null) {
			url.append("/pages/login.jsp?login_error=2");
		} else {
			url.append("/pages/login.jsp?login_error=1");
		}
		response.sendRedirect(url.toString());
	}
}
