package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.HttpException;
import org.ionnic.common.support.Security;
import org.ionnic.common.util.WebUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (response.getContentType() == null) {
			response.setContentType("text/html; charset=UTF-8");
		}

		response.addHeader("X-Frame-Options", "deny");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		response.addHeader("X-UA-Compatible", "IE=Edge,chrome=1");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// If it is an ajax request, a csrfToken is required.
		if (WebUtils.isAjax(request) || WebUtils.hasResponseAnnotation(handler)) {
			if (!Security.checkToken(request)) {
				throw new HttpException(403, "Unacceptable Token");
			}
		}

		return true;
	}
}
