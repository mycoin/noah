package net.io.config;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
@SuppressWarnings("serial")
public class Context implements Serializable {

	public static final String CONTEXT_ATTRIBUTE = Context.class.getName() + ".CONTEXT_ATTRIBUTE";

	public static final MediaType JSON = MediaType.APPLICATION_JSON;

	public static final MediaType HTML = MediaType.TEXT_HTML;

	public static final MediaType SCRIPT = MediaType.valueOf("text/javascript");

	private boolean commit = false;

	private MediaType accept;

	private ModelMap response;

	/**
	 * @param request
	 */
	public Context(HttpServletRequest request) {

		setAccept(null);
		try {
			String requestedWith = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(requestedWith)) {
				setAccept(JSON);
			} else {
				String callback = request.getParameter("callback");
				if (StringUtils.hasLength(callback)) {
					setAccept(SCRIPT);
				}
			}

			if (null == getAccept()) {
				String contentType = request.getContentType();
				MediaType type = MediaType.parseMediaType(contentType);
				setAccept(type);
			}
		} catch (Exception e) {

		}

		System.out.println("content type is: " + getAccept());
	}

	public boolean isCommit() {
		// TODO Auto-generated method stub
		return commit;
	}

	/**
	 * @param status
	 * @param statusInfo
	 * @param data
	 */
	public void commit(int status, String statusInfo, Object data) {
		if (commit == false) {
			response = new ModelMap();

			response.put("status", status);
			response.put("statusInfo", statusInfo);
			response.put("data", data);

			commit = true;
		}
	}

	/**
	 * @return
	 */
	public Map<String, Object> get() {
		return response;
	}

	/**
	 * @return the accept
	 */
	public MediaType getAccept() {
		return accept;
	}

	/**
	 * @param accept
	 *            the accept to set
	 */
	public void setAccept(MediaType accept) {
		this.accept = accept;
	}
}
