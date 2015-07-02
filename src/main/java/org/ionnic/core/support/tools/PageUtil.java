package org.ionnic.core.support.tools;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.core.support.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUtil {

	public Logger logger = LoggerFactory.getLogger(PageUtil.class);

	public static final String DEFAULT_KEY = "app";

	private ViewToolContext context;

	private static Config config = Config.getInstance();

	/**
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		return getConfig(key, "");
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getConfig(String key, String defaultValue) {
		Properties p = config.getViewConfig();
		return p.getProperty(key, defaultValue);
	}

	/**
	 * @return
	 */
	public ViewToolContext getContext() {
		if (context == null) {
			logger.error("ViewToolContext cannot be null.");
		}
		return context;
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void init(Object obj) {
		context = (ViewToolContext) obj;

		System.out.println(config.getViewConfig());

		if (logger.isDebugEnabled()) {
			logger.debug("PageUtil.init() invoked.");
		}
	}

	/**
	 * @param context
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String eval(Context context, String content) throws Exception {
		if (content == null) {
			return null;
		}

		StringWriter sw = new StringWriter();
		boolean success;
		success = Velocity.evaluate(context, sw, "RenderTool.eval()", content);
		if (success) {
			return sw.toString();
		}
		return null;
	}

	// public String test() {
	// System.out.println(context.getVelocityEngine().resourceExists("ad.vm"));
	//
	// return "11";
	// }

	public String toString() {
		return "";
	}
}
