package com.shop.framework.utils;

import com.shop.framework.response.ResultEnum;
import com.shop.framework.response.ResultJson;

/**
 * 返回结果工具类
 * 
 * 
 */
@SuppressWarnings("all")
public class ResultUtil {

	public static ResultJson success(Object object) {
		ResultJson resultJson = new ResultJson();
		resultJson.setCode(ResultEnum.SUCCESS.getCode());
		resultJson.setMsg(ResultEnum.SUCCESS.getMsg());
		resultJson.setSuccess(Boolean.TRUE);
		resultJson.setData(object);
		return resultJson;
	}

	public static ResultJson success() {
		return success(null);
	}

	public static ResultJson error(Integer code, String msg) {
		ResultJson resultJson = new ResultJson();
		resultJson.setCode(code);
		resultJson.setMsg(msg);
		resultJson.setSuccess(Boolean.FALSE);
		return resultJson;
	}

	public static ResultJson error(Integer code, String msg, Object obj) {
		ResultJson resultJson = new ResultJson();
		resultJson.setCode(code);
		resultJson.setMsg(msg);
		resultJson.setSuccess(Boolean.FALSE);
		resultJson.setData(obj);
		return resultJson;
	}

	/**
	 * 服务器错误
	 */
	public static ResultJson serviceError() {
		return error(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getMsg());
	}

	/**
	 * 参数异常
	 */
	public static ResultJson parameterError() {
		return error(ResultEnum.PARAMETER_ERROR.getCode(), ResultEnum.PARAMETER_ERROR.getMsg());
	}

	/**
	 * 登录认证异常
	 */
	public static ResultJson loginError() {
		return error(ResultEnum.LONGIN_ERROR.getCode(), ResultEnum.LONGIN_ERROR.getMsg());
	}

	/**
	 * 服务没找到异常
	 */
	public static ResultJson notFoundError() {
		return error(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getMsg());
	}

	/**
	 * 构建简单的json串返回
	 * 
	 * @param success
	 * @return
	 */
	public static String buildResultJson(boolean success) {
		String result = success ? "success" : "fail";
		return "{\"result\":\"" + result + "\"}";
	}

	/**
	 * @param success
	 * @param code
	 *            1代表失败 0代表成功
	 * @return
	 */
	public static String buildResultJson(boolean success, Integer code) {
		String result = success ? "success" : "fail";
		return "{\"result\":\"" + result + "\",\"code\":" + code + "}";
	}
}
