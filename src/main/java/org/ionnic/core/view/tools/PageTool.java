package org.ionnic.core.view.tools;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.view.ViewToolContext;
import org.ionnic.core.SecuritySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

public class PageTool {

	private Logger logger = LoggerFactory.getLogger(PageTool.class);

	private ViewToolContext context;

	/**
	 * @return
	 */
	public CsrfToken getToken() {
		HttpServletRequest request = context.getRequest();
		return SecuritySupport.getToken(request, true);
	}

	/**
	 * @param type
	 * @return
	 */
	public String getToken(int type) {
		CsrfToken token = getToken();

		if (type == 0) {
			return token.getToken();
		} else if (type == 1) {
			return token.getParameterName();
		}
		return StringTool.toJSON(token);
	}

	/**
	 * @param name
	 * @param data
	 * @return
	 */
	public String exec(String content, Map<String, Object> data) {
		StringWriter writer = new StringWriter();
		VelocityEngine engine = context.getVelocityEngine();

		try {
			data.putAll(context.getToolbox());

			if (engine.evaluate(new VelocityContext(data), writer, "PageTool.eval()", content)) {
				return "" + writer;
			}
		} catch (VelocityException e) {
			// noop
		}
		return "<!-- ERROR -->";
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void init(Object object) {
		if (object instanceof ViewToolContext) {
			context = (ViewToolContext) object;
		} else {
			logger.error("org.ionnic.core.view.tools.PageTool must be in request scope.");
		}
	}
}
