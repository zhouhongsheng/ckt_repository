package com.sioeye.log;

import org.apache.commons.logging.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * author zhouyou ckt email:jinx.zhou@ck-telecom.com 2017��6��13�� SioeyeLog.java
 * description sioeye��־��ӡ��
 */
public class SioeyeLog {

    /**
     * �ɹ���־
     * 
     * @param logger
     * @param object
     *            ����
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
     * �쳣��־
     * 
     * @param logger
     * @param object
     *            �쳣
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
            // ��־��ӡ��ϵͳ����
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
     * ������־
     * 
     * @param logger
     * @param object
     *            �����ַ�������
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
