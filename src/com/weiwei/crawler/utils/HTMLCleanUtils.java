/**
 * HTMLCleanUtil.java
 *
 * Copyright 2007 easou, Inc. All Rights Reserved.
 */
package com.weiwei.crawler.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;

/**
 * @ClassName: HTMLCleanUtil
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:21:33
 *
 */
public class HTMLCleanUtils {
	
	private static final Logger LOG = Logger.getLogger(HTMLCleanUtils.class);
	
	private static HtmlCleaner cleaner = new HtmlCleaner();
	
	private static CleanerProperties props = new CleanerProperties();

	static {
		props = new CleanerProperties();
		props.setAllowHtmlInsideAttributes(true);
		props.setOmitComments(true);
		props.setOmitDoctypeDeclaration(true);
		props.setOmitXmlDeclaration(true);
		props.setPruneTags("script,link,base,style,meta");
		props.setAdvancedXmlEscape(true);
		cleaner = new HtmlCleaner(props);
	}

	public static String clean(String orgPage) {
		String source = null;
		try {
			TagNode rootNode = cleaner.clean(orgPage);
			SimpleXmlSerializer xmlSerializer = new SimpleXmlSerializer(props);
			source = xmlSerializer.getXmlAsString(rootNode);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return source;
	}

	public static String cleanHtml(String htmlPage) throws IOException {
		CleanerProperties props = new CleanerProperties();
		props.setAllowHtmlInsideAttributes(true);
		props.setOmitComments(true);
		props.setOmitDoctypeDeclaration(true);
		props.setPruneTags("script,link,base,style,meta");
		props.setAdvancedXmlEscape(true);
		props.setCleanAttributeName(":");
		HtmlCleaner cleaner = new HtmlCleaner(props);
		SimpleXmlSerializer simpleXmlSerializer = new SimpleXmlSerializer(props);
		// htmlPage = HtmlUtils.htmlUnescape(htmlPage);
		TagNode tagNode = cleaner.clean(htmlPage);
		return simpleXmlSerializer.getXmlAsString(tagNode);
	}

	public static void clean(String inHtmlFileName, String outXmlFileName) {
		// // take default cleaner properties
		// CleanerProperties props = new CleanerProperties();
		//
		// // customize cleaner's behaviour with property setters
		// props.setAllowHtmlInsideAttributes(true);
		// props.setOmitComments(true);
		// props.setOmitDoctypeDeclaration(true);
		// props.setOmitXmlDeclaration(true);
		// // props.setTreatUnknownTagsAsContent(true);
		// // props.setOmitDeprecatedTags(true);
		// props.setPruneTags("script,link,base,style,meta");
		// props.setAdvancedXmlEscape(true);
		//
		// // create an instance of HtmlCleaner
		// HtmlCleaner cleaner = new HtmlCleaner(props);
		//
		// try {
		// // Clean HTML taken from simple string, file, URL, input stream,
		// // input source or reader. Result is root node of created
		// // tree-like structure. Single cleaner instance may be safely used
		// // multiple times.
		// TagNode rootNode = cleaner.clean(new File(inHtmlFileName));
		// SimpleXmlSerializer xmlSerializer = new SimpleXmlSerializer(props);
		// xmlSerializer.writeXmlToFile(rootNode, outXmlFileName, "utf-8");
		// } catch (IOException e) {
		// logger.error(e.getMessage(), e);
		// }
	}
}
