/**     
 * @Title: EmlResult.java   
 * @Package com.enchantin.crawler.entity.bean   
 * @Description: TODO
 * @author weiwei 
 * @date 2016年11月9日 上午10:38:39   
 * @version V1.0     
 */
package com.weiwei.crawler.entity;

import java.io.Serializable;

/**
 * @ClassName: EmlResult
 * @Description: TODO
 * @author weiwei
 * @date 2016年11月9日 上午10:38:39
 * 
 */
public class EmlResult implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4166163468889109156L;

	/** 文件路径 **/
	private String filePath;

	/** 是否是新文件 **/
	private boolean isNewFile;

	/** 文件的MD5值 **/
	private String fileMd5;

	public EmlResult() {
		super();
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the isNewFile
	 */
	public boolean isNewFile() {
		return isNewFile;
	}

	/**
	 * @param isNewFile
	 *            the isNewFile to set
	 */
	public void setNewFile(boolean isNewFile) {
		this.isNewFile = isNewFile;
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
		return "EmlResult [filePath=" + filePath + ", isNewFile=" + isNewFile + ", fileMd5=" + fileMd5 + "]";
	}
}