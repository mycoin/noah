package org.ionnic.core;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.bean.WebConfig;
import org.ionnic.core.exception.BusinessException;
import org.ionnic.core.utils.RequestUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * @author apple
 */
public class SecuritySupport {

	private static WebConfig webConfig = ContextSupport.getBean(WebConfig.class);

	private static HttpSessionCsrfTokenRepository repository;

	/**
	 * @param request
	 * @return
	 * @throws ServletException
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
		return false;
	}

	/**
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	public static boolean checkCsrfToken(HttpServletRequest request) {
		CsrfToken csrfToken = getToken(request, false);
		if (csrfToken == null) {
			return false;
		} else {
			String actualToken = request.getHeader(csrfToken.getHeaderName());
			if (actualToken == null) {
				actualToken = request.getParameter(csrfToken.getParameterName());
			}
			return csrfToken.getToken().equals(actualToken);
		}
	}

	/**
	 * @param request
	 * @param autoWrite
	 * @return
	 */
	public static CsrfToken getToken(HttpServletRequest request, boolean autoWrite) {

		if (null == repository) {
			repository = new HttpSessionCsrfTokenRepository();

			repository.setHeaderName("X-Requested-Token");
			repository.setParameterName("token");
		}

		CsrfToken csrfToken = repository.loadToken(request);

		if (csrfToken == null && autoWrite) {
			csrfToken = repository.generateToken(request);

			// 注意，第三个参数response，方法实现类中未用到，刚好也不想传递这个参数
			repository.saveToken(csrfToken, request, null);
		}

		return csrfToken;
	}
}
