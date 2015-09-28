package org.ionnic.core.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.action.ActionSupport;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {

			HandlerMethod action = (HandlerMethod) handler;

			if (action.getBean() instanceof ActionSupport) {
				ActionSupport bean = (ActionSupport) action.getBean();
				bean.init(request, response, action);
			}
		}

		return true;
	}
}
