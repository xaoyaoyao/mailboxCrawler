package com.weiwei.crawler.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Marco.Pang
 * 
 */
public class SpiderOrder implements java.io.Serializable {

	public static final int SHIPMENT_NO_TRACKING_NUMBER = 1;
	
	public static final int SHIPMENT_YES_TRACKING_NUMBER = 0;

	/** */
	private static final long serialVersionUID = 4342074534715533013L;

	/** 用户ID **/
	private String userId;

	/** 账号ID **/
	private String acountId;

	/** 订单号 **/
	private String orderNum;

	/** 快递单号 **/
	private String trackingNum;

	/** 名称 **/
	private String name;

	/** 站点 **/
	private String webSite;

	/** 站点LogoURL **/
	private String webIcon;

	/** 快递公司 **/
	private String deliveryCompany;

	/** 订单状态 **/
	private Integer deliveryStatus;

	/** 电话 **/
	private String phone;

	/** 收货人 **/
	private String receiver;

	/** 订单总价 **/
	private String subtotal;

	/** 运费 **/
	private String shipping;

	/** 免运费用 **/
	private String freeShipping;

	/** 优惠卡等优惠费用 **/
	private String giftCardAmount;

	/** 订单状态 **/
	private String status;

	/** 税收 **/
	private String tax;

	/** 达到时间 **/
	private String arrivedTime;

	/** 下单时间 **/
	private String orderDate;

	/** 发货时间 **/
	private String deliveryTime;

	/** 快单单号后缀 **/
	private String suffix;

	/** 关键词 **/
	private String keyword;

	/** 订单详情URL **/
	private String orderUrl;

	/** 快递单URL **/
	private String trackingUrl;

	/** 收货详细地址 **/
	private String addr;

	/** 国家 **/
	private String country;

	/** 州/省 **/
	private String state;

	/** 城市 **/
	private String city;

	/** 邮编 **/
	private String zip;

	/** 商品信息 **/
	private List<SpiderProduct> products;

	/** 货币类型，默认美刀 **/
	private String currencyType;

	/** 预计到达时间 **/
	private String expectedDate;

	/** return code **/
	private String returnCode;

	/** 已发货但没有tracking number **/
	private Integer shipmentNoTrackingNumber;

	/** 标记是否需要删除老订单再写入 **/
	private Integer deleteOldOrder;

	/** 是否用户是首次绑定抓取的订单， 0：否 1：是 **/
	private Integer isFirstTime;

	private Date createdTime;

	/** trackingNumber集合 **/
	private List<TrackingInfo> trackingList;

	/** 接收郵件時間 **/
	private Date receiveDate;

	/** 解析时间 **/
	private String resolveDate;

	/** 邮件ID **/
	private String mailId;

	/** 邮件唯一标识 **/
	private String messageId;

	/** 账号 **/
	private String email;

	/** 订单默认图片 **/
	private String defaultImg;

	/** 标识是否需要商品信息才能入库 **/
	private Integer isProduct;

	/** 收件人 **/
	private String addressee;

	/** 唯一键：主要用于处理并发写入订单重复 **/
	private String uniqueIndex;

	private String expressLogo;

	private Integer isIndex;

	/** 订单来源 0：邮件抓取 1：amazon 2：slice **/
	private Integer orderSource;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the acountId
	 */
	public String getAcountId() {
		return acountId;
	}

	/**
	 * @param acountId
	 *            the acountId to set
	 */
	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @return the webIcon
	 */
	public String getWebIcon() {
		return webIcon;
	}

	/**
	 * @param webIcon
	 *            the webIcon to set
	 */
	public void setWebIcon(String webIcon) {
		this.webIcon = webIcon;
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
	 * @return the deliveryStatus
	 */
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * @param deliveryStatus
	 *            the deliveryStatus to set
	 */
	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the subtotal
	 */
	public String getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal
	 *            the subtotal to set
	 */
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @return the shipping
	 */
	public String getShipping() {
		return shipping;
	}

	/**
	 * @param shipping
	 *            the shipping to set
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	/**
	 * @return the freeShipping
	 */
	public String getFreeShipping() {
		return freeShipping;
	}

	/**
	 * @param freeShipping
	 *            the freeShipping to set
	 */
	public void setFreeShipping(String freeShipping) {
		this.freeShipping = freeShipping;
	}

	/**
	 * @return the giftCardAmount
	 */
	public String getGiftCardAmount() {
		return giftCardAmount;
	}

	/**
	 * @param giftCardAmount
	 *            the giftCardAmount to set
	 */
	public void setGiftCardAmount(String giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
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

	/**
	 * @return the tax
	 */
	public String getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}

	/**
	 * @return the arrivedTime
	 */
	public String getArrivedTime() {
		return arrivedTime;
	}

	/**
	 * @param arrivedTime
	 *            the arrivedTime to set
	 */
	public void setArrivedTime(String arrivedTime) {
		this.arrivedTime = arrivedTime;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime
	 *            the deliveryTime to set
	 */
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the orderUrl
	 */
	public String getOrderUrl() {
		return orderUrl;
	}

	/**
	 * @param orderUrl
	 *            the orderUrl to set
	 */
	public void setOrderUrl(String orderUrl) {
		this.orderUrl = orderUrl;
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

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the products
	 */
	public List<SpiderProduct> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<SpiderProduct> products) {
		this.products = products;
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
	 * @return the expectedDate
	 */
	public String getExpectedDate() {
		return expectedDate;
	}

	/**
	 * @param expectedDate
	 *            the expectedDate to set
	 */
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode
	 *            the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the shipmentNoTrackingNumber
	 */
	public Integer getShipmentNoTrackingNumber() {
		return shipmentNoTrackingNumber;
	}

	/**
	 * @param shipmentNoTrackingNumber
	 *            the shipmentNoTrackingNumber to set
	 */
	public void setShipmentNoTrackingNumber(Integer shipmentNoTrackingNumber) {
		this.shipmentNoTrackingNumber = shipmentNoTrackingNumber;
	}

	/**
	 * @return the deleteOldOrder
	 */
	public Integer getDeleteOldOrder() {
		return deleteOldOrder;
	}

	/**
	 * @param deleteOldOrder
	 *            the deleteOldOrder to set
	 */
	public void setDeleteOldOrder(Integer deleteOldOrder) {
		this.deleteOldOrder = deleteOldOrder;
	}

	/**
	 * @return the isFirstTime
	 */
	public Integer getIsFirstTime() {
		return isFirstTime;
	}

	/**
	 * @param isFirstTime
	 *            the isFirstTime to set
	 */
	public void setIsFirstTime(Integer isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the trackingList
	 */
	public List<TrackingInfo> getTrackingList() {
		return trackingList;
	}

	/**
	 * @param trackingList
	 *            the trackingList to set
	 */
	public void setTrackingList(List<TrackingInfo> trackingList) {
		this.trackingList = trackingList;
	}

	/**
	 * @return the receiveDate
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}

	/**
	 * @param receiveDate
	 *            the receiveDate to set
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * @return the resolveDate
	 */
	public String getResolveDate() {
		return resolveDate;
	}

	/**
	 * @param resolveDate
	 *            the resolveDate to set
	 */
	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}

	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * @param mailId
	 *            the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the defaultImg
	 */
	public String getDefaultImg() {
		return defaultImg;
	}

	/**
	 * @param defaultImg
	 *            the defaultImg to set
	 */
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	/**
	 * @return the isProduct
	 */
	public Integer getIsProduct() {
		return isProduct;
	}

	/**
	 * @param isProduct
	 *            the isProduct to set
	 */
	public void setIsProduct(Integer isProduct) {
		this.isProduct = isProduct;
	}

	/**
	 * @return the addressee
	 */
	public String getAddressee() {
		return addressee;
	}

	/**
	 * @param addressee
	 *            the addressee to set
	 */
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getUniqueIndex() {
		return uniqueIndex;
	}

	public void setUniqueIndex(String uniqueIndex) {
		this.uniqueIndex = uniqueIndex;
	}

	/**
	 * @return the expressLogo
	 */
	public String getExpressLogo() {
		return expressLogo;
	}

	/**
	 * @param expressLogo
	 *            the expressLogo to set
	 */
	public void setExpressLogo(String expressLogo) {
		this.expressLogo = expressLogo;
	}

	/**
	 * @return the isIndex
	 */
	public Integer getIsIndex() {
		return isIndex;
	}

	/**
	 * @param isIndex
	 *            the isIndex to set
	 */
	public void setIsIndex(Integer isIndex) {
		this.isIndex = isIndex;
	}

	/**
	 * @return the orderSource
	 */
	public Integer getOrderSource() {
		return orderSource;
	}

	/**
	 * @param orderSource
	 *            the orderSource to set
	 */
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	@Override
	public String toString() {
		return "SpiderOrder [userId=" + userId + ", acountId=" + acountId + ", orderNum=" + orderNum + ", trackingNum=" + trackingNum + ", name=" + name + ", webSite=" + webSite
				+ ", webIcon=" + webIcon + ", deliveryCompany=" + deliveryCompany + ", deliveryStatus=" + deliveryStatus + ", phone=" + phone + ", receiver=" + receiver
				+ ", subtotal=" + subtotal + ", shipping=" + shipping + ", freeShipping=" + freeShipping + ", giftCardAmount=" + giftCardAmount + ", status=" + status + ", tax="
				+ tax + ", arrivedTime=" + arrivedTime + ", orderDate=" + orderDate + ", deliveryTime=" + deliveryTime + ", suffix=" + suffix + ", keyword=" + keyword
				+ ", orderUrl=" + orderUrl + ", trackingUrl=" + trackingUrl + ", addr=" + addr + ", country=" + country + ", state=" + state + ", city=" + city + ", zip=" + zip
				+ ", products=" + products + ", currencyType=" + currencyType + ", expectedDate=" + expectedDate + ", returnCode=" + returnCode + ", shipmentNoTrackingNumber="
				+ shipmentNoTrackingNumber + ", deleteOldOrder=" + deleteOldOrder + ", isFirstTime=" + isFirstTime + ", createdTime=" + createdTime + ", trackingList="
				+ trackingList + ", receiveDate=" + receiveDate + ", resolveDate=" + resolveDate + ", mailId=" + mailId + ", messageId=" + messageId + ", email=" + email
				+ ", defaultImg=" + defaultImg + ", isProduct=" + isProduct + ", addressee=" + addressee + ", uniqueIndex=" + uniqueIndex + ", expressLogo=" + expressLogo
				+ ", isIndex=" + isIndex + ", orderSource=" + orderSource + "]";
	}
}