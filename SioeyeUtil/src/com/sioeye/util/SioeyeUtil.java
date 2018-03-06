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
 * @date 2018��2��24��
 * @fieleName SioeyeUtil.java
 * @TODO sioeye������
 */
public class SioeyeUtil {

    /**
     * �ж϶����Ƿ�Ϊ��
     * 
     * @param ��Ҫ�жϵĶ���
     * @return �Ƿ�Ϊ��
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
     * ��ָ���Ļ�������������ָ��λ��������ַ���
     * 
     * @param �����ַ�������
     * @param ��Ҫ���ɵ�����ַ���λ��
     * @return ���ɵ�����ַ���
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
     * ��Ӣ����ĸ�����ֵ������ȡ��ָ��λ��������ַ���
     * 
     * @param ָ����λ��
     * @return ���ɵ�����ַ���
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
     * ��Ӣ���ַ�ȡ��ָ��λ��������ַ���
     * 
     * @param ָ����λ��
     * @return ���ɵ�����ַ���
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
     * ��������ȡ��ָ��λ��������ַ���
     * 
     * @param ָ����λ��
     * @return ���ɵ�����ַ���
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
     * �ж϶����Ƿ�������
     * 
     * @param ��Ҫ��֤�Ķ���
     * @return �Ƿ�������
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
     * �ж϶����Ƿ����ַ���
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
     * ��֤�����Ƿ���������
     * 
     * @param ��Ҫ��֤�Ķ���
     * @return �Ƿ�Ϊ������
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
     * ƥ��绰����
     *
     * @param �绰����
     * @param ����(cn:�й�us:����)
     * @return �Ƿ��ǵ绰����
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
     * ƥ�����֤����
     * 
     * @param ���֤����
     * @return �Ƿ����֤����
     */
    public static boolean matchCertName(String certName) {
        boolean result = false;
        Pattern idNumPattern = Pattern
                .compile("[\u4e00-\u9fa5]{1}[\u4e00-\u9fa5\\.]{0,19}|([a-zA-Z]{1}[a-zA-Z\\.]{0,49})");
        // ƥ��ģ��
        Matcher idNumMatcher = idNumPattern.matcher(certName);
        if (idNumMatcher.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * ƥ�����֤����,��ݲ���С��1900�Ҳ�����2099
     * 
     * @param ���֤����
     * @return �Ƿ������֤����
     */
    public static boolean matchCertNo(String certNo) {
        boolean result = false;
        // ��֤������ת��Ϊ�ַ�����
        char[] chars = certNo.toCharArray();
        StringBuffer year = new StringBuffer();
        StringBuffer month = new StringBuffer();
        StringBuffer day = new StringBuffer();
        // ƥ��18λ��������ʽ
        Pattern idNumPattern = Pattern.compile("([1-9]\\d{16}[0-9xX])");
        Matcher idNumMatcher = idNumPattern.matcher(certNo);
        // �ж��û������Ƿ�Ϊ���֤��
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
            // �ж������Ƿ���ȷ
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
        // ƥ��15λ��������ʽ
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
            // �ж������Ƿ���ȷ
            /*
             * 15λ���֤�Ų���Ҫ��֤�� if (Integer.parseInt(year.toString()) < 17) {
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
     * �ַ���תʱ��
     * 
     * @param ��Ҫת�����ַ���ʱ��
     * @param ת����ʽ
     * @return ʱ��
     * @throws ParseException
     */
    public static Date str2Date(String str, String format) throws ParseException {
        // ��Ҫת�����ַ���Ϊ��
        if (SioeyeUtil.objectIsEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    /**
     * ʱ��ת�ַ���
     * 
     * @param ʱ��
     * @param ��Ҫת���ĸ�ʽ
     * @return ת�����ʱ��
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * ��10��13λ��ʱ���ת��Ϊʱ��
     * 
     * @param ʱ���
     * @param ת���ĸ�ʽ
     * @return ת�����ʱ��
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
     * ��ָ����Χ�ڻ�ȡ���ֵ
     * 
     * @param ָ����Χ����Сֵ
     * @param ָ����Χ�����ֵ
     * @return ���ɵ����ֵ
     */
    public static long generateRandomByRange(long min, long max) {
        long result = (long) (min + Math.random() * (max - min + 1));
        return result;
    }

    /**
     * ��ȡ�ַ�����MD5����ֵ
     * 
     * @param ��Ҫ���ܵ��ַ���
     * @return ���ܺ�ķ���ֵ
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String generateMD5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // ����һ��MD5���ܼ���ժҪ
        // MessageDigest md = MessageDigest.getInstance("MD5");
        // // ����md5����
        // md.update(str.getBytes());
        // // digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
        // // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
        //// md.digest().toString();
        // byte[] bs = md.digest();
        // StringBuffer sb = new StringBuffer(bs.length * 2);
        // for (int i = 0; i < bs.length; i++) {
        // sb.append(Character.forDigit((bs[i] & 240) >> 4, 16));
        // sb.append(Character.forDigit(bs[i] & 15, 16));
        // }
        MessageDigest md = MessageDigest.getInstance("MD5"); // ����һ��md5�㷨����
        byte[] messageByte = str.getBytes("UTF-8");
        byte[] md5Byte = md.digest(messageByte); // ���MD5�ֽ�����,16*8=128λ
        String md5 = bytesToHex(md5Byte);
        return md5;
    }

    // ������תʮ������
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
     * SHA256�����㷨
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
                    // ���ﰴλ����Ϊ�˰��ֽ�ת��ʱ��ȡ����ȷ��������Java��һ��int��4���ֽ�
                    // ���origBytes[i]���λΪ1����תΪintʱ��hint��ǰ�����ֽڶ���1�����
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
     * ���ַ�������ָ�����ַ��滻Ϊ�µ��ַ�
     * 
     * @param �ַ���
     * @param ��Ҫ�滻���ַ���
     * @param �µ��ַ���
     * @return �滻��ɺ�����ַ���
     */
    public static String replaceStr(String str, String old, String now) {
        if (SioeyeUtil.objectIsEmpty(str)) {
            return null;
        }
        return str.replaceAll(old, now);
    }

    /**
     * ��ȡ�ѻ�UUID ����ֵΪ��д
     * 
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
