package com.sioeye.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author zhouyou
 * @cktEmail:jinx.zhou@ck-telecom.com
 * @date 2018年2月24日
 * @fieleName SioeyeUtil.java
 * @TODO sioeye工具类
 */
public class SioeyeUtil {

    /**
     * 判断对象是否为空
     * 
     * @param 需要判断的对象
     * @return 是否为空
     */
    public static boolean objectIsEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return "".equals(object);
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (object instanceof Collection) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map<?, ?>) object).isEmpty();
        }
        if (object instanceof Object[]) {
            Object[] objects = (Object[]) object;
            if (objects.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < objects.length; i++) {
                if (!objectIsEmpty(objects[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 从指定的基础数据中生成指定位数的随机字符串
     * 
     * @param 基础字符串数据
     * @param 需要生成的随机字符串位数
     * @return 生成的随机字符串
     */
    public static String generateDigitStr(String str, int digit) {
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            int number = random.nextInt(str.length());
            result.append(str.charAt(number));
        }
        return result.toString();
    }

    /**
     * 从英文字母和数字的组合中取出指定位数的随机字符串
     * 
     * @param 指定的位数
     * @return 生成的随机字符串
     */
    public static String generateDigitStr(int digit) {
        String str = "ABCDEFGHIJKLMNOPQRSTYVWXYZ0123456789";
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            int number = random.nextInt(str.length());
            result.append(str.charAt(number));
        }
        return result.toString();
    }

    /**
     * 从英文字符取出指定位数的随机字符串
     * 
     * @param 指定的位数
     * @return 生成的随机字符串
     */
    public static String generateDigitChars(int digit) {
        String str = "ABCDEFGHIJKLMNOPQRSTYVWXYZ";
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            int number = random.nextInt(str.length());
            result.append(str.charAt(number));
        }
        return result.toString();
    }

    /**
     * 从整数中取出指定位数的随机字符串
     * 
     * @param 指定的位数
     * @return 生成的随机字符串
     */
    public static String generateDigitInt(int digit) {
        String str = "0123456789";
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            int number = random.nextInt(str.length());
            result.append(str.charAt(number));
        }
        return result.toString();
    }

    /**
     * 判断对象是否是数字
     * 
     * @param 需要验证的对象
     * @return 是否是数字
     */
    public static boolean isNumber(Object object) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher isNum = pattern.matcher(object.toString());
        if (isNum.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否是字符串
     * 
     * @param object
     * @return
     */
    public static boolean isString(Object object) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher isNum = pattern.matcher(object.toString());
        if (isNum.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 验证对象是否是正整数
     * 
     * @param 需要验证的对象
     * @return 是否为正整数
     */
    public static boolean isInt(Object object) {
        Pattern pattern = Pattern.compile("^[1-9]+[0-9]*$");
        Matcher isNum = pattern.matcher(object.toString());
        if (!isNum.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 匹配电话号码
     *
     * @param 电话号码
     * @param 环境(cn:中国us:美国)
     * @return 是否是电话号码
     */
    public static boolean matcherPhoneNum(String phoneNum, String env) {
        Pattern p = null;
        if ("cn".equals(env)) {
            p = Pattern.compile("^((\\+86)|(86))?[1][3456789]\\d{9}$");
        } else if ("us".equals(env)) {
            p = Pattern.compile("^(\\+?1)?[2-9]\\d{2}[2-9](?!11)\\d{6}$");
        } else {
            return false;
        }
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }

    /**
     * 匹配身份证名称
     * 
     * @param 身份证名称
     * @return 是否身份证名称
     */
    public static boolean matchCertName(String certName) {
        boolean result = false;
        Pattern idNumPattern = Pattern
                .compile("[\u4e00-\u9fa5]{1}[\u4e00-\u9fa5\\.]{0,19}|([a-zA-Z]{1}[a-zA-Z\\.]{0,49})");
        // 匹配模型
        Matcher idNumMatcher = idNumPattern.matcher(certName);
        if (idNumMatcher.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * 匹配身份证号码,年份不能小于1900且不大于2099
     * 
     * @param 身份证号码
     * @return 是否是身份证号码
     */
    public static boolean matchCertNo(String certNo) {
        boolean result = false;
        // 将证件号码转化为字符对象
        char[] chars = certNo.toCharArray();
        StringBuffer year = new StringBuffer();
        StringBuffer month = new StringBuffer();
        StringBuffer day = new StringBuffer();
        // 匹配18位的正则表达式
        Pattern idNumPattern = Pattern.compile("([1-9]\\d{16}[0-9xX])");
        Matcher idNumMatcher = idNumPattern.matcher(certNo);
        // 判断用户输入是否为身份证号
        if (idNumMatcher.matches()) {
            for (int i = 6; i < 14; i++) {
                if (i < 10) {
                    year.append(chars[i]);
                    continue;
                }
                if (i < 12) {
                    month.append(chars[i]);
                    continue;
                }
                if (i < 14) {
                    day.append(chars[i]);
                    continue;
                }
            }
            // 判断年月是否正确
            if (Integer.parseInt(year.toString()) < 1900 || Integer.parseInt(year.toString()) > 2099) {
                result = false;
                return result;
            }
            idNumPattern = Pattern.compile("(0[1-9]|1[0-2])");
            idNumMatcher = idNumPattern.matcher(month.toString());
            if (!idNumMatcher.matches()) {
                result = false;
                return result;
            }
            idNumPattern = Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1])");
            idNumMatcher = idNumPattern.matcher(day.toString());
            if (!idNumMatcher.matches()) {
                result = false;
                return result;
            }
            result = true;
            return result;
        }
        // 匹配15位的正则表达式
        idNumPattern = Pattern.compile("([1-9]\\d{13}[0-9xX])");
        idNumMatcher = idNumPattern.matcher(certNo);
        if (idNumMatcher.matches()) {
            for (int i = 6; i < 12; i++) {
                if (i < 8) {
                    year.append(chars[i]);
                    continue;
                }
                if (i < 10) {
                    month.append(chars[i]);
                    continue;
                }
                if (i < 12) {
                    day.append(chars[i]);
                    continue;
                }
            }
            // 判断年月是否正确
            /*
             * 15位身份证号不需要验证年 if (Integer.parseInt(year.toString()) < 17) {
             * result = false; return result; }
             */
            idNumPattern = Pattern.compile("(0[1-9]|1[0-2])");
            idNumMatcher = idNumPattern.matcher(month.toString());
            if (!idNumMatcher.matches()) {
                result = false;
                return result;
            }
            idNumPattern = Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1])");
            idNumMatcher = idNumPattern.matcher(day.toString());
            if (!idNumMatcher.matches()) {
                result = false;
                return result;
            }
            result = true;
            return result;
        }
        return result;
    }

    /**
     * 字符串转时间
     * 
     * @param 需要转换的字符串时间
     * @param 转换格式
     * @return 时间
     * @throws ParseException
     */
    public static Date str2Date(String str, String format) throws ParseException {
        // 需要转换的字符串为空
        if (SioeyeUtil.objectIsEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    /**
     * 时间转字符串
     * 
     * @param 时间
     * @param 需要转换的格式
     * @return 转换后的时间
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将10、13位的时间戳转换为时间
     * 
     * @param 时间戳
     * @param 转换的格式
     * @return 转换后的时间
     * @throws ParseException
     */
    public static Date timestamp2Date(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (str.length() == 13) {
            Long time = new Long(Long.parseLong(str));
            String d = sdf.format(time);
            Date date = sdf.parse(d);
            return date;
        } else {
            Long time = new Long(Long.parseLong(str) * 1000L);
            String d = sdf.format(time);
            Date date = sdf.parse(d);
            return date;
        }
    }

    /**
     * 从指定范围内获取随机值
     * 
     * @param 指定范围的最小值
     * @param 指定范围的最大值
     * @return 生成的随机值
     */
    public static long generateRandomByRange(long min, long max) {
        long result = (long) (min + Math.random() * (max - min + 1));
        return result;
    }

    /**
     * 获取字符串的MD5加密值
     * 
     * @param 需要加密的字符串
     * @return 加密后的返回值
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String generateMD5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        // MessageDigest md = MessageDigest.getInstance("MD5");
        // // 计算md5函数
        // md.update(str.getBytes());
        // // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        //// md.digest().toString();
        // byte[] bs = md.digest();
        // StringBuffer sb = new StringBuffer(bs.length * 2);
        // for (int i = 0; i < bs.length; i++) {
        // sb.append(Character.forDigit((bs[i] & 240) >> 4, 16));
        // sb.append(Character.forDigit(bs[i] & 15, 16));
        // }
        MessageDigest md = MessageDigest.getInstance("MD5"); // 创建一个md5算法对象
        byte[] messageByte = str.getBytes("UTF-8");
        byte[] md5Byte = md.digest(messageByte); // 获得MD5字节数组,16*8=128位
        String md5 = bytesToHex(md5Byte);
        return md5;
    }

    // 二进制转十六进制
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString();
    }

    /**
     * SHA256加密算法
     * 
     * @param original
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSHA256(String original) throws NoSuchAlgorithmException {
        if (original != null && !"".equals(original)) {
            MessageDigest md = null;
            md = MessageDigest.getInstance("SHA-256");
            if (null != md) {
                byte[] origBytes = original.getBytes();
                md.update(origBytes);
                byte[] digestRes = md.digest();
                StringBuilder stb = new StringBuilder();
                String tempStr = null;
                for (int i = 0; i < digestRes.length; i++) {
                    // 这里按位与是为了把字节转整时候取其正确的整数，Java中一个int是4个字节
                    // 如果origBytes[i]最高位为1，则转为int时，hint的前三个字节都被1填充了
                    tempStr = Integer.toHexString(origBytes[i] & 0xff);
                    if (tempStr.length() == 1) {
                        stb.append("0");
                    }
                    stb.append(tempStr);
                }
                return stb.toString();
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * 将字符串里面指定的字符替换为新的字符
     * 
     * @param 字符串
     * @param 需要替换的字符串
     * @param 新的字符串
     * @return 替换完成后的新字符串
     */
    public static String replaceStr(String str, String old, String now) {
        if (SioeyeUtil.objectIsEmpty(str)) {
            return null;
        }
        return str.replaceAll(old, now);
    }

    /**
     * 获取堆积UUID 返回值为大写
     * 
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
