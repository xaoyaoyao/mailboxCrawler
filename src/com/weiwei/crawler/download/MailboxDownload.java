/**     
 * @Title: MailboxDownload.java   
 * @Package com.weiwei.crawler.download   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年12月1日 下午2:00:21   
 * @version V1.0     
 */
package com.weiwei.crawler.download;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.UIDFolder;

import org.apache.log4j.Logger;

import com.sun.mail.gimap.GmailFolder;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Folder;
import com.weiwei.crawler.entity.EmlResult;
import com.weiwei.crawler.entity.SpiderTask;
import com.weiwei.crawler.service.StoreService;
import com.weiwei.crawler.utils.Constants;
import com.weiwei.crawler.utils.EmlUtils;
import com.weiwei.crawler.utils.ReadFileUtils;

/**
 * @ClassName: MailboxDownload
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午2:00:21
 * 
 */
public class MailboxDownload {

	private static final Logger LOG = Logger.getLogger(MailboxDownload.class);

	public List<EmlResult> download(SpiderTask task, Properties properties) {
		try {
			if (null != task) {
				StoreService storeService = new StoreService();
				Store store = storeService.linkMailboxToStore(task, properties);
				if (null != store) {
					return download(task, store);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	private List<EmlResult> download(SpiderTask task, Store store) throws Exception {
		if (null == task || null == store) {
			throw new Exception("Task或者Store为空");
		}
		Folder folder = null;
		try {
			// 值获取INBOX
			folder = store.getFolder(Constants.EMAIL_INBOX);
			if (null == folder) {
				return null;
			}
			folder.open(Folder.READ_ONLY);
			// 邮件总数
			int totalMessages = folder.getMessageCount();
			if (LOG.isInfoEnabled()) {
				LOG.info("===============总共有" + totalMessages + "封邮件===================");
			}
			int start = 0, end = 0;
			if (totalMessages > 0) {
				start = totalMessages - Constants.EACH_READ_MAIL_SIZE;
				start = start > 0 ? start : 1;
				end = totalMessages;
			}
			return readMailbox(store, folder, task.getEmail(), totalMessages, start, end);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (null != folder && folder.isOpen()) {
				try {
					folder.close(false);
					if (null != store) {
						store.close();
					}
				} catch (MessagingException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	private List<EmlResult> readMailbox(Store store, Folder folder, String email, int totalMessages, int start, int end) {
		return readMailbox(store, folder, start, end, email);
	}

	private List<EmlResult> readMailbox(Store store, Folder folder, int start, int end, String email) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("readMailbox email=" + email + " start=" + start + ", end=" + end);
		}
		try {
			if (folder instanceof POP3Folder) {
				POP3Folder inbox = (POP3Folder) folder;
				Message[] messages = inbox.getMessages(start, end);
				return readMailbox(messages, email);
			} else if (folder instanceof IMAPFolder || folder instanceof GmailFolder) {
				IMAPFolder inbox = (IMAPFolder) folder;
				Message[] messages = inbox.getMessages(start, end);
				FetchProfile fp = new FetchProfile();
				fp.add(FetchProfile.Item.ENVELOPE);
				fp.add(FetchProfile.Item.FLAGS);
				fp.add(FetchProfile.Item.SIZE);
				fp.add(UIDFolder.FetchProfileItem.UID);
				fp.add(IMAPFolder.FetchProfileItem.HEADERS);
				fp.add(IMAPFolder.FetchProfileItem.MESSAGE);
				fp.add(UIDFolder.FetchProfileItem.CONTENT_INFO);
				inbox.fetch(messages, fp);
				return readMailbox(messages, email);
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("===============no have this folder===============");
				}
			}
		} catch (MessagingException e) {
			LOG.error(e.getMessage() + " start=" + start + ", end=" + end, e);
		}
		return null;
	}

	private List<EmlResult> readMailbox(Message[] messages, String email) {
		if (null != messages && messages.length > 0) {
			List<EmlResult> results = new ArrayList<EmlResult>();
			for (int i = 0; i < messages.length; i++) {
				try {
					EmlResult emlResult = EmlUtils.writeFileToEmlAndHtml(ReadFileUtils.getMailboxPath(), messages[i]);
					if (null != emlResult) {
						results.add(emlResult);
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
			return results;
		}
		return null;
	}
}