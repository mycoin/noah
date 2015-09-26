package org.ionnic.core.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.SecuritySupport;
import org.ionnic.core.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 * 
 */
public class CsrfInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(CsrfInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!"GET".equals(request.getMethod()) || RequestUtils.isAjax(request)) {
			if (!SecuritySupport.checkToken(request)) {

				AccessDeniedException exception = new AccessDeniedException("Not Acceptable Token");
				request.setAttribute("exception", exception);
				return false;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
}
