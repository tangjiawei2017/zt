package com.shop.framework.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller 全局异常处理类
 *
 */
@SuppressWarnings("all")
@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理所有Controller异常
	 * 
	 * @param request
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
		logger.error("exception", e);
		String returnMsg = "失败";
		// 创建返回对象,并转换成 json格式
		if (isAjax(request)) {
			if (e instanceof BindException) {
				// 参数绑定异常 注意 BindException 是spring校验异常
				returnMsg = "参数绑定异常";
			}
			return "{\"result\":\"fail\",\"returnMsg\":returnMsg}";
		} else {
			// 非ajax请求,返回到错误页面
			if (e instanceof HttpRequestMethodNotSupportedException) {
				logger.error("exception====>不支持该请求方式");
			}
			return "error";
		}
	}

	/**
	 * 判断是否为ajax请求
	 * 
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
		String param = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(param))
			return true;
		else
			return false;
	}
}
