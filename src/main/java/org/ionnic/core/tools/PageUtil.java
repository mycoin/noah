package org.ionnic.core.tools;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.core.GlobalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author apple
 * 
 */
public class PageUtil {

	private Logger logger = LoggerFactory.getLogger(PageUtil.class);

	private ViewToolContext context;

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
	private boolean evaluateString(StringWriter writer, Map<String, Object> dataMap, String stringValue, boolean threadSafe, boolean escape) {
		VelocityEngine engine = null;

		// Whether to share a VelocityEngine instance
		if (threadSafe == true) {
			engine = new VelocityEngine();
		} else {
			engine = context.getVelocityEngine();
		}
		Map<String, Object> toolbox = context.getToolbox();

		dataMap.putAll(toolbox);
		Context contextMap = new VelocityContext(dataMap);
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

		GlobalConfig.getInstance().getViewConfig();
	}

	@Override
	public String toString() {
		return "";
	}

}
