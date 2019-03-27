package com.jsh.erp.utils;

public class BaseResponseInfo {

    /** 状态码*/
	public int code;

	/** 状态信息*/
	public Object data;
	
	public BaseResponseInfo() {
		code = 400;
		data = null;
	}
}
