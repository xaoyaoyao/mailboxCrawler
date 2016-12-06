package com.weiwei.crawler.entity;

import java.sql.Timestamp;

/**
 * 
 * @author Marco.Pang
 * 
 */
public class SpiderProduct implements java.io.Serializable {
	
	public final static String STATUS_CANCELED = "Canceled";
	
	/** */
	private static final long serialVersionUID = 8807624091685901470L;

	private Long id;

	private String orderId;

	private String name;

	private String link;

	private String price;

	private String quantity;

	private String sku;

	private String imgUrl;

	private Timestamp createdTime;

	private String currencyType;

	private String status;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType
	 *            the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SpiderProduct [id=" + id + ", orderId=" + orderId + ", name=" + name + ", link=" + link + ", price=" + price + ", quantity="
				+ quantity + ", sku=" + sku + ", imgUrl=" + imgUrl + ", createdTime=" + createdTime + ", currencyType=" + currencyType + ", status="
				+ status + "]";
	}
}