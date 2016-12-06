/**     
 * @Title: AppEntry.java   
 * @Package com.weiwei.crawler.entry   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年12月1日 下午12:24:20   
 * @version V1.0     
 */
package com.weiwei.crawler.entry;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.weiwei.crawler.download.MailboxDownload;
import com.weiwei.crawler.entity.EmlResult;
import com.weiwei.crawler.entity.MailboxInfo;
import com.weiwei.crawler.entity.SpiderSeed;
import com.weiwei.crawler.entity.SpiderTask;
import com.weiwei.crawler.parse.MailboxParser;
import com.weiwei.crawler.utils.ReadFileUtils;

/**
 * @ClassName: AppEntry
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午12:24:20
 * 
 */
public class AppEntry {

	/**
	 * @Title: downloadByPasswd
	 * @Description: 密码登录方式，文件默认下载在mailbox目录下
	 * @Reutrn void
	 */
	static void downloadByPasswd() {
		SpiderTask task = new SpiderTask();
		task.setEmail("XXXXXXX@yahoo.com");
		task.setSite("yahoo");
		task.setHost("imap.mail.yahoo.com");
		task.setPassword("XXXXXXXXXXXX");
		task.setAuthType(SpiderTask.OAUTH_BY_PASSWORD);
		task.setPort(993);
		task.setProtocol("imaps");
		task.setSsl(0);// SSL连接
		MailboxDownload md = new MailboxDownload();
		Properties props = new Properties();
		props.put("mail.store.protocol", "imap");
		props.put("mail.imap.socketFactory.fallback", "false");
		props.put("mail.imap.socketFactory.port", "993");
		props.put("mail.imaps.port", "993");
		props.put("mail.imaps.starttls.enable", "true");
		props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// TODO 若是邮件需要配置信息，则如上配置Properties即可，不需要则直接传null
		List<EmlResult> emlResults = md.download(task, props);
		System.out.println(emlResults);
	}

	/**
	 * @Title: downloadByOAuth2
	 * @Description: OAuth登录方式，文件默认下载在mailbox目录下
	 * @Reutrn void
	 */
	static void downloadByOAuth2() {
		SpiderTask task = new SpiderTask();
		task.setEmail("XXXXXXX@yahoo.com");
		task.setSite("yahoo");
		task.setToken("AXXXXXXXXX");
		task.setAuthType(SpiderTask.OAUTH_BY_OAUTH2);
		MailboxDownload md = new MailboxDownload();
		List<EmlResult> emlResults = md.download(task, null);
		System.out.println(emlResults);
	}

	/**
	 * @Title: parse
	 * @Description: 解析
	 * @Reutrn void
	 */
	static void parse() {
		try {
			MailboxParser mp = new MailboxParser();
			// siteRegex,site,regexStep,tmplStep不能为空
			SpiderSeed seed = new SpiderSeed();
			seed.setRegexStep2("([\\s\\S]*)USPS Item Departed Shipping Partner Facility, USPS Awaiting Item for Shipment([\\s\\S]*)");
			seed.setTmplStep2(ReadFileUtils.readTemplateContent("usps-shipper.xml"));
			seed.setWebSite("USPS");
			seed.setSiteRegex("(@(?i)usps\\.(?i)com)[\\s\\S]*");
			mp.parse(ReadFileUtils.getEmlPath("201612011473a9c8235eb1afb915747d5bb766efec.eml"), seed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void parser() {
		try {
			// 主题
			String subject = "USPS Item Departed Shipping Partner Facility, USPS Awaiting Item for Shipment 9361289676090186507414";
			// 发件人
			String from = "auto-reply@usps.com";
			// html文件内容
			String content = ReadFileUtils.readHtml("2016120612bd67538cc53005167199ec5989d11afd.html");
			MailboxParser mp = new MailboxParser();
			MailboxInfo mailboxInfo = new MailboxInfo();
			mailboxInfo.setSubject(subject);
			mailboxInfo.setFrom(from);
			mailboxInfo.setContent(content);
			SpiderSeed seed = new SpiderSeed();
			seed.setRegexStep2("([\\s\\S]*)USPS Item Departed Shipping Partner Facility, USPS Awaiting Item for Shipment([\\s\\S]*)");
			seed.setTmplStep2(ReadFileUtils.readTemplateContent("usps-shipper.xml"));
			seed.setWebSite("USPS");
			seed.setSiteRegex("(@(?i)usps\\.(?i)com)[\\s\\S]*");
			mp.parser(mailboxInfo, seed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @Reutrn void
	 */
	public static void main(String[] args) {
		// 密码登录方式
		// downloadByPasswd();
		// OAuth登录方式
		// downloadByOAuth2();
		// 解析
		// parse();
		parser();
	}
}