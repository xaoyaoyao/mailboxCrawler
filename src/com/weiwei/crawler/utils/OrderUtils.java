/**   
 * @Title: OrderStatusUtil.java 
 * @Package com.enchantin.crawler.util 
 * @Description: TODO
 * @author weiwei
 * @date 2015-6-1 上午9:46:30 
 * @version V1.0   
 */
package com.weiwei.crawler.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

/**
 * @author weiwei
 * @Date 2015-6-1
 */
public class OrderUtils {
	
	private static final Logger LOG = Logger.getLogger(OrderUtils.class);
	
	/** 订单状态已达到 **/
	public final static String DELIVERED_ORDER = "delivered";

	/** 退货 **/
	public final static String REFUNDED_ORDER = "refunded";

	/** 发货 **/
	public final static String SHIPPED_ORDER = "shipped";

	/** 确认下单 **/
	public final static String CONFIRMED_ORDER = "confirmed";

	/** 在途 **/
	public final static String ARRIVING_ORDER = "arriving";

	/**
	 * @Title: siteRegexByText
	 * @Description: 限时匹配
	 * @param siteRegex
	 *            正则表达式
	 * @param text
	 *            内容
	 * @param seconds
	 *            秒数5~120
	 * @Reutrn boolean
	 */
	public static boolean siteRegexByText(final String siteRegex, final String text, int seconds) {
		if (StringUtils.isBlank(text) || StringUtils.isBlank(siteRegex)) {
			return false;
		}
		seconds = seconds <= 0 ? 5 : seconds > 120 ? 120 : seconds;
		if (LOG.isDebugEnabled()) {
			LOG.debug("######### 根据正文获取站点, 正则表达式 >> " + siteRegex + ", headText：" + text + " #########");
		}
		Boolean result = false;
		ExecutorService service = Executors.newSingleThreadExecutor();
		FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
			public Boolean call() throws Exception {
				if (LOG.isDebugEnabled()) {
					LOG.debug("######### 开始根据正文获取站点, 正则表达式 >> " + siteRegex + " #########");
				}
				Boolean result = StringUtils.strRegex(siteRegex, text);
				if (LOG.isDebugEnabled()) {
					LOG.debug("######### 根据正文获取站点结束,  正则表达式 >> " + siteRegex + ", 结果1：" + result + " #########");
				}
				return result;
			}
		});
		service.execute(future);
		try {
			result = future.get(1000 * seconds, TimeUnit.MILLISECONDS); // 任务处理超时时间设为seconds
		} catch (TimeoutException ex) {
			LOG.error("######### 根据正文获取站点, 正则表达式 >> " + siteRegex + " , error：" + ex.getMessage());
			future.cancel(true);
		} catch (Exception e) {
			LOG.error("######### 根据正文获取站点, 正则表达式 >> " + siteRegex + ", error：" + e.getMessage());
			future.cancel(true);
		} finally {
			if (null != service) {
				service.shutdown();
			}
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("######### 根据正文获取站点,  正则表达式 >> " + siteRegex + ", 结果2：" + result + " #########");
		}
		return result == null ? false : result;
	}
}