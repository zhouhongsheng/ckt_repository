package com.sioeye.log;

/**
 * author zhouyou ckt email:jinx.zhou@ck-telecom.com 2017��6��14��
 * CustomSuperException.java description �쳣����
 */
public class CustomSuperException extends RuntimeException {

    protected static final long serialVersionUID = 1L;
    protected Integer code;// ������
    protected String message;// ������Ϣ

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
