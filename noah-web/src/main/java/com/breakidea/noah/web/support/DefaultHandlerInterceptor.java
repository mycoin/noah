package com.breakidea.noah.web.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DefaultHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final String AJAX_REQUEST = DefaultHandlerInterceptor.class.getName();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof AbstractExtendedRequest) {
			request.setAttribute(AJAX_REQUEST, true);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getAttribute(AJAX_REQUEST) == null) {
			return;
		}

		if (modelAndView.getViewName() == null) {
			modelAndView.setViewName("/welcome");
		}
	}
}
