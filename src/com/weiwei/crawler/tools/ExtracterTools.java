/**   
 * @Title: ExtracterUtils.java 
 * @Package com.enchantin.crawler.util 
 * @Description: TODO
 * @author xaoyaoyao
 * @date 2015-9-21 下午6:42:28 
 * @version V1.0   
 */
package com.weiwei.crawler.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.weiwei.crawler.entity.OrderLink;
import com.weiwei.crawler.entity.SpiderOrder;
import com.weiwei.crawler.entity.SpiderProduct;
import com.weiwei.crawler.entity.TrackingInfo;
import com.weiwei.crawler.utils.InfoExtractorUtils;
import com.weiwei.crawler.utils.StringUtils;

/**
 * @author xaoyaoyao
 * @Date 2015-9-21
 */
public class ExtracterTools {
	
	private final static Logger LOG = Logger.getLogger(ExtracterTools.class);

	public static List<SpiderOrder> transform(String pageContent, String xslt) {
		if (StringUtils.isBlank(pageContent) || StringUtils.isBlank(xslt)) {
			return null;
		}
		try {
			XStream xStream = new XStream();
			xStream.alias("orderList", ArrayList.class);
			xStream.alias("spiderOrder", SpiderOrder.class);
			xStream.alias("products", ArrayList.class);
			xStream.alias("product", SpiderProduct.class);
			xStream.alias("trackingList", ArrayList.class);
			xStream.alias("trackingInfo", TrackingInfo.class);
			String xml = InfoExtractorUtils.transform(pageContent, xslt);
			if (LOG.isDebugEnabled()) {
				LOG.debug(xml);
			}
			if (StringUtils.isNotBlank(xml)) {
				List<SpiderOrder> spiderOrders = (List<SpiderOrder>) xStream.fromXML(xml);
				return spiderOrders;
			}
		} catch (Exception e) {
			LOG.error("transToSpiderOrder err :: " + e.getMessage(), e);
		}
		return null;
	}

	public static OrderLink transformOrderLink(String pageContent, String xslt) {
		if (StringUtils.isBlank(pageContent) || StringUtils.isBlank(xslt)) {
			return null;
		}
		try {
			XStream xStream = new XStream();
			xStream.alias("orderLink", OrderLink.class);
			xStream.alias("orderList", ArrayList.class);
			xStream.alias("spiderOrder", SpiderOrder.class);
			xStream.alias("products", ArrayList.class);
			xStream.alias("product", SpiderProduct.class);
			xStream.alias("trackingList", ArrayList.class);
			xStream.alias("trackingInfo", TrackingInfo.class);
			String xml = InfoExtractorUtils.transform(pageContent, xslt);
			if (LOG.isDebugEnabled()) {
				LOG.debug(xml);
			}
			OrderLink orderLink = (OrderLink) xStream.fromXML(xml);
			return orderLink;
		} catch (Exception e) {
			LOG.error("transToSpiderOrder err :: " + e.getMessage(), e);
		}
		return null;
	}
}