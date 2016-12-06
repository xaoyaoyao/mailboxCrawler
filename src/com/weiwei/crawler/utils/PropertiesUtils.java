/**   
 * @Title: USPropertiesUtils.java 
 * @Package com.enchantin.crawler.util 
 * @Description: TODO
 * @author weiwei
 * @date 2015-6-12 下午2:12:08 
 * @version V1.0   
 */
package com.weiwei.crawler.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.weiwei.crawler.entity.MailboxAuthenticate;

/**
 * @ClassName: PropertiesUtils
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:57:43
 */
public class PropertiesUtils {

	private static final Logger LOG = Logger.getLogger(PropertiesUtils.class);

	/**
	 * @Title: readPropertiesToMap
	 * @Description: 读取配置文件信息
	 * @param fileName
	 * @Reutrn Map<String,String>
	 */
	public static Map<String, String> readPropertiesToMap(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return new HashMap<String, String>(1);
		}
		Properties properties = new Properties();
		InputStream input = PropertiesUtils.class.getResourceAsStream("/" + fileName);
		if (input != null) {
			try {
				properties.load(input);
				return new HashMap<String, String>((Map) properties);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			} finally {
				if (input != null)
					try {
						input.close();
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
			}
		}
		return new HashMap<String, String>(1);
	}

	/**
	 * @Title: getMailboxAuthenticateBySite
	 * @Description: 获取oauth2配置信息
	 * @param site
	 * @Reutrn MailboxAuthenticate
	 */
	public static MailboxAuthenticate getMailboxAuthenticateBySite(String site) {
		if (StringUtils.isBlank(site)) {
			return null;
		}
		site = site.toLowerCase().trim();
		Map<String, String> map = readPropertiesToMap(site + "-oauth2.properties");
		if (null != map && !map.isEmpty()) {
			MailboxAuthenticate mailboxAuthenticate = new MailboxAuthenticate();
			String callBack = getValue(map.get("oauth2-callback"));
			String mailApiKey = getValue(map.get("oauth2-key"));
			String mailApiSecret = getValue(map.get("oauth2-secret"));
			String mailHost = getValue(map.get("oauth2-host"));
			String scope = getValue(map.get("oauth2-scope"));
			mailboxAuthenticate.setCallBack(callBack);
			mailboxAuthenticate.setMailApiKey(mailApiKey);
			mailboxAuthenticate.setMailApiSecret(mailApiSecret);
			mailboxAuthenticate.setMailHost(mailHost);
			mailboxAuthenticate.setScope(scope);
			return mailboxAuthenticate;
		}
		return null;
	}

	private static String getValue(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return value.trim();
	}

	public static void main(String[] args) {
		Map<String, String> map = readPropertiesToMap("gmail-oauth2.properties");
		Set<Entry<String, String>> propertySet = map.entrySet();
		for (Object o : propertySet) {
			Map.Entry<String, String> entry = (Map.Entry) o;
			System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
		}
		System.out.println("================================================");
		MailboxAuthenticate mailboxAuthenticate = getMailboxAuthenticateBySite("yahoo");
		System.out.println(mailboxAuthenticate);
	}
}