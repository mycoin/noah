package org.ionnic.core.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.SecuritySupport;
import org.ionnic.core.utils.RequestUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class CsrfInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!"GET".equals(request.getMethod()) || RequestUtils.isAjax(request)) {

			if (!SecuritySupport.checkCsrfToken(request)) {
				AccessDeniedException exception = new AccessDeniedException("Not Acceptable Token");
				request.setAttribute("exception", exception);

				throw exception;
			}
		}
		return true;
	}
}
