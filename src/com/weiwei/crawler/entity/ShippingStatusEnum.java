/**     
 * @Title: ShippingStatusEnum.java   
 * @Package com.enchantin.endzone.entity.service   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年8月16日 下午7:58:44   
 * @version V1.0     
 */
package com.weiwei.crawler.entity;

/**
 * @ClassName: ShippingStatusEnum
 * @Description: TODO
 * @author weiwei
 * @date 2016年8月16日 下午7:58:44
 * 
 */
public enum ShippingStatusEnum {

	/** 0: Cancel Order 取消订单 **/
	CANCEL(0, "Cancel Order", "取消订单"),

	/** 10.Ordered 已下单 **/
	ORDERED(10, "Ordered", "已下单"),

	/** 20.Shipped 已发货 **/
	SHIPPED(20, "Shipped", "已发货"),

	/** 30.Out For Delivery 正在配送 **/
	OUT_FOR_DELIVERY(30, "Out For Delivery", "正在配送"),

	/** 40.In the uCella 已投递进uCella **/
	IN_THE_UCELLA(40, "In the uCella", "已投递进uCella"),

	/** 50.Received? 收到确认 **/
	RECEIVED_NEED_USER_CONFIRM(50, "Received?", "收到确认"),

	/** 60.Received 已收到 **/
	RECEIVED(60, "Received", "已收到"),

	/** 70.Partlally Returned 部分退货 **/
	PARTLALLY_RETURNED(70, "Partlally Returned", "部分退货"),

	/** 80.Returned 已退货 **/
	RETURNED(80, "Returned", "已退货"),

	/** 90.Pickup Requested 已请求取货 **/
	PICKUP_REQUESTED(90, "Pickup Requested", "已请求取货"),

	/** 100.Picked up 已取走 **/
	PICKED_UP(100, "Picked up", "已取走"),

	/** 110.Arrived 已到达目的地 **/
	ARRIVED(110, "Arrived", "已到达目的地");

	/** 状态码 **/
	private Integer shippingStatus;

	/** 状态名称 **/
	private String name;

	/** 中文描述信息 **/
	private String remark;

	// 构造方法
	private ShippingStatusEnum(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	// 构造方法
	private ShippingStatusEnum(Integer shippingStatus, String name) {
		this.shippingStatus = shippingStatus;
		this.name = name;
	}

	// 构造方法
	private ShippingStatusEnum(Integer shippingStatus, String name, String remark) {
		this.shippingStatus = shippingStatus;
		this.name = name;
		this.remark = remark;
	}

	/**
	 * @Title: getShippingStatus
	 * @Description: 状态码
	 * @Reutrn Integer
	 */
	public Integer getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * @Title: getName
	 * @Description: 状态名称
	 * @Reutrn String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @Title: getRemark
	 * @Description: 中文描述信息
	 * @Reutrn String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * @Title: getDeliveryStatus
	 * @Description: 转换为老订单状态，为了兼容老版本
	 * @param shippingStatus
	 *            状态码
	 * @Reutrn Integer
	 */
	public static Integer getDeliveryStatus(Integer shippingStatus) {
		if (null != shippingStatus) {
			if (shippingStatus == ShippingStatusEnum.CANCEL.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_CANCEL;
			} else if (shippingStatus == ShippingStatusEnum.ORDERED.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_ORDERED;
			} else if (shippingStatus == ShippingStatusEnum.SHIPPED.getShippingStatus() || shippingStatus == ShippingStatusEnum.OUT_FOR_DELIVERY.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_ON_THE_WAY;
			} else if (shippingStatus == ShippingStatusEnum.IN_THE_UCELLA.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_IN_THE_UCELLA;
			} else if (shippingStatus == ShippingStatusEnum.RECEIVED_NEED_USER_CONFIRM.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_RECEIVED_NEED_USER_CONFIRM;
			} else if (shippingStatus == ShippingStatusEnum.RECEIVED.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_RECEIVED;
			} else if (shippingStatus == ShippingStatusEnum.PARTLALLY_RETURNED.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_PARTLALLY_RETURNED;
			} else if (shippingStatus == ShippingStatusEnum.RETURNED.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_RETURNED;
			} else if (shippingStatus == ShippingStatusEnum.PICKUP_REQUESTED.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_PICKUP_REQUEST;
			} else if (shippingStatus == ShippingStatusEnum.PICKED_UP.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_PICKED_UP;
			} else if (shippingStatus == ShippingStatusEnum.ARRIVED.getShippingStatus()) {
				return ORDER_DELIVERY_STATUS_ARRIVED;
			}
		}
		return ORDER_DELIVERY_STATUS_ORDERED;
	}

	/**
	 * @Title: getShippingStatus
	 * @Description: 老的状态转换为新的状态
	 * @param deliveryStatus
	 * @Reutrn Integer
	 */
	public static Integer getShippingStatus(Integer deliveryStatus) {
		if (null != deliveryStatus) {
			switch (deliveryStatus) {
			case ORDER_DELIVERY_STATUS_CANCEL:
				return ShippingStatusEnum.CANCEL.getShippingStatus();
			case ORDER_DELIVERY_STATUS_ORDERED:
				return ShippingStatusEnum.ORDERED.getShippingStatus();
			case ORDER_DELIVERY_STATUS_ON_THE_WAY:
				return ShippingStatusEnum.SHIPPED.getShippingStatus();
			case ORDER_DELIVERY_STATUS_IN_THE_UCELLA:
				return ShippingStatusEnum.IN_THE_UCELLA.getShippingStatus();
			case ORDER_DELIVERY_STATUS_RECEIVED_NEED_USER_CONFIRM:
				return ShippingStatusEnum.RECEIVED_NEED_USER_CONFIRM.getShippingStatus();
			case ORDER_DELIVERY_STATUS_RECEIVED:
				return ShippingStatusEnum.RECEIVED.getShippingStatus();
			case ORDER_DELIVERY_STATUS_PARTLALLY_RETURNED:
				return ShippingStatusEnum.PARTLALLY_RETURNED.getShippingStatus();
			case ORDER_DELIVERY_STATUS_RETURNED:
				return ShippingStatusEnum.RETURNED.getShippingStatus();
			case ORDER_DELIVERY_STATUS_PICKUP_REQUEST:
				return ShippingStatusEnum.PICKUP_REQUESTED.getShippingStatus();
			case ORDER_DELIVERY_STATUS_PICKED_UP:
				return ShippingStatusEnum.PICKED_UP.getShippingStatus();
			case ORDER_DELIVERY_STATUS_ARRIVED:
				return ShippingStatusEnum.ARRIVED.getShippingStatus();
			}
		}
		return ShippingStatusEnum.ORDERED.getShippingStatus();
	}

	/************************************ 以下老的订单状态 ************************************/

	/** -1：订单取消 **/
	private final static int ORDER_DELIVERY_STATUS_CANCEL = -1;

	/** 0:已下单Ordered **/
	private final static int ORDER_DELIVERY_STATUS_ORDERED = 0;

	/** 1:在途On the way **/
	private final static int ORDER_DELIVERY_STATUS_ON_THE_WAY = 1;

	/** 2:到达In the uCella **/
	private final static int ORDER_DELIVERY_STATUS_IN_THE_UCELLA = 2;

	/** 3：取走Received:只有从In the uCella过来的 **/
	private final static int ORDER_DELIVERY_STATUS_RECEIVED = 3;

	/** 4：部分退货Partlally Returned **/
	private final static int ORDER_DELIVERY_STATUS_PARTLALLY_RETURNED = 4;

	/** 5：退货Returned **/
	private final static int ORDER_DELIVERY_STATUS_RETURNED = 5;

	/** 6: 退货单PICKUP_REQUEST **/
	private final static int ORDER_DELIVERY_STATUS_PICKUP_REQUEST = 6;

	/** 7：从uCella端取走Picked up **/
	private final static int ORDER_DELIVERY_STATUS_PICKED_UP = 7;

	/** 8：退货到达目的Arrived **/
	private final static int ORDER_DELIVERY_STATUS_ARRIVED = 8;

	/** 9：取走Received:直接从on the way过来的，需要用户确认 **/
	private final static int ORDER_DELIVERY_STATUS_RECEIVED_NEED_USER_CONFIRM = 9;

	public static void main(String[] args) {
		System.out.println(ShippingStatusEnum.ORDERED.getShippingStatus());
		System.out.println(ShippingStatusEnum.ORDERED.getName());
		System.out.println(ShippingStatusEnum.ORDERED.getRemark());
		System.out.println(ShippingStatusEnum.getDeliveryStatus(ShippingStatusEnum.SHIPPED.getShippingStatus()));
	}
}