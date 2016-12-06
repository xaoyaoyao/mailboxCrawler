/**   
 * @Title: MailboxAuthenticate.java 
 * @Package com.enchantin.endzone.entity 
 * @Description: TODO
 * @author xaoyaoyao
 * @date 2015-8-25 下午3:49:13 
 * @version V1.0   
 */
package com.weiwei.crawler.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: MailboxAuthenticate
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午1:00:44
 */
public class MailboxAuthenticate implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6756753927497799181L;

	private String mailHost;

	private String mailApiKey;

	private String mailApiSecret;

	private String scope;

	private String callBack;

	/**
	 * @return the mailHost
	 */
	public String getMailHost() {
		return mailHost;
	}

	/**
	 * @param mailHost
	 *            the mailHost to set
	 */
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	/**
	 * @return the mailApiKey
	 */
	public String getMailApiKey() {
		return mailApiKey;
	}

	/**
	 * @param mailApiKey
	 *            the mailApiKey to set
	 */
	public void setMailApiKey(String mailApiKey) {
		this.mailApiKey = mailApiKey;
	}

	/**
	 * @return the mailApiSecret
	 */
	public String getMailApiSecret() {
		return mailApiSecret;
	}

	/**
	 * @param mailApiSecret
	 *            the mailApiSecret to set
	 */
	public void setMailApiSecret(String mailApiSecret) {
		this.mailApiSecret = mailApiSecret;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the callBack
	 */
	public String getCallBack() {
		return callBack;
	}

	/**
	 * @param callBack
	 *            the callBack to set
	 */
	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}