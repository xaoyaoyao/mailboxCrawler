package com.weiwei.crawler.utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.regex.Pattern;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * @ClassName: InfoExtractorUtils
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:21:17
 *
 */
public class InfoExtractorUtils {

	private static final Logger LOG = Logger.getLogger(InfoExtractorUtils.class);

	private static TransformerFactory transformerFactory = SAXTransformerFactory.newInstance();

	/**
	 * 依据xslt模版从网页中提取信息
	 * 
	 * @param pageContent
	 * @param xslt
	 * @return 失败: null
	 */
	public static String transform(String pageContent, String xslt) {
		if (null == pageContent || "".equals(pageContent) || null == xslt || "".equals(xslt)) {
			return null;
		}
		String resultInfo = null;
		try {
			if (-1 == pageContent.lastIndexOf("</wml>")) { // html
				pageContent = pageContent.replace("&nbsp;", " "); // 必须
				pageContent = HTMLCleanUtils.cleanHtml(pageContent);
				pageContent = pageContent.replaceFirst("<html.*?>", "<html>");
				// log.debug("html cleaned page content:\n{}", pageContent);
			} else { // wml
				pageContent = pageContent.replaceFirst("<!DOCTYPE.*?>", "");
				pageContent = pageContent.replace("&nbsp;", " "); // 可以使最终提取出来的信息规整些,去掉也不会有问题
				// log.debug("wml page content:\n{}", pageContent);
			}
			// 处理一些非法字符
			Pattern p = Pattern.compile("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+");
			pageContent = p.matcher(pageContent).replaceAll("");
			// System.out.println(pageContent);
			StreamSource xsltStream = new StreamSource(IOUtils.toInputStream(xslt, "utf-8"));
			Transformer transformer = transformerFactory.newTransformer(xsltStream);
			StreamSource xmlStream = new StreamSource(new StringReader(pageContent));
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			transformer.transform(xmlStream, new StreamResult(byteStream));
			// 结果信息
			resultInfo = byteStream.toString("utf-8");
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("transform error for xslt:\n{}" + xslt);
			LOG.error(e.getMessage(), e);
		}
		return resultInfo;
	}

	/**
	 * 依据xslt模版从网页中提取信息
	 * 
	 * @param pageContent
	 * @param xslt
	 * @return 失败:null
	 * @throws Exception
	 */
	public static String transformWithThrowException(String pageContent, String xslt) throws Exception {
		if (null == pageContent || "".equals(pageContent) || null == xslt || "".equals(xslt)) {
			return null;
		}
		String resultInfo = null;
		try {
			if (-1 == pageContent.lastIndexOf("</wml>")) { // html
				pageContent = pageContent.replace("&nbsp;", " "); // 必须
				pageContent = HTMLCleanUtils.clean(pageContent);
				pageContent = pageContent.replaceFirst("<html.*?>", "<html>");
				// logger.debug("html cleaned page content:\n{}", pageContent);
			} else { // wml
				pageContent = pageContent.replaceFirst("<!DOCTYPE.*?>", "");
				pageContent = pageContent.replace("&nbsp;", " "); // 可以使最终提取出来的信息规整些,去掉也不会有问题
				// logger.debug("wml page content:\n{}", pageContent);
			}
			StreamSource xsltStream = new StreamSource(IOUtils.toInputStream(xslt, "utf-8"));
			Transformer transformer = transformerFactory.newTransformer(xsltStream);
			StreamSource xmlStream = new StreamSource(new StringReader(pageContent));
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			transformer.transform(xmlStream, new StreamResult(byteStream));
			// 结果信息
			resultInfo = byteStream.toString("utf-8");
		} catch (Exception e) {
			LOG.error("transform error for xslt:\n{}" + xslt);
			LOG.error(e.getMessage(), e);
			throw e;
		}
		return resultInfo;
	}
}
