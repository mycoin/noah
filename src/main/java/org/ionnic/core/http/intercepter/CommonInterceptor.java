package org.ionnic.core.http.intercepter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.utils.RequestUtils;
import org.ionnic.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 * 
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

	private String[] allowedExtension;

	private Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("intercepter", this.getClass().getName());

		// check Extension
		checkExtension(request);

		writeTokenHeader(request, response);

		return true;
	}

	/**
	 * @param request
	 * @param response
	 */
	protected void writeTokenHeader(HttpServletRequest request, HttpServletResponse response) {
		Cookie tokenToken = RequestUtils.getCookie(request, "token");
		String token = (String) RequestUtils.getSession(request, "x-request-token");

		if (token == null) {
			token = StringUtils.getGuid();
			request.getSession(true).setAttribute("x-request-token", token);
		}

		if (tokenToken == null || !token.equals(tokenToken.getValue())) {
			Cookie cookie = new Cookie("token", token);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	/**
	 * @param request
	 * @throws ServletException
	 */
	protected void checkExtension(HttpServletRequest request) throws ServletException {
		String path = request.getServletPath();
		if (path.indexOf(".") > -1) {
			for (String ext : allowedExtension) {
				if (path.endsWith(ext)) {
					return;
				}
			}
			ServletException exception = new ServletException("Not Acceptable");
			logger.error("not acceptable extension, url: " + path, exception);
			throw exception;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (!response.isCommitted()) {
			String token = StringUtils.getGuid();

			response.setHeader("X-Token", token);
			response.setHeader("X-Frame-Options", "SAMEORIGIN");
			response.setHeader("X-XSS-Protection", "1; mode=block");
		}
	}

	/**
	 * @return the allowedExtension
	 */
	public String[] getAllowedExtension() {
		return allowedExtension;
	}

	/**
	 * @param allowedExtension
	 *            the allowedExtension to set
	 */
	public void setAllowedExtension(String[] allowedExtension) {
		this.allowedExtension = allowedExtension;
	}

}
