package org.ionnic.core;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.bean.WebConfig;
import org.ionnic.core.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * @author apple
 */
public class SecuritySupport {

	private static WebConfig webConfig = ContextSupport.getBean(WebConfig.class);

	private static HttpSessionCsrfTokenRepository repository;

	private static Logger logger = LoggerFactory.getLogger(SecuritySupport.class);

	/**
	 * init csrf token
	 */
	static {
		repository = new HttpSessionCsrfTokenRepository();

		repository.setHeaderName("X-Requested-Token");
		repository.setParameterName("token");
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
	 * @param response
	 * @return
	 */
	public static boolean checkToken(HttpServletRequest request) {
		CsrfToken csrfToken = getToken(request, false);
		String actualToken = request.getHeader(csrfToken.getHeaderName());

		if (actualToken == null) {
			actualToken = request.getParameter(csrfToken.getParameterName());
		}

		// check token
		if (csrfToken == null || !csrfToken.getToken().equals(actualToken)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @param request
	 * @param autoWrite
	 * @return
	 */
	public static CsrfToken getToken(HttpServletRequest request, boolean autoWrite) {
		CsrfToken csrfToken = repository.loadToken(request);

		if (csrfToken == null && autoWrite) {
			csrfToken = repository.generateToken(request);

			// 注意，第三个参数方法实现类中未用到，刚好也不想传递这个参数
			repository.saveToken(csrfToken, request, null);
		}

		return csrfToken;
	}
}
