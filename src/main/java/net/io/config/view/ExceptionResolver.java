package net.io.config.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.Context;
import net.io.config.Context.ResponseData;
import net.io.config.util.GsonUtils;
import net.io.config.util.ServletUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private static Log logger = LogFactory.getLog(ExceptionResolver.class);

	private String errorView = "/common/error";

	private int statusCode = 500;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse respones, Object obj, Exception ex) {
		if (obj == null || ex == null) {
			return null;
		}
		Context context = ServletUtils.getContext(request);
		try {

			ResponseData result = context.getResponseData();
			if (result == null) {
				result = new ResponseData(statusCode, ex);
				context.setResponseData(result);
			}

			// output exception
			if (context.getAccept() == null || context.getAccept() == Context.HTML) {
				respones.setStatus(statusCode);
				return new ModelAndView(errorView, result.get());
			} else {
				String json = GsonUtils.toJson(result);
				respones.setContentType("" + Context.JSON);
				respones.getOutputStream().write(json.getBytes());
				return new ModelAndView();
			}
		} catch (Exception e) {
			logger.error("Not catch exception by exceptionResolver:", ex);
		}
		return null;
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
