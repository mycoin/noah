package org.ionnic.core;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.bean.WebConfig;
import org.ionnic.core.utils.RequestUtils;
import org.ionnic.core.utils.StringUtils;

public class SecuritySupport {

	private static WebConfig webConfig = ContextSupport.getBean(WebConfig.class);

	public static String HTTP_HEADER_TOKEN = "X-Requested-Token";

	public static String HTTP_SESSION_TOKEN = "x-requested-token";

	/**
	 * @param request
	 * @return
	 */
	public static boolean checkReferDomain(HttpServletRequest request) {
		URL refer = RequestUtils.getRefer(request);

		// check refer domain
		if (refer != null) {
			for (String domain : webConfig.getTrustDomain()) {
				if (refer.getHost().equalsIgnoreCase(domain)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param req
	 * @return
	 */
	public static String getToken(HttpServletRequest req) {
		String token = (String) RequestUtils.getSession(req, HTTP_SESSION_TOKEN);
		if (token == null || token.length() == 0) {

			// save session
			token = StringUtils.getGuid();
			req.getSession(true).setAttribute(HTTP_SESSION_TOKEN, token);
		}

		return token;
	}
}
