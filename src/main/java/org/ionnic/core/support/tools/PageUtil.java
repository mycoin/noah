package org.ionnic.core.support.tools;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.core.support.Config;
import org.ionnic.core.support.config.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class PageUtil {

	private Logger logger = LoggerFactory.getLogger(PageUtil.class);

	private ViewToolContext context;

	private static Config config;

	private static ViewConfig viewConfig;

	/**
	 * @param template
	 * @param contextMap
	 * @return
	 */
	public String eval(String template, Map<?, ?> contextMap) {
		StringWriter writer = new StringWriter();
		Context context = new VelocityContext(contextMap);

		// auto escape template
		if (viewConfig.getTripMode() == ViewConfig.AUTO_ESCAPE) {
			template = escapeTemplate(template);
		}

		if (true == internalEval(writer, context, template, false)) {
			return writer.toString();
		}
		return "<!-- -->";
	}

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
		Properties p = config.getViewMap();
		return p.getProperty(key, defaultValue);
	}

	/**
	 * @param templateName
	 * @return
	 * @throws Exception
	 */
	public String getExternal(String templateName) throws Exception {
		Resource res = viewConfig.getExternalPath();
		return res.getFile().getAbsolutePath() + File.separator + templateName;
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

		// init global config
		if (null == config) {
			config = Config.getInstance();
			viewConfig = config.getViewConfig();
		}
	}

	/**
	 * @param template
	 * @return
	 */
	private String escapeTemplate(String template) {
		template = template.replace("\\\"", "'");
		template = template.replace("\\n", "\n");
		template = template.replace("\\'", "\"");
		template = template.replace("\\t", "\t");
		return template;
	}

	/**
	 * 内部渲染函数
	 * 
	 * @param writer
	 * @param dataMap
	 * @param stringValue
	 * @param threadSafe
	 * @return
	 */
	private boolean internalEval(StringWriter writer, Context dataMap, String stringValue, boolean threadSafe) {
		boolean result = false;
		VelocityEngine engine;

		if (stringValue == null || "".equals(stringValue)) {
			return false;
		}

		// Whether to share a VelocityEngine instance
		if (threadSafe == true) {
			engine = new VelocityEngine();
		} else {
			engine = context.getVelocityEngine();
		}

		try {
			Map<String, Object> toolbox = context.getToolbox();
			for (String key : toolbox.keySet()) {
				Object value = toolbox.get(key);
				if (value instanceof PageUtil) {
					dataMap.put(key, this);
				} else {
					dataMap.put(key, toolbox.get(key));
				}
			}
			result = engine.evaluate(dataMap, writer, "RenderTool.eval()", stringValue);
		} catch (Exception e) {
			logger.error("internalEval() error.", e);
		}
		return result;
	}
}
