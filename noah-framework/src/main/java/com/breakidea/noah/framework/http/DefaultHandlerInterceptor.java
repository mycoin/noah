package com.breakidea.noah.framework.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.breakidea.noah.framework.support.WebUtils;
import com.breakidea.noah.framework.support.logger.Logger;
import com.breakidea.noah.framework.support.logger.LoggerFactory;

public class DefaultHandlerInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(DefaultHandlerInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		logger.info("Request DefaultHandlerInterceptor, URL:" + request.getRequestURL());

		if (modelAndView.getViewName() == null && WebUtils.isAjax(request)) {
			modelAndView.setViewName("/welcome");
		}
	}
}
