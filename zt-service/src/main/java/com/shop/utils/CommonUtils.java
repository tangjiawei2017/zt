package com.shop.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.shop.vo.CustomFormCondition;

public class CommonUtils {
	public static final String WEI_XIN_BROWSER = "MicroMessenger/";
	public static final int WEI_XIN_LEGAL_VERSION = 5;

	/**
	 * @param length
	 *            表示生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成全数字类型指定长度的字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomDigitString(int length) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 字符串中数字用逗号隔开
	 */
	public static List<Integer> convertStringToListInt(String str) {
		List<Integer> list = new ArrayList<Integer>();
		String[] array = str.split(",");
		for (String s : array) {
			list.add(Integer.valueOf(s));
		}
		return list;
	}

	public static Integer[] convertStringToArrayInt(String str) {
		String[] strArray = str.split(",");
		Integer[] arr = new Integer[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			arr[i] = Integer.valueOf(strArray[i]);
		}
		return arr;
	}

	/**
	 * 字符串校验,防止sql注入
	 * 
	 * @return
	 */
	public static boolean isValid(String str) {
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
				+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(str).find()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断订单查询状态字符串参数是否合法
	 * 
	 * @param str
	 * @return
	 */
	public static boolean judgeOrderParamIsValid(String str) {
		String array[] = { "all", "waitpay", "waitdelivery", "hascomplete", "hascancel" };
		for (String a : array) {
			if (a.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断微信浏览器，微信浏览器版本要大于5
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isWeiXinBrowser(String userAgent) {
		// 判断是否为微信，并对支付渠道做相应处理
		// "Mozilla/5.0(iphone;CPU iphone OS 5_1_1 like Mac OS X)
		// AppleWebKit/534.46(KHTML,like Geocko) Mobile/9B206 MicroMessenger/5.0"
		// String userAgent = request.getHeader("user-agent");
		boolean isLegalVersion = false;
		if (StringUtils.isNotBlank(userAgent)) {
			// 判断是否为微信
			int position = userAgent.indexOf(WEI_XIN_BROWSER);
			userAgent.matches(WEI_XIN_BROWSER);
			if (position != -1) {
				String versionStr = userAgent.substring(position + WEI_XIN_BROWSER.length());
				String[] splitVersion = versionStr.split("\\.");

				// 程序解析
				if (splitVersion != null && splitVersion.length > 0) {
					String mainVersion = splitVersion[0];
					// 判断主版本号大于5
					if (NumberUtils.isNumber(mainVersion)) {
						int i = Integer.valueOf(mainVersion);
						if (i >= WEI_XIN_LEGAL_VERSION) {
							isLegalVersion = true;
						}
					}
				}
			}
		}
		return isLegalVersion;
	}

	/**
	 * 判断是不是手机浏览器打开
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isMobileBrowser(String userAgent) {
		boolean isLegalVersion = false;
		if (StringUtils.isNotBlank(userAgent)) {
			if (userAgent.indexOf("Android") != -1) {
				isLegalVersion = true;
			} else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPad") != -1) {
				isLegalVersion = true;
			}
		}
		return isLegalVersion;
	}

	public static String convertStreamToString(InputStream input) {
		StringBuffer sb = new StringBuffer();
		String str = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 将[12,23] 格式规格转换成 Array
	 * 
	 * @param str
	 * @return
	 */
	public static int[] convertStringTOArray(String str) {
		String[] array = null;
		int[] newArray = null;
		if (str.length() > 2) {
			String newString = str.substring(1, str.length() - 1);
			array = newString.split(",");
		}
		if (array != null) {
			newArray = new int[array.length];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = Integer.parseInt(array[i]);
			}
		}
		return newArray;
	}

	/**
	 * 时间筛选--字符串转换成时间格式 1 代表今天 ,2代表昨天,3代表本周,4代表本月 5代表本季度
	 * 
	 * @param ctime
	 * @param condition
	 */
	public static void convertStringToDate(String ctime, CustomFormCondition condition) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = null;
		String endTime = null;
		if ("1".equals(ctime)) {
			startTime = format.format(DateUtils.getDayBegin());
			endTime = format.format(DateUtils.getDayEnd());
		} else if ("2".equals(ctime)) {
			startTime = format.format(DateUtils.getBeginDayOfYesterday());
			endTime = format.format(DateUtils.getEndDayOfYesterDay());
		} else if ("3".equals(ctime)) {
			startTime = format.format(DateUtils.getBeginDayOfWeek());
			endTime = format.format(DateUtils.getEndDayOfWeek());
		} else if ("4".equals(ctime)) {
			startTime = format.format(DateUtils.getBeginDayOfMonth());
			endTime = format.format(DateUtils.getEndDayOfMonth());
		} else if ("5".equals(ctime)) {
			startTime = format.format(DateUtils.getCurrentQuarterStartTime());
			endTime = format.format(DateUtils.getCurrentQuarterEndTime());
		} else if (ctime.indexOf("~") != -1) {
			startTime = ctime.substring(0, ctime.indexOf("~"));
			endTime = ctime.substring(ctime.indexOf("~") + 1, ctime.length());
		}
		condition.setStartTime(startTime);
		condition.setEndTime(endTime);
	}
}
