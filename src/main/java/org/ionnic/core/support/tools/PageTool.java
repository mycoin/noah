package org.ionnic.core.support.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.springframework.util.ResourceUtils;

public class PageTool {

	private Logger logger = LoggerFactory.getLogger(PageTool.class);

	private ViewToolContext context;

	private static Config config = Config.getInstance();

	private static ViewConfig viewConfig;

	/**
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static String getTemplate(File filename) throws IOException {
		FileInputStream fis = null;
		InputStreamReader read = null;
		String html = "";
		try {
			fis = new FileInputStream(filename);
			read = new InputStreamReader(fis, Config.CHARSET);
			BufferedReader br = new BufferedReader(read);

			String line = null;
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if (first) {
					html = line;
				} else {
					html += line + "\n";
				}
				first = false;
			}
		} finally {
			read.close();
			fis.close();
		}
		return html;
	}

	/**
	 * @param templateName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static File getTemplate(String templateName) {
		Resource path = viewConfig.getExternalPath();
		try {
			File file = ResourceUtils.getFile(path.getFile().getAbsoluteFile() + "/" + templateName);
			if (file.exists() && file.canRead()) {
				return file;
			}
		} catch (Exception e) {
			// do nothing
		}
		return null;
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
	private static boolean innerEvaluate(PageTool instance, StringWriter writer, Context dataMap, String stringValue, boolean threadSafe) {
		VelocityEngine engine;

		if (stringValue == null || "".equals(stringValue)) {
			return false;
		}

		// Whether to share a VelocityEngine instance
		if (threadSafe == true) {
			engine = new VelocityEngine();
		} else {
			engine = instance.context.getVelocityEngine();
		}

		try {
			Map<String, Object> toolbox = instance.context.getToolbox();
			for (String key : toolbox.keySet()) {
				Object value = toolbox.get(key);
				if (value instanceof PageTool) {
					dataMap.put(key, instance);
				} else {
					dataMap.put(key, toolbox.get(key));
				}
			}
			return engine.evaluate(dataMap, writer, "PageTool.eval()", stringValue);
		} catch (Exception e) {
			instance.logger.error("internalEval() error.");
		}
		return false;
	}

	/**
	 * @param key
	 * @return
	 */
	public String config(String key) {
		return config(key, "");
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String config(String key, String defaultValue) {
		Properties p = config.getViewMap();
		return p.getProperty(key, defaultValue);
	}

	/**
	 * @param template
	 * @param contextMap
	 * @return
	 */
	public String eval(String template, Map<String, ?> contextMap) {
		StringWriter writer = new StringWriter();
		Context context = new VelocityContext(contextMap);

		// auto escape symbol
		template = StringTool.escapeSymbol(template);

		if (innerEvaluate(this, writer, context, template, false)) {
			return writer.toString();
		}
		return "<!-- eval:exception -->";
	}

	/**
	 * @param externalName
	 * @return
	 */
	public String external(String externalName) {
		return external(externalName, null);
	}

	/**
	 * @param externalName
	 * @param contextMap
	 * @return
	 * @throws Exception
	 */
	public String external(String externalName, Map<String, ?> contextMap) {
		String filename = externalName + viewConfig.getSuffix();
		try {
			File file = getTemplate(filename);
			String content = getTemplate(file);
			return eval(content, contextMap);
		} catch (Exception e) {
			logger.error("Unable to find external '" + externalName + "', " + viewConfig.getExternalPath(), e);
		}
		return "<!-- external: " + externalName + " -->";
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void init(Object context) {
		this.context = (ViewToolContext) context;

		if (null == viewConfig) {
			viewConfig = config.getViewConfig();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("PageTool.init() invoked.");
		}
	}

}
