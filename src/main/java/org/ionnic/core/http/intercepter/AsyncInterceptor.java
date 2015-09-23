package org.ionnic.core.http.intercepter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.SecuritySupport;
import org.ionnic.core.utils.RequestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AsyncInterceptor extends HandlerInterceptorAdapter {

	protected void checkFromDomain(HttpServletRequest request) throws ServletException {
		if (!SecuritySupport.checkReferDomain(request)) {
			throw new ServletException("Not Acceptable Referrer");
		}
	}

	/**
	 * @param request
	 * @throws ServletException
	 */
	protected void checkUserToken(HttpServletRequest request) throws ServletException {
		String token = request.getParameter("token");
		String session = SecuritySupport.getToken(request);

		if (token == null) {
			token = request.getHeader("X-Requested-Token");
		}
		if (token == null || session == null) {
			throw new ServletException("Invalid request");
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (!response.isCommitted()) {
			if (modelAndView != null) {
				modelAndView.addObject("rx", 1);
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			if (RequestUtils.isAjax(request)) {
				checkUserToken(request);
				checkFromDomain(request);
			}
		}
		return true;
	}
}
