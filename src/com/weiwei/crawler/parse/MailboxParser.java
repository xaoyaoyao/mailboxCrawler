/**     
 * @Title: MailboxParser.java   
 * @Package com.weiwei.crawler.parse   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年12月1日 下午2:54:55   
 * @version V1.0     
 */
package com.weiwei.crawler.parse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.weiwei.crawler.entity.EmlResult;
import com.weiwei.crawler.entity.MailboxInfo;
import com.weiwei.crawler.entity.SpiderSeed;
import com.weiwei.crawler.entity.SpiderOrder;
import com.weiwei.crawler.extracter.AbstractMailExtracter;
import com.weiwei.crawler.utils.EmlUtils;
import com.weiwei.crawler.utils.OrderUtils;
import com.weiwei.crawler.utils.StringUtils;

/**
 * @ClassName: MailboxParser
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午2:54:55
 * 
 */
public class MailboxParser extends AbstractMailExtracter {

	private static final Logger LOG = Logger.getLogger(MailboxParser.class);

	public List<SpiderOrder> parse(String emlFilePathAndName, SpiderSeed seed) {
		if (StringUtils.isNotBlank(emlFilePathAndName)) {
			EmlResult emlResult = new EmlResult();
			emlResult.setFilePath(emlFilePathAndName);
			emlResult.setFileMd5(System.currentTimeMillis() + "");
			return parse(seed, Arrays.asList(emlResult));
		}
		return null;
	}

	public List<SpiderOrder> parse(SpiderSeed seed, List<EmlResult> lists) {
		if (null != seed && null != lists && !lists.isEmpty()) {
			List<SpiderOrder> dataList = new ArrayList<SpiderOrder>();
			for (EmlResult emlResult : lists) {
				try {
					MailboxInfo mailboxInfo = EmlUtils.readEmlToMailboxInfo(emlResult.getFilePath());
					List<SpiderOrder> orders = parser(mailboxInfo, seed);
					if (null != orders && !orders.isEmpty()) {
						dataList.addAll(orders);
					}
				} catch (FileNotFoundException e) {
					LOG.error(e.getMessage(), e);
				} catch (MessagingException e) {
					LOG.error(e.getMessage(), e);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
			return dataList;
		}
		return null;
	}

	public List<SpiderOrder> parser(MailboxInfo mailboxInfo, SpiderSeed seed) {
		if (null != mailboxInfo && null != seed) {
			boolean result = matchingMessageSite(seed, mailboxInfo);
			if (result) {
				// 解析模板
				return super.extract(mailboxInfo, seed);
			}
		}
		return null;
	}

	private boolean matchingMessageSite(SpiderSeed seed, MailboxInfo info) {
		if (null != seed && null != info) {
			String headText = info.getHeaderContent();
			String subject = info.getSubject();
			String from = info.getFrom();
			String sender = info.getSender();
			String siteRegex = seed.getSiteRegex();
			boolean flag = StringUtils.strRegex(siteRegex, from);
			if (!flag && StringUtils.isNotBlank(sender)) {
				flag = StringUtils.strRegex(siteRegex, sender);
			}
			if (!flag && StringUtils.isNotBlank(subject)) {
				flag = StringUtils.strRegex(siteRegex, subject);
			}
			if (!flag && StringUtils.isNotBlank(headText)) {
				flag = OrderUtils.siteRegexByText(siteRegex, headText, 10);
			}
			return flag;
		}
		return false;
	}
}