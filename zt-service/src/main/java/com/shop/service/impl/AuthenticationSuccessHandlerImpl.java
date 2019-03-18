package com.shop.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/*
 * 登录成功 index.action
 */
public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/index.action");
	}
}