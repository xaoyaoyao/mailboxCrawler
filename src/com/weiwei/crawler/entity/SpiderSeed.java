package com.weiwei.crawler.entity;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: Seed
 * @Description: TODO
 * @author weiwei
 * @date 2016年12月1日 下午2:53:53
 */
public class SpiderSeed implements java.io.Serializable {

	private static final long serialVersionUID = 729335599081004614L;

	private String tmplStep1;

	private String tmplStep2;

	private String tmplStep3;

	private String webSite;

	private String siteRegex;

	private String regexStep1;

	private String regexStep2;

	private String regexStep3;

	private String regexOrderNumStep1;

	private String regexOrderNumStep2;

	private String regexOrderNumStep3;

	private String groupRegex;

	public SpiderSeed() {
		super();
	}

	/**
	 * @return the tmplStep1
	 */
	public String getTmplStep1() {
		return tmplStep1;
	}

	/**
	 * @param tmplStep1
	 *            the tmplStep1 to set
	 */
	public void setTmplStep1(String tmplStep1) {
		this.tmplStep1 = tmplStep1;
	}

	/**
	 * @return the tmplStep2
	 */
	public String getTmplStep2() {
		return tmplStep2;
	}

	/**
	 * @param tmplStep2
	 *            the tmplStep2 to set
	 */
	public void setTmplStep2(String tmplStep2) {
		this.tmplStep2 = tmplStep2;
	}

	/**
	 * @return the tmplStep3
	 */
	public String getTmplStep3() {
		return tmplStep3;
	}

	/**
	 * @param tmplStep3
	 *            the tmplStep3 to set
	 */
	public void setTmplStep3(String tmplStep3) {
		this.tmplStep3 = tmplStep3;
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
	 * @return the siteRegex
	 */
	public String getSiteRegex() {
		return siteRegex;
	}

	/**
	 * @param siteRegex
	 *            the siteRegex to set
	 */
	public void setSiteRegex(String siteRegex) {
		this.siteRegex = siteRegex;
	}

	/**
	 * @return the regexStep1
	 */
	public String getRegexStep1() {
		return regexStep1;
	}

	/**
	 * @param regexStep1
	 *            the regexStep1 to set
	 */
	public void setRegexStep1(String regexStep1) {
		this.regexStep1 = regexStep1;
	}

	/**
	 * @return the regexStep2
	 */
	public String getRegexStep2() {
		return regexStep2;
	}

	/**
	 * @param regexStep2
	 *            the regexStep2 to set
	 */
	public void setRegexStep2(String regexStep2) {
		this.regexStep2 = regexStep2;
	}

	/**
	 * @return the regexStep3
	 */
	public String getRegexStep3() {
		return regexStep3;
	}

	/**
	 * @param regexStep3
	 *            the regexStep3 to set
	 */
	public void setRegexStep3(String regexStep3) {
		this.regexStep3 = regexStep3;
	}

	/**
	 * @return the regexOrderNumStep1
	 */
	public String getRegexOrderNumStep1() {
		return regexOrderNumStep1;
	}

	/**
	 * @param regexOrderNumStep1
	 *            the regexOrderNumStep1 to set
	 */
	public void setRegexOrderNumStep1(String regexOrderNumStep1) {
		this.regexOrderNumStep1 = regexOrderNumStep1;
	}

	/**
	 * @return the regexOrderNumStep2
	 */
	public String getRegexOrderNumStep2() {
		return regexOrderNumStep2;
	}

	/**
	 * @param regexOrderNumStep2
	 *            the regexOrderNumStep2 to set
	 */
	public void setRegexOrderNumStep2(String regexOrderNumStep2) {
		this.regexOrderNumStep2 = regexOrderNumStep2;
	}

	/**
	 * @return the regexOrderNumStep3
	 */
	public String getRegexOrderNumStep3() {
		return regexOrderNumStep3;
	}

	/**
	 * @param regexOrderNumStep3
	 *            the regexOrderNumStep3 to set
	 */
	public void setRegexOrderNumStep3(String regexOrderNumStep3) {
		this.regexOrderNumStep3 = regexOrderNumStep3;
	}

	/**
	 * @return the groupRegex
	 */
	public String getGroupRegex() {
		return groupRegex;
	}

	/**
	 * @param groupRegex
	 *            the groupRegex to set
	 */
	public void setGroupRegex(String groupRegex) {
		this.groupRegex = groupRegex;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}