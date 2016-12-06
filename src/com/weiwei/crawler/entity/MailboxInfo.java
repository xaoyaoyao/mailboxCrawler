/**   
 * @Title: Mailbox.java 
 * @Package com.enchantin.crawler.entity 
 * @Description: TODO
 * @author xaoyaoyao
 * @date 2015-8-13 下午6:14:49 
 * @version V1.0   
 */
package com.weiwei.crawler.entity;

import java.io.Serializable;
import java.util.Date;

import javax.mail.Message;

/**
 * @ClassName: MailboxInfo
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午1:33:05
 */
public class MailboxInfo implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5162751679802215412L;

	// 邮件messageId
	private String messageId;

	// 邮件UID
	private String messageUid;

	// from
	private String from;

	// sender
	private String sender;

	// 收件人
	private String to;

	// 抄送人
	private String cc;

	// 密送人
	private String bcc;

	// 主题
	private String subject;

	// 邮件头部内容
	private String headerContent;

	// 邮件正文
	private String content;

	// 邮件
	private Message message;

	// 收件时间
	private Date receivedDate;

	// 发送时间
	private Date sentDate;

	// 站点
	private String webSite;

	// 文件md5值
	private String fileMd5;

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
	 * @return the messageUid
	 */
	public String getMessageUid() {
		return messageUid;
	}

	/**
	 * @param messageUid
	 *            the messageUid to set
	 */
	public void setMessageUid(String messageUid) {
		this.messageUid = messageUid;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc
	 *            the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the headerContent
	 */
	public String getHeaderContent() {
		return headerContent;
	}

	/**
	 * @param headerContent
	 *            the headerContent to set
	 */
	public void setHeaderContent(String headerContent) {
		this.headerContent = headerContent;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * @return the receivedDate
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @param receivedDate
	 *            the receivedDate to set
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * @param sentDate
	 *            the sentDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
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
	 * @return the fileMd5
	 */
	public String getFileMd5() {
		return fileMd5;
	}

	/**
	 * @param fileMd5
	 *            the fileMd5 to set
	 */
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	@Override
	public String toString() {
		return "MailboxInfo [messageId=" + messageId + ", messageUid=" + messageUid + ", from=" + from + ", sender=" + sender + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc
				+ ", subject=" + subject + ", headerContent=" + headerContent + ", content=" + content + ", message=" + message + ", receivedDate=" + receivedDate + ", sentDate="
				+ sentDate + ", webSite=" + webSite + ", fileMd5=" + fileMd5 + "]";
	}
}