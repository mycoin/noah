package org.ionnic.core.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author apple
 * 
 */
public class PropertiesUtil {

	public static String FILE_CONFIG = "classpath:/WEB-INF/conf/context.properties";

	private Properties data = null;

	private String version = "1.0";

	public static void main(String[] args) {
		System.out.println(new PropertiesUtil().get("domain", "vs.baidu.com"));
	}

	/**
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return get(key, null);
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @param force
	 * @return
	 */
	public String get(String key, String defaultValue) {
		String value = null;
		if (data == null) {
			data = new Properties();
			try {
				FileInputStream fis = new FileInputStream(FILE_CONFIG);
				data.load(fis);
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		value = data.getProperty(key);
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}
}
