package com.shop.framework.response;

/**
 * 规范Json格式
 * 
 * @param <T>
 */
public class ResultJson<T> {

	// 状态码
	private Integer code;
	// 返回信息描述
	private String msg;
	// 是否成功
	private boolean success;
	// 数据条数
	private Integer count;
	// 返回数据
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
