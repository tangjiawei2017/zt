package com.shop.framework.response;

public enum ResultEnum {
	NODATA(0, "无数据"), ERROR(-1, "请求失败"), SUCCESS(200, "请求成功"), PARAMETER_ERROR(400, "参数错误"), SERVER_REFUSE_NMOBILE(401,
			"请使用手机访问"), SERVER_REFUSE_NWECHAT(402,
					"请用微信打开"), LONGIN_ERROR(403, "登录认证异常"), NOT_FOUND(404, "未找到服务"), SERVER_ERROR(500, "服务异常");

	private Integer code;
	private String msg;

	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
