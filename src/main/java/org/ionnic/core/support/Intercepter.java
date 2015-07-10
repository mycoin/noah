package org.ionnic.core.support;

import java.io.Writer;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

public class Intercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("intercepter", this.getClass().getName());
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (null != modelAndView) {
			boolean hasToken = false;
			String uuid = UUID.randomUUID().toString();

			Cookie cookies[] = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("token".equals(cookies[i].getName())) {
						hasToken = true;
						uuid = cookies[i].getValue();
						break;
					}
				}
			}
			if (!hasToken) {
				response.addCookie(new Cookie("token", uuid));
			}
			response.setHeader("X-Token", uuid);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Writer e = response.getWriter();
		
		System.out.println(e.toString());
	}

}
