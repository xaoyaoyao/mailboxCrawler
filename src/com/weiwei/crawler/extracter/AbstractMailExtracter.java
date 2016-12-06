/**   
 * @Title: CommonMailExtracter.java 
 * @Package com.enchantin.crawler.extracter.impl 
 * @Description: TODO
 * @author xaoyaoyao
 * @date 2015-9-22 下午12:27:11 
 * @version V1.0   
 */
package com.weiwei.crawler.extracter;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.weiwei.crawler.entity.MailboxInfo;
import com.weiwei.crawler.entity.SpiderSeed;
import com.weiwei.crawler.entity.ShippingStatusEnum;
import com.weiwei.crawler.entity.SpiderOrder;
import com.weiwei.crawler.tools.ExtracterTools;
import com.weiwei.crawler.utils.DateUtils;
import com.weiwei.crawler.utils.OrderUtils;
import com.weiwei.crawler.utils.StringUtils;

/**
 * @author xaoyaoyao
 * @Date 2015-9-22
 */
public abstract class AbstractMailExtracter {

	private static final Logger LOG = Logger.getLogger(AbstractMailExtracter.class);

	/**
	 * @Title: checkExtract
	 * @Description: 检测Mail&&种子信息
	 * @param info
	 * @param seed
	 * @return boolean
	 */
	public boolean checkExtract(MailboxInfo info, SpiderSeed seed) {
		if (null == info || null == seed) {
			if (LOG.isInfoEnabled()) {
				LOG.info("================解析Mail失败，MailInfo或SpiderSeed为空.================");
			}
			return false;
		}
		if (StringUtils.isBlank(seed.getWebSite())) {
			if (LOG.isInfoEnabled()) {
				LOG.info("================解析Mail失败，SpiderSeed的webSite为空.================");
			}
			return false;
		}
		String site = seed.getWebSite();
		String subject = info.getSubject();
		if (StringUtils.isBlank(subject)) {
			if (LOG.isInfoEnabled()) {
				LOG.info("================解析" + site + "失败，主题为空.================");
			}
			return false;
		}
		String body = info.getContent();
		if (StringUtils.isBlank(body)) {
			if (LOG.isInfoEnabled()) {
				LOG.info("================解析" + site + "失败，邮件内容为空.================");
			}
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkInfo
	 * @Description: 检测Mail&&模板&&站点
	 * @param info
	 * @param tmpl
	 * @param site
	 * @return boolean
	 */
	private boolean checkInfo(MailboxInfo info, String tmpl, String site) {
		if (null != info && StringUtils.isNotBlank(info.getContent()) && StringUtils.isNotBlank(tmpl) && StringUtils.isNotBlank(site)) {
			return true;
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("================解析Mail失败，邮件主题，邮件内容，邮件模板或站点为空.================");
		}
		return false;
	}

	/**
	 * @Title: orderedExtracter
	 * @Description: 下单解析器
	 * @param info
	 * @param tmpl
	 * @param site
	 * @return List<SpiderOrder>
	 */
	public List<SpiderOrder> orderedExtracter(MailboxInfo info, SpiderSeed seed) {
		String site = seed.getWebSite();
		String tmpl = seed.getTmplStep1();
		if (!checkInfo(info, tmpl, site)) {
			return null;
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("================下单解析" + site + "下单.================");
		}
		List<SpiderOrder> dataList = ExtracterTools.transform(info.getContent(), tmpl);
		if (null != dataList && !dataList.isEmpty()) {
			for (SpiderOrder order : dataList) {
				order.setWebSite(site);
				// 判断订单号是否存在，不存在则从主题取
				LOG.error("Order1 >> OrderNumber >> " + order.getOrderNum() + ", Regex >> " + seed.getRegexOrderNumStep1() + ", Subject >> " + info.getSubject());
				if (StringUtils.isBlank(order.getOrderNum()) && StringUtils.isNotBlank(seed.getRegexOrderNumStep1())) {
					String orderNum = StringUtils.strRegex2(seed.getRegexOrderNumStep1(), info.getSubject());
					if (StringUtils.isNotBlank(orderNum)) {
						order.setOrderNum(orderNum);
					}
				}
					LOG.error("Order2 >> OrderNumber >> " + order.getOrderNum() + ", Regex >> " + seed.getRegexOrderNumStep1() + ", Subject >> " + info.getSubject());
				// 订单状态为已下单
				order.setDeliveryStatus(ShippingStatusEnum.ORDERED.getShippingStatus());
				order.setStatus(OrderUtils.CONFIRMED_ORDER);
				if (StringUtils.isBlank(order.getOrderDate())) {
					Date date = info.getReceivedDate();
					if (null == date) {
						date = info.getSentDate();
					}
					order.setOrderDate(DateUtils.getLongDate(date));
				}
				order.setReceiveDate(info.getSentDate());
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("<><><><><><><><><><><><>下单解析" + site + "结果:" + dataList);
			}
			return dataList;
		}
		return null;
	}

	/**
	 * @Title: shippedExtracter
	 * @Description: 配送/快递单号解析器
	 * @param info
	 * @param seed
	 * @return List<SpiderOrder>
	 */
	public List<SpiderOrder> shippedExtracter(MailboxInfo info, SpiderSeed seed) {
		String site = seed.getWebSite();
		String tmpl = seed.getTmplStep2();
		if (!checkInfo(info, tmpl, site)) {
			return null;
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("================发货解析" + site + "配送.================");
		}
		List<SpiderOrder> dataList = ExtracterTools.transform(info.getContent(), tmpl);
		if (null != dataList && !dataList.isEmpty()) {
			for (SpiderOrder order : dataList) {
				// 判断订单号是否存在，不存在则从主题取
					LOG.error("Shipped1 >> OrderNumber >> " + order.getOrderNum() + ", Regex >> " + seed.getRegexOrderNumStep2() + ", Subject >> " + info.getSubject());
				if (StringUtils.isBlank(order.getOrderNum()) && StringUtils.isNotBlank(seed.getRegexOrderNumStep2())) {
					String orderNum = StringUtils.strRegex2(seed.getRegexOrderNumStep2(), info.getSubject());
					if (StringUtils.isNotBlank(orderNum)) {
						order.setOrderNum(orderNum);
					}
				}
					LOG.error("Shipped2 >> OrderNumber >> " + order.getOrderNum() + ", Regex >> " + seed.getRegexOrderNumStep2() + ", Subject >> " + info.getSubject());
				if (StringUtils.isBlank(order.getTrackingNum())) {
					order.setShipmentNoTrackingNumber(SpiderOrder.SHIPMENT_NO_TRACKING_NUMBER);
					if (StringUtils.isBlank(order.getOrderDate())) {
						Date date = info.getReceivedDate();
						if (null == date) {
							date = info.getSentDate();
						}
						order.setOrderDate(DateUtils.getLongDate(date));
					}
				}
				order.setTrackingNum(order.getTrackingNum());
				order.setDeliveryCompany(order.getDeliveryCompany());
				order.setWebSite(site);
				order.setDeliveryStatus(ShippingStatusEnum.SHIPPED.getShippingStatus());
				order.setStatus(OrderUtils.SHIPPED_ORDER);
				if (StringUtils.isBlank(order.getDeliveryTime())) {
					order.setDeliveryTime(DateUtils.getLongDate(info.getSentDate()));
				}
				order.setReceiveDate(info.getSentDate());
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("<><><><><><><><><><><><>发货解析" + site + "结果:" + dataList);
			}
			return dataList;
		}
		return null;
	}

	/**
	 * @Title: deliveredExtracter
	 * @Description: 收货解析器
	 * @param info
	 * @param seed
	 * @return List<SpiderOrder>
	 */
	public List<SpiderOrder> deliveredExtracter(MailboxInfo info, SpiderSeed seed) {
		String site = seed.getWebSite();
		String tmpl = seed.getTmplStep3();
		if (!checkInfo(info, tmpl, site)) {
			return null;
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("================收货解析" + site + "收货.================");
		}
		List<SpiderOrder> dataList = ExtracterTools.transform(info.getContent(), tmpl);
		if (null != dataList && !dataList.isEmpty()) {
			for (SpiderOrder order : dataList) {
				// 判断订单号是否存在，不存在则从主题取
				if (StringUtils.isBlank(order.getOrderNum()) && StringUtils.isNotBlank(seed.getRegexOrderNumStep3())) {
					String orderNum = StringUtils.strRegex2(seed.getRegexOrderNumStep3(), info.getSubject());
					if (StringUtils.isNotBlank(orderNum)) {
						order.setOrderNum(orderNum);
					}
				}
				order.setWebSite(site);
				order.setDeliveryStatus(ShippingStatusEnum.RECEIVED_NEED_USER_CONFIRM.getShippingStatus());
				order.setStatus(OrderUtils.DELIVERED_ORDER);
				if (StringUtils.isBlank(order.getArrivedTime())) {
					Date date = info.getReceivedDate();
					if (null == date) {
						date = info.getSentDate();
					}
					order.setOrderDate(DateUtils.getLongDate(date));
				}
				order.setReceiveDate(info.getSentDate());
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("<><><><><><><><><><><><>收货解析" + site + "结果:" + dataList);
			}
			return dataList;
		}
		return null;
	}

	public List<SpiderOrder> extract(MailboxInfo info, SpiderSeed seed) {
		if (!this.checkExtract(info, seed)) {
			if (LOG.isInfoEnabled()) {
				LOG.info("================解析Mail的失败.================");
			}
			return null;
		}
		String subject = info.getSubject();
		String site = seed.getWebSite();
		try {
			if (StringUtils.strRegex(seed.getRegexStep1(), subject)) {
				return this.orderedExtracter(info, seed);
			} else if (StringUtils.strRegex(seed.getRegexStep2(), subject)) {
				return this.shippedExtracter(info, seed);
			} else if (StringUtils.strRegex(seed.getRegexStep3(), subject)) {
				return this.deliveredExtracter(info, seed);
			}
		} catch (Exception e) {
			LOG.error("解析XML获取Bean失败", e);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("================解析" + site + "失败================");
		}
		return null;
	}
}