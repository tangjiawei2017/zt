package com.shop.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
	public Logger logger = Logger.getLogger(this.getClass());

	public Logger getLogger() {
		return logger;
	}

	/**
	 * 
	 */
	public String getCurrentLoginAdmin() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
