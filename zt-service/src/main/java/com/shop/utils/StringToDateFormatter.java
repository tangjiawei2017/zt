package com.shop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class StringToDateFormatter implements Formatter<Date> {
	private SimpleDateFormat format;

	public StringToDateFormatter(String pattern) {
		this.format = new SimpleDateFormat(pattern);
	}

	@SuppressWarnings("all")
	@Override
	public String print(Date date, Locale locale) {
		return date.toLocaleString();
	}

	@Override
	public Date parse(String source, Locale locale) {
		try {
			return this.format.parse(source);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
