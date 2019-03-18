package com.shop.utils;

import org.springframework.core.convert.converter.Converter;

/**
 * Integer-->Boolean类型转换器
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-07-17 21:41:03
 */
public class IntegerToBooleanConverter implements Converter<Integer, Boolean> {

	@Override
	public Boolean convert(Integer source) {
		if (source.equals(0)) {
			return false;
		}
		return true;
	}
}
