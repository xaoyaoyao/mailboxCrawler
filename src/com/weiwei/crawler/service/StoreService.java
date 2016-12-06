/**     
 * @Title: StoreService.java   
 * @Package com.weiwei.crawler.service   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年12月1日 下午12:27:23   
 * @version V1.0     
 */
package com.weiwei.crawler.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Store;

import org.apache.log4j.Logger;

import com.weiwei.crawler.entity.MailboxAuthenticate;
import com.weiwei.crawler.entity.SpiderTask;
import com.weiwei.crawler.utils.LinkMailboxUtils;
import com.weiwei.crawler.utils.PropertiesUtils;
import com.weiwei.crawler.utils.StringUtils;

/**
 * @ClassName: StoreService
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:27:23
 * 
 */
public class StoreService {

	private static final Logger LOG = Logger.getLogger(StoreService.class);

	public Store linkMailboxToStore(SpiderTask task, Properties properties) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug("===============<>获取邮件Store<>===================");
			LOG.debug("****************SpiderTask >> " + task);
		}
		if (task == null || StringUtils.isBlank(task.getSite())) {
			return null;
		}
		if (null == properties) {
			properties = new Properties();
		}
		String strAuth = "OAuth2模式";
		try {
			// 密码模式
			if (null != task.getAuthType() && task.getAuthType() == SpiderTask.OAUTH_BY_PASSWORD) {
				strAuth = "密码模式";
				String host = task.getHost();
				Integer port = task.getPort();
				Integer ssl = task.getSsl();
				String protocol = task.getProtocol();
				String user = task.getEmail();
				boolean isSSL = null == ssl || ssl != LinkMailboxUtils.IS_SSL ? true : false;
				if (LOG.isDebugEnabled()) {
					LOG.debug("host>>>" + host + "  user>>>" + user + "  port>>>" + port + "  protocol>>>" + protocol + "  isSSL>>>" + isSSL);
				}
				Store store = LinkMailboxUtils.getStoreByPasswd(properties, protocol, host, user, task.getPassword(), isSSL);
				if (LOG.isInfoEnabled()) {
					LOG.info("****************LinkMailboxUtils.StoreByPasswd >> " + store);
				}
				return store;
			}
			// oauth2模式
			OAuth2Service oauth2 = new OAuth2Service();
			String site = task.getSite();
			MailboxAuthenticate mailAuthenticate = PropertiesUtils.getMailboxAuthenticateBySite(site);
			if (null != mailAuthenticate) {
				String host = mailAuthenticate.getMailHost();
				String accessToken = oauth2.getAccessToken(task.getToken(), task.getSite(), mailAuthenticate);
				String user = task.getEmail();
				if (LOG.isDebugEnabled()) {
					LOG.debug("host>>>" + host + "  user>>>" + user + "  accessToken>>>" + accessToken);
				}
				Store store = LinkMailboxUtils.getStoreByOauth2(properties, host, user, accessToken);
				if (LOG.isInfoEnabled()) {
					LOG.info("****************LinkMailboxUtils.StoreByOauth2 >> " + store);
				}
				return store;
			}
		} catch (MessagingException e) {
			LOG.error("getMailStore err >> " + e.getMessage() + ", Email >> " + task.getEmail() + ", 授权方式 >> " + strAuth, e);
		}
		LOG.error("===============" + task.getEmail() + "登录失败===================");
		return null;
	}
}