package org.ionnic.core.tools;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.core.Config;
import org.ionnic.core.bean.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author apple
 * 
 */
public class PageUtil {

	private Logger logger = LoggerFactory.getLogger(PageUtil.class);

	private ViewToolContext context;

	private static ViewConfig viewConfig;

	/**
	 * 内部渲染函数
	 * 
	 * @param writer
	 * @param contextMap
	 * @param stringValue
	 * @param threadSafe
	 * @param escape
	 * @return
	 */
	private boolean evaluateString(StringWriter writer, Map<String, ?> dataMap, String stringValue, boolean threadSafe, boolean escape) {
		VelocityEngine engine = null;

		// Whether to share a VelocityEngine instance
		if (threadSafe == true) {
			engine = new VelocityEngine();
		} else {
			engine = context.getVelocityEngine();
		}

		Map<String, Object> toolbox = context.getToolbox();
		Context contextMap = new VelocityContext(dataMap);

		if (viewConfig.isShareTools()) {
			for (String key : toolbox.keySet()) {
				Object value = toolbox.get(key);
				if (value instanceof PageUtil) {
					contextMap.put(key, this);
				} else {
					contextMap.put(key, toolbox.get(key));
				}
			}
		}

		if (escape) {
			stringValue = StringUtil.escapeSymbol(stringValue);
		}
		return engine.evaluate(contextMap, writer, "PageUtil.eval()", stringValue);
	}

	/**
	 * @param template
	 * @param contextMap
	 * @return
	 */
	public String eval(String template, Map<String, Object> contextMap) {
		StringWriter writer = new StringWriter();

		if (evaluateString(writer, contextMap, template, false, true)) {
			return writer.toString();
		}
		return "<!-- eval:exception -->";
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void init(Object context) {
		this.context = (ViewToolContext) context;

		if (logger.isDebugEnabled()) {
			logger.debug("PageUtil.init() invoked.");
		}

		viewConfig = Config.getInstance().getViewConfig();
	}

	@Override
	public String toString() {
		return "";
	}

}
