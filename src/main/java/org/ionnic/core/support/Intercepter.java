package org.ionnic.core.support;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Intercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (null != modelAndView) {
			boolean hasToken = false;
			String uuid = UUID.randomUUID().toString();
			String key = "token";

			Cookie cookies[] = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (key.equals(cookies[i].getName())) {
						hasToken = true;
						break;
					}
				}
			}
			if (!hasToken) {
				response.addCookie(new Cookie("token", uuid));
			}
			
			response.setHeader("CSRF-Key", "token");
			response.setHeader("CSRF-Token", uuid);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}