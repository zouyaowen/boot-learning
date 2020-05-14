package com.zou.learning.error;

public enum BusinessError implements CommonError {
	// 参数错误code统一返回-1
	REQUEST_PERAM_INVALIDED(-1, "请求参数不正确"),

	// 没有错误code为200
	// 标准的HTTP请求错误码
	USER_REQUEST_UNAUTHORIZED(401, "用户请求未授权"),

	REQUEST_URL_NOT_EXIST(404, "请求路由不存在"),

	REQUEST_METHOD_NOT_ALLOWED(405, "请求方法错误"),

	// 5开头的为未知错误
	UNKNOW_ERROR(500, "系统内部错误"),

	;
	private Integer errCode;
	private String errMsg;

	private BusinessError(Integer errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	@Override
	public Integer getErrCode() {
		return this.errCode;
	}

	@Override
	public String getErrMsg() {
		return this.errMsg;
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}
}