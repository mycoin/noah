package net.io.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.io.config.util.GsonUtils;

import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
@SuppressWarnings("serial")
public class Context implements Serializable {

	/**
	 * @author apple
	 *
	 */
	public static class RequestData implements Serializable {

	}

	/**
	 * @author apple
	 *
	 */
	public static class ResponseData implements Serializable {

		private int status = 0;

		private String statusInfo;

		private Map<String, Object> data;

		/**
		 * @param statusCode
		 * @param errorMessage
		 */
		public ResponseData(int statusCode, Exception ex) {
			this(statusCode, ex.getMessage());
			data.put("exception", ex);
		}

		/**
		 * @param statusCode
		 * @param errorMessage
		 */
		public ResponseData(int statusCode, String errorMessage) {
			this(statusCode, errorMessage, null);
			Assert.isTrue(statusCode > 0, "For an error request, `statusCode` must large than 0");
		}

		/**
		 * @param errorMessage
		 */
		public ResponseData(int status, String statusInfo, Map<String, Object> data) {
			if (data == null) {
				data = new HashMap<String, Object>();
			}

			this.status = status;
			this.statusInfo = statusInfo;
			this.data = data;
		}

		/**
		 * @param errorMessage
		 */
		public ResponseData(Map<String, Object> data) {
			this(0, "OK", data);
		}

		/**
		 * @return
		 */
		public Map<String, Object> get() {
			Map<String, Object> result = new HashMap<String, Object>();

			result.put("status", status);
			result.put("statusInfo", statusInfo);
			result.put("data", data);

			return result;
		}

		/**
		 * @return the data
		 */
		public Map<String, Object> getData() {
			return data;
		}

		/**
		 * @return the status
		 */
		public int getStatus() {
			return status;
		}

		/**
		 * @return the statusInfo
		 */
		public String getStatusInfo() {
			return statusInfo;
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public void setData(Map<String, Object> data) {
			this.data = data;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(int status) {
			this.status = status;
		}

		/**
		 * @param statusInfo
		 *            the statusInfo to set
		 */
		public void setStatusInfo(String statusInfo) {
			this.statusInfo = statusInfo;
		}

		/**
		 * @return
		 */
		public String toJSON() {
			return GsonUtils.toJson(data);
		}
	}

	public static final String CONTEXT_ATTRIBUTE = Context.class.getName() + ".CONTEXT_ATTRIBUTE";

	public static final MediaType JSON = MediaType.APPLICATION_JSON;

	public static final MediaType HTML = MediaType.TEXT_HTML;

	public static final MediaType SCRIPT = MediaType.valueOf("text/javascript");

	private MediaType accept;

	private ResponseData responseData;

	/**
	 * @param request
	 */
	public Context(HttpServletRequest request) {
		setAccept(null);

		try {
			String requestedWith = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(requestedWith)) {
				accept = JSON;
			} else {
				String callback = request.getParameter("callback");
				if (StringUtils.hasLength(callback)) {
					accept = SCRIPT;
				}
			}
			if (null == getAccept()) {
				String contentType = request.getContentType();
				MediaType type = MediaType.parseMediaType(contentType);
				accept = type;
			}
		} catch (Exception e) {

		}

		if (null == getAccept()) {
			accept = HTML;
		}
	}

	/**
	 * @param status
	 * @param statusInfo
	 * @param data
	 */
	// public void commit(int status, String statusInfo, Object data) {
	// if (commit == false) {
	// response = new ModelMap();
	//
	// response.put("status", status);
	// response.put("statusInfo", statusInfo);
	// response.put("data", data);
	//
	// commit = true;
	// }
	// }

	/**
	 * @return
	 */
	// public Map<String, Object> get() {
	// return response;
	// }

	/**
	 * @return the accept
	 */
	public MediaType getAccept() {
		return accept;
	}

	/**
	 * @return the responseData
	 */
	public ResponseData getResponseData() {
		return responseData;
	}

	/**
	 * @param accept
	 *            the accept to set
	 */
	public void setAccept(MediaType accept) {
		this.accept = accept;
	}

	/**
	 * @param responseData
	 *            the responseData to set
	 */
	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}
}
