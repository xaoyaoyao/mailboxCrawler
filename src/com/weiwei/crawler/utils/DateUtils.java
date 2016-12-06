/**   
 * @Title: DateUtils.java 
 * @Package com.enchantin.endzone.util 
 * @Description: TODO
 * @author weiwei
 * @date 2015-4-17 下午8:10:42 
 * @version V1.0   
 */
package com.weiwei.crawler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weiwei
 * @Date 2015-4-17
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");

	private final static SimpleDateFormat longFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getSimpleDate(Date date) {
		if (null == date) {
			return null;
		}
		return longFormatDate.format(date);
	}

	public static String getSimpleDate2(Date date) {
		if (null == date) {
			return null;
		}
		return dateFormat.format(date);
	}

	public static String getLongDate(Date date) {
		if (null == date) {
			return null;
		}
		return longFormatDate.format(date);
	}
}