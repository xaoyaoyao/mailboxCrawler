/**     
 * @Title: MailboxUtils.java   
 * @Package com.enchantin.crawler.util   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年11月8日 下午6:57:00   
 * @version V1.0     
 */
package com.weiwei.crawler.utils;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

/**
 * @ClassName: MailboxUtils
 * @Description: TODO
 * @author weiwei
 * @date 2016年11月8日 下午6:57:00
 * 
 */
public class MailboxUtils {
	
	private static final Logger LOG = Logger.getLogger(MailboxUtils.class);

	public final static String EXTENSION_EML = ".eml";
	
	public final static String EXTENSION_HTML = ".html";

	/**
	 * @Title: getAddress
	 * @Description: 获取Address
	 * @param address
	 * @return String
	 */
	public static String getAddress(InternetAddress[] address) {
		if (null != address && address.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int index = 0; index < address.length; index++) {
				sb.append(address[index].getAddress());
				if (index < address.length - 1) {
					sb.append(Constants.SEPARATOR_PRDUCT_NAME);
				}
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * @Title: getMailAddress
	 * @Description: 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同
	 * @param type
	 *            "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址
	 * @param message
	 * @return
	 * @throws Exception
	 * @return String
	 */
	public static String getMailAddress(String type, MimeMessage message) throws Exception {
		if (null == message) {
			return null;
		}
		String mailAddr = "";
		try {
			String addType = type.toUpperCase();
			InternetAddress[] address = null;
			if (addType.equals("TO") || addType.equals("CC") || addType.equals("BCC")) {
				if (addType.equals("TO")) {
					address = (InternetAddress[]) message.getRecipients(Message.RecipientType.TO);
				} else if (addType.equals("CC")) {
					address = (InternetAddress[]) message.getRecipients(Message.RecipientType.CC);
				} else {
					address = (InternetAddress[]) message.getRecipients(Message.RecipientType.BCC);
				}
				if (address != null) {
					for (int i = 0; i < address.length; i++) {
						String emailAddr = address[i].getAddress();
						if (StringUtils.isNotBlank(emailAddr)) {
							emailAddr = MimeUtility.decodeText(emailAddr);
						}
						if (StringUtils.isNotBlank(emailAddr)) {
							if (StringUtils.isBlank(mailAddr)) {
								mailAddr += emailAddr;
							} else {
								mailAddr += Constants.MAIL_SEPARATOR + emailAddr;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("getMailAddress err: " + e.getMessage(), e);
		}
		if (StringUtils.isNotBlank(mailAddr)) {
			return mailAddr.trim();
		}
		return null;
	}

	/**
	 * @Title: parseMailMessages
	 * @Description: 读取邮件头部内容和正文
	 * @param headText
	 * @param bodyText
	 * @param part
	 * @return void
	 */
	public static void parseMailMessages(StringBuffer headText, StringBuffer bodyText, Part part) {
		if (null == part) {
			return;
		}
		if (null == headText) {
			headText = new StringBuffer();
		}
		if (null == bodyText) {
			bodyText = new StringBuffer();
		}
		try {
			String contenttype = part.getContentType();
			boolean conname = false;
			if (StringUtils.isNotBlank(contenttype)) {
				int nameindex = contenttype.indexOf("name");
				if (nameindex != -1) {
					conname = true;
				}
			}
			if (part.isMimeType("text/plain") && !conname) {
				headText.append((String) part.getContent());
			} else if (part.isMimeType("text/html") && !conname) {
				bodyText.append((String) part.getContent());
			} else if (part.isMimeType("multipart/*")) {
				Multipart multipart = (Multipart) part.getContent();
				int counts = multipart.getCount();
				for (int i = 0; i < counts; i++) {
					parseMailMessages(headText, bodyText, multipart.getBodyPart(i));
				}
			} else if (part.isMimeType("message/rfc822")) {
				parseMailMessages(headText, bodyText, (Part) part.getContent());
			}
		} catch (MessagingException e) {
			LOG.error("MessagingException parseMailMessages err: " + e.getMessage(), e);
		} catch (IOException e) {
			LOG.error("IOException parseMailMessages err: " + e.getMessage(), e);
		}
	}
}