package com.shop.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 验证码过滤器
 * 
 */
public class UsernamePasswordAuthenticationFilterImpl extends UsernamePasswordAuthenticationFilter {
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String veryCode = request.getParameter("veryCode").trim();
		Object obj = request.getSession().getAttribute("verifyCode");
		String verifyCode = "";
		if (obj != null && obj instanceof String) {
			verifyCode = (String) obj;
			if (!verifyCode.equals(veryCode)) {
				request.setAttribute("verifyCodeError", true);
				throw new AuthenticationExceptionImpl("verifyCode is error!");
			}
		}
		return super.attemptAuthentication(request, response);
	}
}
