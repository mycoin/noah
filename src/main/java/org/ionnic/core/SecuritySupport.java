package org.ionnic.core;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.bean.WebConfig;
import org.ionnic.core.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

public class SecuritySupport {

	private static WebConfig webConfig = ContextSupport.getBean(WebConfig.class);

	public static String HTTP_HEADER_TOKEN = "X-Requested-Token";

	public static String HTTP_SESSION_TOKEN = "x-requested-token";

	private static Logger logger = LoggerFactory.getLogger(SecuritySupport.class);

	/**
	 * @param request
	 * @return
	 */
	public static boolean checkRefererDomain(HttpServletRequest request) {
		URL refer = RequestUtils.getRefer(request);

		if (refer != null) {
			for (String domain : webConfig.getTrustDomain()) {
				if (refer.getHost().equalsIgnoreCase(domain)) {
					return true;
				}
			}
		}

		logger.error("Illegal file extension, path: " + refer);
		return false;
	}

	/**
	 * @param request
	 * @return
	 * @throws ServletException
	 */
	public static boolean checkExtension(HttpServletRequest request) {
		String path = request.getServletPath();
		String allowedExtension[] = webConfig.getAllowedExtension();
		if (path.indexOf(".") > -1) {
			for (String ext : allowedExtension) {
				if (path.endsWith(ext)) {
					return true;
				}
			}

			logger.error("Illegal file extension, path: " + path);
			return false;
		}
		return true;
	}

	/**
	 * @param req
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return csrfToken.getToken();
	}
}
