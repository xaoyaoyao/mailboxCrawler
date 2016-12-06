/**   
 * @Title: OrderLink.java 
 * @Package com.enchantin.ucella.entity 
 * @Description: TODO
 * @author weiwei
 * @date 2015-5-20 下午2:56:35 
 * @version V1.0   
 */
package com.weiwei.crawler.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author weiwei
 * @Date 2015-5-20
 */
public class OrderLink implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private List<SpiderOrder> orderList;

	private String nextPage;

	/**
	 * @return the orderList
	 */
	public List<SpiderOrder> getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList
	 *            the orderList to set
	 */
	public void setOrderList(List<SpiderOrder> orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the nextPage
	 */
	public String getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "OrderLink [orderList=" + orderList + ", nextPage=" + nextPage + "]";
	}
}