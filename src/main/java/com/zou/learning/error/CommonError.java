package com.zou.learning.error;

public interface CommonError {
	
	public Integer getErrCode();

	public String getErrMsg();

	public CommonError setErrMsg(String errMsg);

}