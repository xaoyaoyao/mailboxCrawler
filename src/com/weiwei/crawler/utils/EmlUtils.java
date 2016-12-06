/**     
 * @Title: EmlUtils.java   
 * @Package com.enchantin.crawler.util   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年11月8日 下午5:23:29   
 * @version V1.0     
 */
package com.weiwei.crawler.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.weiwei.crawler.entity.EmlResult;
import com.weiwei.crawler.entity.MailboxInfo;

/**
 * @ClassName: EmlUtils
 * @Description: eml文件处理类
 * @author weiwei
 * @date 2016年11月8日 下午5:23:29
 * 
 */
public class EmlUtils {

	/**
	 * @Title: writeFileToEml
	 * @Description: 邮件生成eml文件格式
	 * @param rootPath
	 *            文件保存根路径
	 * @param mailbox
	 *            谁的邮件:邮箱
	 * @param message
	 *            邮件
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 * @Reutrn EmlResult
	 */
	public static EmlResult writeFileToEml(String rootPath, String mailbox, Message message) throws FileNotFoundException, IOException, MessagingException {
		EmlResult emlResult = new EmlResult();
		if (StringUtils.isNotBlank(rootPath) && null != message && StringUtils.isNotBlank(mailbox)) {
			if (!rootPath.endsWith("/")) {
				rootPath += "/";
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);
			byte[] buf = baos.toByteArray();
			String fileMd5 = DigestUtils.md5Hex(buf);
			emlResult.setFileMd5(fileMd5);
			String filePath = rootPath + StringUtils.cleanSymbol2(mailbox) + "/";
			File rootFile = new File(filePath);
			if (!rootFile.exists()) {
				rootFile.mkdirs();
			}
			String filePathAndName = filePath + fileMd5 + MailboxUtils.EXTENSION_EML;
			emlResult.setFilePath(filePathAndName);
			File file = new File(filePathAndName);
			if (file.exists()) {// 存在则直接返回
				emlResult.setNewFile(false);
				return emlResult;
			}
			FileUtils.writeByteArrayToFile(file, buf);
			emlResult.setNewFile(true);
		}
		return emlResult;
	}

	/**
	 * @Title: writeFileToEmlAndHtml
	 * @Description: 邮件生成eml文件格式
	 * @param rootPath
	 *            文件保存根路径
	 * @param message
	 *            邮件
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 * @Reutrn EmlResult
	 */
	public static EmlResult writeFileToEmlAndHtml(String rootPath, Message message) throws FileNotFoundException, IOException, MessagingException {
		EmlResult emlResult = new EmlResult();
		if (StringUtils.isNotBlank(rootPath) && null != message) {
			if (!rootPath.endsWith("/")) {
				rootPath += "/";
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);
			byte[] buf = baos.toByteArray();
			String fileMd5 = DigestUtils.md5Hex(buf);
			emlResult.setFileMd5(fileMd5);
			String filePath = rootPath;
			File rootFile = new File(filePath);
			if (!rootFile.exists()) {
				rootFile.mkdirs();
			}
			String filePathAndName = filePath + DateUtils.getSimpleDate2(new Date()) + fileMd5 + MailboxUtils.EXTENSION_EML;
			emlResult.setFilePath(filePathAndName);
			File file = new File(filePathAndName);
			if (file.exists()) {// 存在则直接返回
				emlResult.setNewFile(false);
				return emlResult;
			}
			FileUtils.writeByteArrayToFile(file, buf);
			emlResult.setNewFile(true);
			// 同时生产html文件
			writeToHtml(message, fileMd5, filePath);
		}
		return emlResult;
	}

	/**
	 * @Title: writeToHtml
	 * @Description: 生产html文件
	 * @param message
	 * @param fileMd5
	 * @param filePath
	 * @Reutrn void
	 */
	private static void writeToHtml(Message message, String fileMd5, String filePath) {
		try {
			MailboxInfo mailboxInfo = readMailbox((MimeMessage) message);
			if (null != mailboxInfo && StringUtils.isNotBlank(mailboxInfo.getContent())) {
				filePath = filePath.replace("mailbox", "html");
				String htmlFilePathAndName = filePath + DateUtils.getSimpleDate2(new Date()) + fileMd5 + MailboxUtils.EXTENSION_HTML;
				File htmlFile = new File(htmlFilePathAndName);
				FileUtils.writeByteArrayToFile(htmlFile, IOUtils.toByteArray(IOUtils.toInputStream(mailboxInfo.getContent())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: readEmlToMailboxInfo
	 * @Description: 读取eml文件
	 * @param fileName
	 *            eml文件
	 * @throws FileNotFoundException
	 * @throws MessagingException
	 * @throws Exception
	 * @Reutrn MailboxInfo
	 */
	public static MailboxInfo readEmlToMailboxInfo(String fileName) throws FileNotFoundException, MessagingException, Exception {
		if (StringUtils.isNotBlank(fileName) && fileName.toLowerCase().trim().endsWith(MailboxUtils.EXTENSION_EML)) {
			InputStream source = new FileInputStream(new File(fileName));
			if (null != source) {
				Session session = Session.getDefaultInstance(System.getProperties(), null);
				MimeMessage message = new MimeMessage(session, source);
				if (null != message) {
					return readMailbox(message);
				}
			}
		}
		return null;
	}

	/**
	 * @Title: readMailbox
	 * @Description: 读取文件
	 * @param message
	 * @throws MessagingException
	 * @throws Exception
	 * @Reutrn MailboxInfo
	 */
	private static MailboxInfo readMailbox(MimeMessage message) throws MessagingException, Exception {
		if (null == message) {
			return null;
		}
		MailboxInfo info = new MailboxInfo();
		info.setFrom(MailboxUtils.getAddress((InternetAddress[]) message.getFrom()));
		Address address = message.getSender();
		String sender = null;
		if (null != address) {
			try {
				InternetAddress ia = (InternetAddress) address;
				sender = ia.getAddress();
			} catch (Exception e) {
			}
		}
		info.setSender(sender);
		info.setMessageId(message.getMessageID());
		info.setSubject(message.getSubject());
		info.setReceivedDate(message.getReceivedDate());
		info.setSentDate(message.getSentDate());
		info.setTo(MailboxUtils.getMailAddress("to", message));
		info.setCc(MailboxUtils.getMailAddress("cc", message));
		info.setBcc(MailboxUtils.getMailAddress("bcc", message));
		info.setMessage(message);
		// 读取邮件头部内容和正文
		StringBuffer headText = new StringBuffer();
		StringBuffer bodyText = new StringBuffer();
		MailboxUtils.parseMailMessages(headText, bodyText, message);
		info.setHeaderContent(headText.toString());
		info.setContent(bodyText.toString());
		return info;
	}

	public static void main(String[] args) throws FileNotFoundException, MessagingException, Exception {
		String fileName = ReadFileUtils.getEmlPath("1f89be360972f7ecae59413355733987.eml");
		System.out.println(fileName);
		MailboxInfo info = readEmlToMailboxInfo(fileName);
		System.out.println(info);
	}
}