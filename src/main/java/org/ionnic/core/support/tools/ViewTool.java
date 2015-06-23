package org.ionnic.core.support.tools;

import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.ionnic.core.support.Config;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ViewTool {

	public static final String DEFAULT_KEY = "view";

	private HttpServletRequest request;

	private HttpSession session;

	Config config = Config.getInstance();

	/**
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		return getConfig(key, "");
	}

	/**
	 * @param key
	 * @return
	 */
	public String getConfig(String key, String defaultValue) {
		Properties p = config.getViewConfig();
		return p.getProperty(key, defaultValue);
	}

	/**
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return getConfig(key, null);
	}

	/**
	 * @return
	 */
	public HttpServletRequest getRequest() {
		if (null == request) {
			RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes servletRequestAttr = (ServletRequestAttributes) requestAttr;

			request = servletRequestAttr.getRequest();
		}
		return request;
	}

	/**
	 * @param baseUrl
	 * @return
	 */
	public String getUrl(String baseUrl) {
		HttpServletRequest request = getRequest();
		return request.getContextPath() + "/" + baseUrl;
	}

	/**
	 * @param context
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String internalEval(Context context, String content) throws Exception {
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

	/**
	 * @return
	 */
	public HttpSession getSession() {
		if (null == session) {
			HttpServletRequest req = getRequest();
			session = req.getSession();
		}
		return session;
	}
}
