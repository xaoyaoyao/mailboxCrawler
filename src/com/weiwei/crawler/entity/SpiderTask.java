package com.weiwei.crawler.entity;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: Task
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:35:54
 */
public class SpiderTask implements java.io.Serializable {
	/** */
	private static final long serialVersionUID = -4145685659594790185L;

	public static final int OAUTH_BY_OAUTH2 = 0;

	public static final int OAUTH_BY_PASSWORD = 1;

	/** SSL连接 **/
	public final static int IS_SSL = 0;

	private String password;

	private String email;

	private String site;

	private String token;

	private String host;

	private Integer port;

	private Integer ssl;

	private String protocol;

	private Integer authType;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the ssl
	 */
	public Integer getSsl() {
		return ssl;
	}

	/**
	 * @param ssl
	 *            the ssl to set
	 */
	public void setSsl(Integer ssl) {
		this.ssl = ssl;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 *            the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the authType
	 */
	public Integer getAuthType() {
		return authType;
	}

	/**
	 * @param authType
	 *            the authType to set
	 */
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}