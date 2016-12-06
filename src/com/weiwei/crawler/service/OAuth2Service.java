/**     
 * @Title: OAuth2Service.java   
 * @Package com.weiwei.crawler.service   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年12月1日 下午12:44:11   
 * @version V1.0     
 */
package com.weiwei.crawler.service;

import java.util.HashMap;
import java.util.Map;

import com.unclefool.easyoauth.builder.ServiceBuilder;
import com.unclefool.easyoauth.builder.api.GoogleApi;
import com.unclefool.easyoauth.builder.api.LiveApi;
import com.unclefool.easyoauth.builder.api.YahooApi;
import com.unclefool.easyoauth.model.TokenResponse;
import com.unclefool.easyoauth.model.Verifier;
import com.unclefool.easyoauth.oauth.OAuthService;
import com.unclefool.easyoauth.oauth.OAuthService.GrantType;
import com.weiwei.crawler.entity.MailboxAuthenticate;
import com.weiwei.crawler.utils.PropertiesUtils;
import com.weiwei.crawler.utils.StringUtils;

/**
 * @ClassName: OAuth2Service
 * @Description: 目前仅支持yahoo、outlook和gmail
 * @author weiwei
 * @date 2016年12月1日 下午12:44:11
 * 
 */
public class OAuth2Service {

	final static String YAHOO_SITE = "yahoo";

	final static String OUTLOOK_SITE = "outlook";

	final static String GMAIL_SITE = "gmail";

	private static Map<String, Class> API_CLASS;

	static {
		API_CLASS = new HashMap<String, Class>();
		API_CLASS.put(YAHOO_SITE, YahooApi.class);
		API_CLASS.put(GMAIL_SITE, GoogleApi.class);
		API_CLASS.put(OUTLOOK_SITE, LiveApi.class);
	}

	/**
	 * @Title: getAccessToken
	 * @Description: 获取accessToken
	 * @param refreshToken
	 * @param site
	 * @Reutrn String
	 */
	public String getAccessToken(String refreshToken, String site) {
		return getAccessToken(refreshToken, site, null);
	}

	/**
	 * @Title: getAccessToken
	 * @Description: 获取accessToken
	 * @param refreshToken
	 * @param site
	 * @param mailboxAuthenticate
	 * @Reutrn String
	 */
	public String getAccessToken(String refreshToken, String site, MailboxAuthenticate mailboxAuthenticate) {
		if (StringUtils.isNotBlank(site) && StringUtils.isNotBlank(refreshToken)) {
			if (null == mailboxAuthenticate) {
				mailboxAuthenticate = PropertiesUtils.getMailboxAuthenticateBySite(site);
			}
			if (null != mailboxAuthenticate) {
				String apiKey = mailboxAuthenticate.getMailApiKey();
				String apiSecret = mailboxAuthenticate.getMailApiSecret();
				String callBack = mailboxAuthenticate.getCallBack();
				String scope = mailboxAuthenticate.getScope();
				try {
					Class apiClass = API_CLASS.get(site);
					if (null != apiClass) {
						ServiceBuilder builder = new ServiceBuilder().provider(apiClass).apiKey(apiKey).apiSecret(apiSecret);
						if (StringUtils.isNotBlank(callBack)) {
							builder.callback(callBack);
						}
						if (StringUtils.isNotBlank(scope)) {
							builder.scope(scope);
						}
						if (null != builder) {
							OAuthService service = builder.build();
							if (null != service) {
								TokenResponse tokenRsp = service.getTokenResponse(GrantType.TOKEN, new Verifier(refreshToken));
								if (null != tokenRsp) {
									return tokenRsp.getAccessToken();
								}
							}
						}
					}
				} catch (Exception e) {
				}
			}
			return null;
		}
		return null;
	}
}
