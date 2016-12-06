/**     
 * @Title: StringUtils.java   
 * @Package com.weiwei.crawler.utils   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年12月1日 下午12:15:10   
 * @version V1.0     
 */
package com.weiwei.crawler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.CharUtils;

/**
 * @ClassName: StringUtils
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:15:10
 * 
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * @Title: strRegex
	 * @Description: TODO
	 * @param regEx
	 * @param str
	 * @return boolean
	 */
	public static boolean strRegex(String regEx, String str) {
		if (isBlank(regEx) || isBlank(str)) {
			return false;
		}
		try {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			return m.find();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Title: strRegex2
	 * @Description: TODO
	 * @param regEx
	 * @param str
	 * @return String
	 */
	public static String strRegex2(String regEx, String str) {
		if (isBlank(regEx) || isBlank(str)) {
			return null;
		}
		try {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str.trim());
			if (m.find()) {
				String result = m.group(2);
				if (isNotBlank(result)) {
					return result.trim();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断字符c是否是中日韩字符
	 * 
	 * @param c
	 * @return
	 * @author Snail
	 */
	public static boolean isCJK(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_COMPATIBILITY// 1
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS// 1
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
				|| ub == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT// 1
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS// 1
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A// 1
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.HANGUL_SYLLABLES
				|| ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || ub == Character.UnicodeBlock.HANGUL_JAMO
				|| ub == Character.UnicodeBlock.KATAKANA
				|| ub == Character.UnicodeBlock.HIRAGANA || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	/**
	 * 清理特殊字符
	 * 
	 * @param str
	 * @return
	 */
	public static String cleanSymbol(String str) {
		if (isEmpty(str)) {
			return null;
		}
		str = str.replaceAll("\\t*|\r*|\n*", "");
		str = str.replaceAll("\\p{P}", "");
		StringBuffer sb = new StringBuffer();
		char[] ch = str.trim().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!isCJK(c) && !CharUtils.isAsciiAlphanumeric(c)) {
				sb.append("");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String cleanSymbol2(String str) {
		if (isEmpty(str)) {
			return null;
		}
		str = str.replaceAll("\\t*|\r*|\n*", "");
		StringBuffer sb = new StringBuffer();
		char[] ch = str.trim().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!isCJK(c) && !CharUtils.isAsciiAlphanumeric(c)) {
				sb.append("_");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}