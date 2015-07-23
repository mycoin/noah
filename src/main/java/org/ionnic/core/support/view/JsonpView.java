package org.ionnic.core.support.view;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class JsonpView extends MappingJacksonJsonView {

	public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

	private String callbackKey;

	private String varKey;

	private String defaultCallback;

	public String getCallbackKey() {
		return callbackKey;
	}

	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String callback;
		String var;

		// TODO Auto-generated method stub
		if ("GET".equals(request.getMethod().toUpperCase())) {
			Map<String, String[]> param = request.getParameterMap();
			OutputStream out = response.getOutputStream();
			
			if(param.containsKey(callbackKey)) {
				System.out.println(param.get(callbackKey));
			}
			
			
			if (param.containsKey(varKey)) {
				out.write(new String("var " + param.get(varKey)[0] + " = (").getBytes());
				super.render(model, request, response);
				out.write(new String(");").getBytes());
			} else {
				String callbackName = defaultCallback;
				if (param.containsKey(callbackKey)) {
					callbackName = param.get(callbackKey)[0];
				}
				out.write(new String(callbackName + "(").getBytes());
				super.render(model, request, response);
				out.write(new String(");").getBytes());
			}
			response.setContentType(DEFAULT_CONTENT_TYPE);
		} else {
			super.render(model, request, response);
		}
	}

	public void setCallbackKey(String callbackKey) {
		this.callbackKey = callbackKey;
	}

	public String getDefaultCallback() {
		return defaultCallback;
	}

	public void setDefaultCallback(String defaultCallback) {
		this.defaultCallback = defaultCallback;
	}

	public String getVarKey() {
		return varKey;
	}

	public void setVarKey(String varKey) {
		this.varKey = varKey;
	}
}
