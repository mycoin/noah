package org.ionnic.core.bean;

public class WebConfig {

	private static String DEFAULT_METHOD_NAME = "method";

	private static String DEFAULT_TOKEN_NAME = "token";

	public static String DEFAULT_CHARSET = "UTF-8";

	// allow From Domain
	private String[] trustDomain;

	// hidden Method Field
	private String methodField = DEFAULT_METHOD_NAME;

	// token name
	private String tokenField = DEFAULT_TOKEN_NAME;

	// charset type
	private String charset = DEFAULT_CHARSET;

	// force Encoding
	private boolean forceEncoding;

	// default DEFAULT_METHOD_NAME
	private String secretKey = DEFAULT_METHOD_NAME;

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @return the methodField
	 */
	public String getMethodField() {
		return methodField;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @return the tokenField
	 */
	public String getTokenField() {
		return tokenField;
	}

	/**
	 * @return the trustDomain
	 */
	public String[] getTrustDomain() {
		return trustDomain;
	}

	/**
	 * @return the forceEncoding
	 */
	public boolean isForceEncoding() {
		return forceEncoding;
	}

	/**
	 * @param charset
	 *            the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @param forceEncoding
	 *            the forceEncoding to set
	 */
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}

	/**
	 * @param methodField
	 *            the methodField to set
	 */
	public void setMethodField(String methodField) {
		this.methodField = methodField;
	}

	/**
	 * @param secretKey
	 *            the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @param tokenField
	 *            the tokenField to set
	 */
	public void setTokenField(String tokenField) {
		this.tokenField = tokenField;
	}

	/**
	 * @param trustDomain
	 *            the trustDomain to set
	 */
	public void setTrustDomain(String[] trustDomain) {
		this.trustDomain = trustDomain;
	}
}
