package com.sioeye.sidecar.config;

/**
 * author zhouyou ckt email:jinx.zhou@ck-telecom.com 2017年5月26日
 * CustomException.java description 自定义异常类
 */

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer code;
	private String message;

	public CustomException(EnumHandle enumHandle) {
		this.code = enumHandle.getCode();
		this.message = enumHandle.getMessage();
	}

	public CustomException(Integer code, String messge) {
		this.code = code;
		this.message = messge;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
