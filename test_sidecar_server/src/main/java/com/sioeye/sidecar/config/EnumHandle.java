package com.sioeye.sidecar.config;

/**
 * author zhouyou 
 * ckt email:jinx.zhou@ck-telecom.com
 * 2017年5月26日
 * EnumHandle.java 
 * description 错误枚举类
 */
public enum EnumHandle {

	CALL_RIGHT_UPDATE_FAILED(2001,"call right server to update right is failed . "),
	CALL_PRODUCTS_QUERY_FAILED(2002,"call products manage server query products is failed . "),
	PRICE_NOT_IDENTITY(2003,"orders price is not identity . "),
	CALL_PAY_PAY_FAILED(2004,"call pay server to pay is failed . "),
	INSERT_ORDER_FAILED(2005,"insert orders is failed . "),
	INSERT_STATUS_FAILED(2006,"insert status is failed . "),
	UPDATE_ORDERS_FAILED(2007,"update orders is failed . "),
	QUERY_ORDERS_FAILED(2008,"query orders is failed . "),
	QUERY_ORDERS_LIST_FAILED(2009,"query orders list is failed . "),
	CALL_QUERY_PAY_FAILED(2010,"call query pay is failed . "),
	HTTP_URL_IS_INCORRECT(2011,"request url is incorrect . "),
	PLACE_ORDER_PARAM_INCORRECT(2012,"place order params is incorrect"),
	CALL_APPLE_PAY_FAILED(2013,"call pay server to query ios server receipt is failed . "),
	PROMOTION_PRICE_GREATER_ACTUAL_PRICE(2014,"promotion price greater actual price . "),
	PLACE_ORDERS_PARAM_INCORRECT(2015,"place orders params is incorrect . "),
	QUERY_ORDERS_PARAM_INCORRECT(2016,"query orders params is incorrect . "),
	QUERY_ORDERS_LIST_PARAM_INCORRECT(2017,"query orders list params is incorrect . "),
	PROMOTION_PRICE_IS_INCORRECT(2018,"promotions total price is incorrect . "),
	COMPENSATE_PAY_FAIL(2019,"compensate payment is fail . "),
	DELETE_ORDERS_FAILED(2020,"delete orders fail . "),
	UPDATE_ORDERS_PARAMS_INCORRECT(2021,"update orders params is empty . "),
	STATUS_IS_INCORRECT(2022,"status is incorrect . "),
	PARAMS_INCORRECT(2023,"params is incorrect . "),
	SEND_CAPTCHA_FAILURE(2024,"call netease send captcha is failure . "),
	CREATE_COUPON_FAILURE(2025,"create coupon is failure . "),
	NOT_SEND_CAPTCHA(2026,"the phone number did not send captcha . "),
	CAPTCHA_INCORRECT(2027,"captcha is incorrect . "),
	COUPON_INCORRECT(2028,"coupon is incorrect . "),
	UPDATE_COUPON_FAILURE(2029,"update coupon is failure . "),
	COUPONTYPE_INCORRECT(2030,"coupontype is incorrect . "),
	COUPON_ALREADY_USED(2031,"the coupon has used . "),
	COUPON_HAVE_EXPIRED(2032,"the coupon have expired . "),
	PHONENUM_BAD_FORMAT(2033,"the phone number is bad format ! "),
	EXCHANGE_COUPON_FAILURE(2034,"exchange coupon is failure . "),
	EXPIRATIONDATE_INCORRECT(2035,"expirationDate is incorrect . "),
	ERROR_ENVIRONMENT(2036,"environment is error . "),
	TRADE_TYPE_INCORRECT(2037,"tradeType is error . "),
	ORDER_TYPE_INCORRECT(2038,"order type is error . "),
	ORDER_NOT_EXISIT(2039,"order is not exisit . "),
	ORDER_HAS_BEEN_PAID(2040,"The order has been paid"),
	CALL_FACE_UPDATE_ORDER_FAILURE(2041,"cala face weixin server to update order is failed . "),
	ORDER_MULTIPLE(2042,"There are multiple orders"),
	COMPANY_PAY_FAIL(2043,"call company pay is failure . "),
	CALL_APP_SERVER_PAY_FAIL(2044,"call app server user to purchase video failed . ");

	private Integer code;
	private String message;

	EnumHandle(Integer code, String message) {
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
