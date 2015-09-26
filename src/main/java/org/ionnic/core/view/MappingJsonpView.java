package org.ionnic.core.view;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class MappingJsonpView extends MappingJacksonJsonView {

	public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

	private String callbackKey;

	private String defaultCallback = "callback";

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		OutputStream out = response.getOutputStream();
		if ("GET".equals(request.getMethod().toUpperCase())) {

			String callback = request.getParameter(callbackKey);
			if (!StringUtils.hasText(callback)) {
				callback = defaultCallback;
			}

			out.write(new String(callback + "(").getBytes());
			super.render(model, request, response);
			out.write(new String(");").getBytes());
		}
	}

	/**
	 * @param callbackKey
	 */
	public void setCallbackKey(String callbackKey) {
		this.callbackKey = callbackKey;
	}

	/**
	 * @param defaultCallback
	 */
	public void setDefaultCallback(String defaultCallback) {
		this.defaultCallback = defaultCallback;
	}
}
