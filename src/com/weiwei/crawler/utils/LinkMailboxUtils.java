/**   
 * @Title: LinkMailboxUtils.java 
 * @Package com.enchantin.endzone.util 
 * @Description: TODO
 * @author xaoyaoyao
 * @date 2015-12-22 下午7:39:10 
 * @version V1.0   
 */
package com.weiwei.crawler.utils;

import java.security.Security;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

/**
 * @author xaoyaoyao
 * @Date 2015-12-22
 */
public class LinkMailboxUtils {

	private final static Logger logger = Logger.getLogger(LinkMailboxUtils.class);

	/** JavaMail IMAP服务 **/
	public final static int IMAP_PROTOCOL_TYPE = 0;

	/** JavaMail POP3服务 **/
	public final static int POP3_PROTOCOL_TYPE = 1;

	/** JavaMail IMAP服务 **/
	public final static String IMAP_PROTOCOL = "imap";

	/** JavaMail POP3服务 **/
	public final static String POP3_PROTOCOL = "pop3";

	/** JavaMail IMAP服务 **/
	public final static String IMAPS_PROTOCOL = "imaps";

	/** JavaMail POP3服务 **/
	public final static String POP3S_PROTOCOL = "pop3s";

	/** JavaMail IMAP默认993端口 **/
	public final static int IMAPS_DEFAULT_PORT = 993;

	public final static int IMAP_DEFAULT_PORT = 143;

	/** JavaMail POP3默认995端口 **/
	public final static int POP3S_DEFAULT_PORT = 995;

	public final static int POP3_DEFAULT_PORT = 110;

	/** SSL连接 **/
	public final static int IS_SSL = 0;

	public final static int PROTOCOL_SIZE = 4;

	/**
	 * @Title: getStoreByPasswd
	 * @Description: 密码登录
	 * @param properties
	 * @param protocol
	 * @param host
	 * @param user
	 * @param password
	 * @param isSSL
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 * @return Store
	 */
	public static Store getStoreByPasswd(Properties properties, String protocol, String host, String user, String password, boolean isSSL)
			throws NoSuchProviderException, MessagingException {
		if (isSSL) {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		}
		if (null == properties) {
			properties = new Properties();
		}
		Session session = Session.getDefaultInstance(properties);
		if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(protocol) && StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
			user = handleSpecialMailbox(user);
			Store store = session.getStore(protocol);
			store.connect(host, user, password);
			return store;
		}
		return null;
	}

	/**
	 * @Title: getStoreByOauth2
	 * @Description: Oauth2登录
	 * @param props
	 * @param host
	 * @param user
	 * @param accessToken
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 * @return Store
	 */
	public static Store getStoreByOauth2(Properties props, String host, String user, String accessToken) throws NoSuchProviderException, MessagingException {
		if (null == props) {
			props = new Properties();
		}
		props.put("mail.imap.ssl.enable", "true"); // required for Gmail
		props.put("mail.imap.sasl.enable", "true");
		props.put("mail.imap.sasl.mechanisms", "XOAUTH2");
		props.put("mail.imap.auth.login.disable", "true");
		props.put("mail.imap.auth.plain.disable", "true");
		Session session = Session.getInstance(props);
		session.setDebug(false);
		Store store = session.getStore(IMAP_PROTOCOL);
		if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(accessToken) && StringUtils.isNotBlank(user)) {
			store.connect(host, user, accessToken);
			return store;
		}
		return null;
	}

	/**
	 * @Title: getALLMessageINBOX
	 * @Description: 获取INBOX的邮件总数
	 * @param store
	 * @return long
	 */
	public static long getALLMessageINBOX(Store store) {
		if (null != store) {// 返回空则表示已经连接上Email
			try {
				Folder folder = getFolderByInBoxAndReadOnly(store);
				Message[] messages = null;
				if (null != folder) {
					messages = folder.getMessages();
				}
				close(store, folder);
				if (null != messages) {
					return messages.length;
				}
			} catch (MessagingException e) {
				logger.error("getALLMessageINBOX MessagingException: " + e.getMessage(), e);
			}
		}
		return 0;
	}

	/**
	 * @Title: getMessage
	 * @Description: 获取邮件
	 * @param start
	 * @param end
	 * @param folder
	 * @return Message[]
	 */
	public static Message[] getMessage(int start, int end, Folder folder) {
		if (null != folder) {
			try {
				int totalMessages = folder.getMessageCount();
				start = start > 1 ? start > totalMessages ? totalMessages : start : 1;
				end = end > start ? end : start;
				end = end > totalMessages ? totalMessages : end;
				Message[] messages = folder.getMessages(start, end);
				close(folder);
				return messages;
			} catch (MessagingException e) {
				logger.error("getMessage MessagingException: " + e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * @Title: getMessage
	 * @Description: 获取从start到最新邮件
	 * @param start
	 * @param folder
	 * @return Message[]
	 */
	public static Message[] getMessage(int start, Folder folder) {
		if (null != folder) {
			try {
				int totalMessages = folder.getMessageCount();
				start = start > 1 ? start > totalMessages ? totalMessages : start : 1;
				Message[] messages = folder.getMessages(start, totalMessages);
				close(folder);
				return messages;
			} catch (MessagingException e) {
				logger.error("getMessage MessagingException: " + e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * @Title: getTopNMessage
	 * @Description: 取TopN条数据
	 * @param top
	 * @param folder
	 * @return Message[]
	 */
	public static Message[] getTopNMessage(int top, Folder folder) {
		if (null != folder) {
			try {
				int totalMessages = folder.getMessageCount();
				int start = top > totalMessages ? 1 : totalMessages - top;
				Message[] messages = folder.getMessages(start, totalMessages);
				close(folder);
				return messages;
			} catch (MessagingException e) {
				logger.error("getMessage MessagingException: " + e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * @Title: getTopNMessage
	 * @Description: 取TopN条数据
	 * @param top
	 * @param store
	 * @return Message[]
	 */
	public static Message[] getTopNMessage(int top, Store store) {
		if (null != store) {
			try {
				Folder folder = getFolderByInBoxAndReadOnly(store);
				Message[] messages = getMessage(top, folder);
				close(store);
				return messages;
			} catch (MessagingException e) {
				logger.error("getTopNMessage MessagingException: " + e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * @Title: getMessage
	 * @Description: 获取邮件
	 * @param start
	 * @param end
	 * @param store
	 * @return Message[]
	 */
	public static Message[] getMessage(int start, int end, Store store) {
		if (null != store) {
			try {
				Folder folder = getFolderByInBoxAndReadOnly(store);
				Message[] messages = getMessage(start, end, folder);
				close(store);
				return messages;
			} catch (MessagingException e) {
				logger.error("getMessage MessagingException: " + e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * @Title: getMessage
	 * @Description: 获取从start到最新邮件
	 * @param start
	 * @param store
	 * @return Message[]
	 */
	public static Message[] getMessage(int start, Store store) {
		if (null != store) {
			try {
				Folder folder = getFolderByInBoxAndReadOnly(store);
				Message[] messages = getMessage(start, folder);
				close(store);
				return messages;
			} catch (MessagingException e) {
				logger.error("getMessage MessagingException: " + e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * @Title: close
	 * @Description: 关闭
	 * @param store
	 * @param folder
	 * @return void
	 */
	public static void close(Store store, Folder folder) {
		close(folder);
		close(store);
	}

	/**
	 * @Title: closeStore
	 * @Description: 关闭Store
	 * @param store
	 * @return void
	 */
	public static void close(Store store) {
		try {
			if (null != store) {
				store.close();
			}
		} catch (MessagingException e) {
			logger.error("closeStore MessagingException: " + e.getMessage(), e);
		}
	}

	/**
	 * @Title: closeFolder
	 * @Description: 关闭Folder
	 * @param folder
	 * @return void
	 */
	public static void close(Folder folder) {
		if (null != folder && folder.isOpen()) {
			try {
				folder.close(false);
			} catch (MessagingException e) {
				logger.error("closeFolder MessagingException: " + e.getMessage(), e);
			}
		}
	}

	/**
	 * @Title: getFolderByInBoxAndReadOnly
	 * @Description: TODO
	 * @param store
	 * @throws MessagingException
	 * @return Folder
	 */
	private static Folder getFolderByInBoxAndReadOnly(Store store) throws MessagingException {
		if (null != store) {
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			return folder;
		}
		return null;
	}

	/**
	 * @Title: handleSpecialMailbox
	 * @Description: 处理一些特殊订单：iCloud.com和me.com
	 * @param email
	 * @return String
	 */
	public static String handleSpecialMailbox(String email) {
		if (StringUtils.isNotBlank(email)) {
			Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@[iCloud\\.com|me\\.com|icloud\\.com]+");
			Matcher m = p.matcher(email);
			if (m.matches()) {
				p = Pattern.compile("([^@]*)@([^@]*)");
				m = p.matcher(email);
				if (m.find()) {
					String name = m.group(1);
					if (StringUtils.isNotBlank(name)) {
						return name;
					}
				}
			}
		}
		return email;
	}
}