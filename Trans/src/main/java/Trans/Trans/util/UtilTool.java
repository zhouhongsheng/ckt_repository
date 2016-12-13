package Trans.Trans.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * title UtilTool description 工具类
 * 
 * @author zhouyou 15328022010
 * @date 2016年11月11日
 * 
 */
public class UtilTool {

	private static final Logger logger = LoggerFactory.getLogger(UtilTool.class);

	/*
	 * @param count 需要产生的数组个数
	 * 
	 * @param min 范围最小值
	 * 
	 * @param max 范围最大值 int[] 返回一个数组 TODO 通过传入的取值返回，获取一个数组
	 */
	public static int[] getRandomNumArrayByRange(int count, int min, int max) {
		int[] resultArray = new int[count];
		try {
			int result = 0;
			if (min > max || max < min || count < 0) {
				throw new Exception("传入的参数不符合要求.");
			} else {
				for (int i = 0; i < resultArray.length; i++) {
					// 产生min~max的整数
					result = (new Random().nextInt(max - min)) + min;
					if (result < min || result > max) {
						throw new Exception("数据产生不符合范围规则.");
					} else {
						resultArray[i] = result;
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return resultArray;
	}

	/*
	 * 
	 * @param min
	 * 
	 * @param max void TODO 取得一个指定范围内的随机整数值,如果min=max则返回max
	 */
	public static int getRandomNumByRange(int min, int max) {
		int result = 0;
		try {
			if (min > max || max < min) {
				throw new Exception("传入的参数最小值和最大值不符合要求.");
			} else {
				if (max == min) {
					result = max;
				} else {
					// 产生min~max的整数
					result = (new Random().nextInt(max - min)) + min;
					if (result > max || result < min) {
						throw new Exception("数据产生不符合范围规则.");
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}

	/*
	 * 
	 * @return String TODO 将日期装换为yyyy-MM-dd标准的日期格式字符串
	 */
	public static String convertDate(String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(new Date());
	}

	/*
	 * 将字符串型日期转换为日期型
	 * 
	 * @param strDate 字符串型日期 如果为空，取当前日期时间
	 * 
	 * @param dstDateFormat 目标日期格式
	 * 
	 * @return Date 返回的util.Date型日期
	 */
	public static Date stringToDate(String strDate, String dstDateFormat) {
		Date rtDate = null;
		try {
			if (!"".equals(strDate) && strDate != null) {
				rtDate = new SimpleDateFormat(dstDateFormat).parse(strDate);
			} else {
				rtDate = new SimpleDateFormat(dstDateFormat).parse(convertDate(dstDateFormat));
			}
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return rtDate;
	}

	/*
	 * 
	 * @param date
	 * 
	 * @param plusNum
	 * 
	 * @return Date TODO 为指定时间加上一个指定的时间，date可以为空，为空获取当前时间。
	 */
	public static Date plusTimeForDate(Date date, long plusNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null;// 需要处理的时间
		if (date != null && !"".equals(date)) {
			now = date;
		} else {
			// 没有传值，获取当前时间
			now = new Date();
		}
		Date afterDate = new Date(now.getTime() + plusNum);
		try {
			afterDate = sdf.parse(sdf.format(afterDate));
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return afterDate;
	}

	/*
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return long TODO 获取随机休眠时间
	 */
	public static long getRandomTimeByRange(long min, long max) {
		long result = (long) (min + Math.random() * (max - min + 1));
		return result;
	}

	/**
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			// md.digest().toString();
			byte[] bs=md.digest();
			StringBuffer sb = new StringBuffer(bs.length * 2); 
	        for (int i = 0; i < bs.length; i++) { 
	            sb.append(Character.forDigit((bs[i] & 240) >> 4, 16)); 
	            sb.append(Character.forDigit(bs[i] & 15, 16)); 
	        } 
	        
//	        return new BigInteger(1, bs).toString(16);
			return sb.toString();
		} catch (Exception e) {
			logger.error("What? MD加密失败");
		}

		return null;
	}
	
	/**
	 * @param timestampString
	 * @return
	 */
	public static Date TimeStamp2UTCDate(String timestampString) {
		Long timestamp = Long.parseLong(timestampString)*1000;
		return new java.util.Date(timestamp);
	}


	/**
	 * @param timestampString
	 * @return
	 */
	public static String TimeStamp2DefaultDateStr(String timestampString) {
		return TimeStamp2DateStr(timestampString,  "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param timestampString
	 * @param formats
	 * @return
	 */
	public static String TimeStamp2DateStr(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
		return date;
	}
	
}
