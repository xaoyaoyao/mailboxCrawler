/**   
 * @Title: ReadFileUtils.java 
 * @Package com.weiwei.crawler.utils
 * @Description: TODO
 * @author xaoyaoyao
 * @date 2015-8-7 下午1:52:57 
 * @version V1.0   
 */
package com.weiwei.crawler.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @ClassName: ReadFileUtils
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午1:42:10
 *
 */
public class ReadFileUtils {

	/**
	 * @Title: readTemplateContent
	 * @Description: 读取模板内容
	 * @param templateName
	 * @throws IOException
	 * @Reutrn String
	 */
	public static String readTemplateContent(String templateName) throws IOException {
		String pathname = System.getProperty("user.dir") + File.separator + "template" + File.separator + templateName;
		File file = new File(pathname);
		String content = FileUtils.readFileToString(file);
		return content;
	}

	/**
	 * @Title: getEmlPath
	 * @Description: 获取文件路径+文件名
	 * @param fileName
	 * @Reutrn String
	 */
	public static String getEmlPath(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			String pathname = System.getProperty("user.dir") + File.separator + "mailbox" + File.separator + fileName;
			return pathname;
		}
		return null;
	}

	/**
	 * @Title: getMailboxPath
	 * @Description: 获取mailbox的绝对路径
	 * @Reutrn String
	 */
	public static String getMailboxPath() {
		return System.getProperty("user.dir") + File.separator + "mailbox" + "/";
	}

	/**
	 * @Title: readHtml
	 * @Description: 读取html文件
	 * @param filePath
	 * @throws IOException 
	 * @Reutrn String
	 */
	public static String readHtml(String filePath) throws IOException {
		return readContent(filePath, "html");
	}

	/**
	 * @Title: readContent
	 * @Description: 读取pathName下的文件
	 * @param templateName
	 * @param pathName
	 * @throws IOException
	 * @return String
	 */
	public static String readContent(String templateName, String pathName) throws IOException {
		String pathname = System.getProperty("user.dir") + File.separator + pathName + File.separator + templateName;
		File file = new File(pathname);
		String content = FileUtils.readFileToString(file);
		return content;
	}

	public static void main(String[] args) throws IOException {
		String str = getEmlPath("1f89be360972f7ecae59413355733987.eml");
		System.out.println(str);
		System.out.println(getMailboxPath());
		System.out.println(readTemplateContent("usps-shipper.xml"));
	}
}