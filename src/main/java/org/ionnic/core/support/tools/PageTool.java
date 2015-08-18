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
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.core.support.Config;
import org.ionnic.core.support.config.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

/**
 * @author apple
 * 
 */
public class PageTool {

	private Logger logger = LoggerFactory.getLogger(PageTool.class);

	private ViewToolContext context;

	private static Config config = Config.getInstance();

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
	private static boolean innerEvaluate(PageTool page, StringWriter writer, Map<String, ?> dataMap, String stringValue, boolean threadSafe,
	        boolean escape) {
		VelocityEngine engine = null;

		// Whether to share a VelocityEngine instance
		if (threadSafe == true) {
			engine = new VelocityEngine();
		} else {
			engine = page.context.getVelocityEngine();
		}

		Map<String, Object> toolbox = page.context.getToolbox();
		Context contextMap = new VelocityContext(dataMap);

		if (viewConfig.isShareTools()) {
			for (String key : toolbox.keySet()) {
				Object value = toolbox.get(key);
				if (value instanceof PageTool) {
					contextMap.put(key, page);
				} else {
					contextMap.put(key, toolbox.get(key));
				}
			}
		}

		if (escape) {
			stringValue = StringTool.escapeSymbol(stringValue);
		}
		return engine.evaluate(contextMap, writer, "PageTool.eval()", stringValue);
	}

	/**
	 * @param templateName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private static String loadExternal(String templateName) throws IOException {
		Resource path = viewConfig.getExternalPath();
		File templateFile = null;
		String html = null;

		try {
			String filename = path.getFile().getAbsoluteFile() + "/" + templateName;
			templateFile = ResourceUtils.getFile(filename);

			if (templateFile.exists() && templateFile.canRead()) {
				InputStreamReader read = null;
				try {
					read = new InputStreamReader(new FileInputStream(templateFile), Config.CHARSET);
					BufferedReader br = new BufferedReader(read);
					String line = "";
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
				}
			}
		} catch (IOException e) {
			throw e;
		}
		return html;
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
	public String eval(String template, Map<String, Object> contextMap) {
		StringWriter writer = new StringWriter();

		if (innerEvaluate(this, writer, contextMap, template, false, true)) {
			return writer.toString();
		}
		return "<!-- eval:exception -->";
	}

	public String external(String external) {
		return external(external, null);
	}

	/**
	 * @param externalName
	 * @param contextMap
	 * @return
	 * @throws Exception
	 */
	public String external(String externalName, Map<String, ?> contextMap) {
		String filename = externalName + viewConfig.getExternalExtension();
		try {
			StringWriter writer = new StringWriter();
			String template = loadExternal(filename);

			if (innerEvaluate(this, writer, contextMap, template, true, false)) {
				return writer.toString();
			} else {
				throw new VelocityException("");
			}
		} catch (Exception e) {
			logger.error("Failed to render external template named '" + externalName + "'", e);
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

	@Override
	public String toString() {
		return "";
	}

}
