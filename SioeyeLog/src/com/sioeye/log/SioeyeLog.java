package com.sioeye.log;

import org.apache.commons.logging.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * author zhouyou ckt email:jinx.zhou@ck-telecom.com 2017年6月13日 SioeyeLog.java
 * description sioeye日志打印类
 */
public class SioeyeLog {

    /**
     * 成功日志
     * 
     * @param logger
     * @param object
     *            对象
     * @return
     */
    public static String packageSuccessLog(Log logger, Object object) {
        String result = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        if (object != null) {
            jsonObject.put("value", "{#value}");
            String jsonStr = JSON.toJSONString(object);
            result = jsonObject.toString().replace("\"{#value}\"", jsonStr);
        } else {
            result = jsonObject.toString();
        }
        logger.info(result);
        return result;
    }

    /**
     * 异常日志
     * 
     * @param logger
     * @param object
     *            异常
     * @return
     */
    public static String packageExceptionLog(Log logger, Object object) {
        String result = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", false);
        if (object instanceof CustomSuperException) {
            CustomSuperException exception = (CustomSuperException) object;
            String jsonStr = LogFormatUtil.businessException(exception);
            jsonObject.put("value", "{#value}");
            result = jsonObject.toString().replace("\"{#value}\"", jsonStr);
        } else if (object instanceof Exception) {
            // 日志打印出系统错误
            Exception exception = (Exception) object;
            String jsonStr = LogFormatUtil.internalException(exception);
            jsonObject.put("value", "{#value}");
            result = jsonObject.toString().replace("\"{#value}\"", jsonStr);
        } else {
            jsonObject.put("value", "object not belong to exception");
            result = jsonObject.toString();
        }
        logger.info(result);
        return result;
    }

    /**
     * 错误日志
     * 
     * @param logger
     * @param object
     *            错误字符串对象
     * @return
     */
    public static String packageErrorLog(Log logger, String object) {
        String result = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", false);
        if (object != null && !"".equals(object)) {
            jsonObject.put("value", "{#value}");
            String jsonStr = LogFormatUtil.businessError(object);
            result = jsonObject.toString().replace("\"{#value}\"", jsonStr);
        } else {
            result = jsonObject.toString();
        }
        logger.info(result);
        return result;
    }
}
