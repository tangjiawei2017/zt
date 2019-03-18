package com.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultDefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON解析配置:主要用到 将bean转换成JSONObject时,Integer为null会转换成0的bug修改
 * 
 */
public class JsonConfigFactory {
	private static JsonConfig instance = null;

	public static synchronized JsonConfig getInstance() {
		if (instance == null) {
			instance = new JsonConfig();
			register(instance);
		}
		return instance;
	}

	@SuppressWarnings("all")
	private static void register(JsonConfig jsonConfig) {
		// 以下是注册Integer类型的，详细就不说了，和上面一样，如果有其他类型，就按照此方法在注册
		jsonConfig.registerDefaultValueProcessor(Integer.class,
				new DefaultDefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});
		jsonConfig.registerDefaultValueProcessor(Short.class,
				new DefaultDefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor());
	}
}

/**
 * 时间格式的处理
 * 
 */
class DateJsonValueProcessor implements JsonValueProcessor {
	public static final String Default_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private DateFormat dateFormat;

	public DateJsonValueProcessor() {
		dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		if (value == null) {
			return "";
		} else {
			return dateFormat.format(value);
		}
	}
}
