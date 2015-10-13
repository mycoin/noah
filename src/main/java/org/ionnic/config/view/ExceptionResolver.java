package org.ionnic.config.view;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.config.util.ServletUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private String errorView = "/common/error";

	private int statusCode = 500;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse respones, Object obj, Exception ex) {

		System.out
		        .println("[DEBUG] org.ionnic.config.view.ExceptionResolver.resolveException(HttpServletRequest, HttpServletResponse, Object, Exception)");
		if (obj == null || ex == null) {
			return null;
		}

		Method method = ((HandlerMethod) obj).getMethod();
		if (method == null) {
			return null;
		}

		ModelAndView modelView = null;
		Context cache = ServletUtils.getAttribute(request, Context.class);

		try {
			if (cache == null) {
				cache = new Context(500, ex.getMessage());
			} else {
				if (cache.getStatus() == 0) {
					cache.setStatus(403);
					cache.setStatusInfo("Unknown Exception");
				}
			}
			if (ServletUtils.getAttribute(request, ResponseBody.class) == null) {
				respones.setStatus(statusCode);
				modelView = new ModelAndView(errorView, cache.asMap());
			} else {
				respones.setStatus(200);
				respones.getOutputStream().write(cache.toJSON().getBytes());

				// never remove this.
				modelView = new ModelAndView();
			}
		} catch (Exception e) {
			try {
				respones.sendError(500);
			} catch (IOException e1) {

			}
		}
		return modelView;
	}

	/**
	 * @param errorView
	 *            the errorView to set
	 */
	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
