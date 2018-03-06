package com.sioeye.log;

/**
 * author zhouyou ckt email:jinx.zhou@ck-telecom.com 2017年6月14日
 * CustomSuperException.java description 异常父类
 */
public class CustomSuperException extends RuntimeException {

    protected static final long serialVersionUID = 1L;
    protected Integer code;// 错误码
    protected String message;// 错误消息

    public CustomSuperException() {

    }

    public CustomSuperException(Integer code, String message) {
        this.code = code;
        this.message = message;
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
