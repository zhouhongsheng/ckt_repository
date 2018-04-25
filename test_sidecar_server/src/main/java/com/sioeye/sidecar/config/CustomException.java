package com.sioeye.sidecar.config;

import com.sioeye.log.CustomSuperException;

/**
 * author zhouyou 
 * ckt email:jinx.zhou@ck-telecom.com
 * 2017年5月26日
 * CustomException.java 
 * description 自定义异常类
 */

public class CustomException extends CustomSuperException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(EnumHandle enumHandle) {
		super.code = enumHandle.getCode();
		super.message = enumHandle.getMessage();
	}
	public CustomException(Integer code,String messge){
		super.code = code;
		super.message = messge;
	}
}
