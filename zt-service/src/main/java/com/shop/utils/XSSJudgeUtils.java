package com.shop.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串防止SQL注入校验
 * 
 */
public class XSSJudgeUtils {
	public static boolean isSqlInject(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		str = str.toLowerCase();// 统一转为小写
		String badStr = "'|and|exec|script|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|;|or|+|,|like'|and|exec|execute|insert|create|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
				+ "chr|mid|master|truncate|char|declare|or|;|--|+|,|like|//|/|%|#|CR|LF|document|eval|iframe";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

}
