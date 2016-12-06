/**   
 * @Title: TrackingInfo.java 
 * @Package com.enchantin.crawler.entity 
 * @Description: TODO
 * @author weiwei
 * @date 2015-12-16 下午4:34:09 
 * @version V1.0   
 */
package com.weiwei.crawler.entity;

import java.io.Serializable;

/**
 * @author weiwei
 * @Date 2015-12-16
 */
public class TrackingInfo implements Serializable {

	/** 
	 * @Fields serialVersionUID : TODO 
	 */ 
	private static final long serialVersionUID = 7468583428069632865L;

	/** 快递单号 **/
	private String trackingNum;

	/** 快递公司 **/
	private String deliveryCompany;

	/** 快递单URL **/
	private String trackingUrl;

	/**
	 * @return the trackingNum
	 */
	public String getTrackingNum() {
		return trackingNum;
	}

	/**
	 * @param trackingNum
	 *            the trackingNum to set
	 */
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	/**
	 * @return the deliveryCompany
	 */
	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	/**
	 * @param deliveryCompany
	 *            the deliveryCompany to set
	 */
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	/**
	 * @return the trackingUrl
	 */
	public String getTrackingUrl() {
		return trackingUrl;
	}

	/**
	 * @param trackingUrl
	 *            the trackingUrl to set
	 */
	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}
}