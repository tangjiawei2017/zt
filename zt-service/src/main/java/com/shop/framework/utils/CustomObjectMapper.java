package com.shop.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson格式化输出
 * 
 */
public class CustomObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;
	// date输出格式
	private String dateFormatPattern;

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public void init() {
		// 进行日期格式化
		if (StringUtils.isNotEmpty(dateFormatPattern)) {
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			setDateFormat(dateFormat);
		}
	}
}
