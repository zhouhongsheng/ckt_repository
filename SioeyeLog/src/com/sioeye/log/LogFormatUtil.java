package com.sioeye.log;

import java.util.LinkedHashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

/**
 * 日志格式化输出工具
 * 
 * @author xiaodong.su
 *
 */
public class LogFormatUtil {
    /**
     * 业务异常
     * 
     * @param exception
     * @return
     */
    public static String businessException(CustomSuperException exception) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "businessException");
        map.put("code", exception.getCode());
        map.put("message", exception.getMessage());
        return JSON.toJSONString(map);
    }

    /**
     * 内部异常
     * 
     * @param e
     * @return
     */
    public static String internalException(Throwable e) {
        StackTraceElement[] elements = e.getStackTrace();
        StackTraceElement element = null;
        for (StackTraceElement se : elements) {
            String className = se.getClassName();
            if (className.indexOf(".sioeye") != -1) {
                element = se;
                break;
            }
        }
        if (element == null) {
            element = elements[elements.length - 1];
        }
        Map<String, Object> map = new LinkedHashMap<>();
        String message = e.getMessage();
        if (LogFormatUtil.isStringEmpty(message)) {
            message = e.getLocalizedMessage();
        }
        map.put("type", "internalException");
        map.put("message", message);
        map.put("class", element.getClassName());
        map.put("method", element.getMethodName());
        map.put("lineNumber", element.getLineNumber());

        return JSON.toJSONString(map);
    }

    /**
     * 业务错误
     * 
     * @param obejct
     * @return
     */
    public static String businessError(String obejct) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "businessError");
        map.put("message", obejct);
        return JSON.toJSONString(map);
    }

    /**
     * 
     * @param str
     * @return
     */
    private static boolean isStringEmpty(String str) {
        return (str == null || "".equals(str.trim()));
    }
}
