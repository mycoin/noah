package com.breakidea.noah.starter.support;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public abstract class AbstractAsyncController extends AbstractController {

	public static final String REQUEST_ATTRIBUTE = "request";

	public static final String SESSION_ATTRIBUTE = "session";

	public static final String RESPONSE_ATTRIBUTE = "response";

	public static final String ERROR_NAME = "errorMsg";

	protected Log logger = LogFactory.getLog(getClass());

	@Resource
	protected ServletContext servletContext;

	@Resource
	protected WebRequest webRequest;

	@Override
	protected final ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();

		try {
			handleRequestInternal(mv, request, response);

			mv.addObject(REQUEST_ATTRIBUTE, request);
			mv.addObject(RESPONSE_ATTRIBUTE, response);
			mv.addObject(SESSION_ATTRIBUTE, request.getSession(false));

		} catch (ServletException e) {
			mv.addObject(ERROR_NAME, e.getMessage());
		}

		return mv;
	}

	/**
	 * Template method. Subclasses must implement this.
	 */
	protected abstract void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException;
}
