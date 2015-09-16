package org.ionnic.core.bean;

/**
 * 过滤器配置信息
 * 
 * @author apple
 */
public class FilterConfig {

	// 隐藏HTTP method 的隐藏域名称
	private String hiddenMethodName;

	// 默认的编码方式
	private String charset;

	// refer 白名单
	private String[] allowFromDomain;

	// 开启隐藏HTTP方式隐藏字段
	private boolean enableHiddenMethod = true;

	// 强制转换编码方式
	private boolean forceEncoding;

	/**
	 * @return the allowFromDomain
	 */
	public String[] getAllowFromDomain() {
		return allowFromDomain;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @return the hiddenMethodName
	 */
	public String getHiddenMethodName() {
		return hiddenMethodName;
	}

	/**
	 * @return the enableHiddenMethod
	 */
	public boolean isEnableHiddenMethod() {
		return enableHiddenMethod;
	}

	/**
	 * @return the forceEncoding
	 */
	public boolean isForceEncoding() {
		return forceEncoding;
	}

	/**
	 * @param allowFromDomain
	 *            the allowFromDomain to set
	 */
	public void setAllowFromDomain(String[] allowFromDomain) {
		this.allowFromDomain = allowFromDomain;
	}

	/**
	 * @param charset
	 *            the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @param enableHiddenMethod
	 *            the enableHiddenMethod to set
	 */
	public void setEnableHiddenMethod(boolean enableHiddenMethod) {
		this.enableHiddenMethod = enableHiddenMethod;
	}

	/**
	 * @param forceEncoding
	 *            the forceEncoding to set
	 */
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}

	/**
	 * @param hiddenMethodName
	 *            the hiddenMethodName to set
	 */
	public void setHiddenMethodName(String hiddenMethodName) {
		this.hiddenMethodName = hiddenMethodName;
	}
}
