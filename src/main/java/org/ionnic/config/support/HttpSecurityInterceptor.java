package org.ionnic.config.support;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.config.ActionSupport;
import org.ionnic.config.util.ServletUtils;
import org.ionnic.config.view.Context;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class HttpSecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		System.out
		        .println("[DEBUG] org.ionnic.config.support.HttpSecurityInterceptor.afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out
		        .println("[DEBUG] org.ionnic.config.support.HttpSecurityInterceptor.postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("[DEBUG] org.ionnic.config.support.HttpSecurityInterceptor.preHandle(HttpServletRequest, HttpServletResponse, Object)");
		boolean requestBody = false;

		try {
			Method method = ((HandlerMethod) handler).getMethod();
			ResponseBody rb = AnnotationUtils.findAnnotation(method, ResponseBody.class);
			if (rb != null) {
				ServletUtils.setAttribute(request, ResponseBody.class, rb);
				requestBody = true;
			}
		} catch (Exception e) {

		}
		if (requestBody || ServletUtils.isAjax(request)) {
			if (!TokenRepository.checkToken(request)) {
				Context data = new Context(403, "Unacceptable Token");

				ServletUtils.setAttribute(request, Context.class, data);
				throw new ServletException();
			}
		}

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
