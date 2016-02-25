package org.ionnic.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.ActionSupport;
import org.ionnic.common.support.WebException;
import org.ionnic.common.util.WebUtils;
import org.ionnic.common.view.JsonView;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * @author apple
 *
 */
public class DefaultExceptionResolver implements HandlerExceptionResolver {

	protected final Log logger = LogFactory.getLog(getClass());

	private String errorView = "error";

	private int statusCode = 0;

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param status
	 * @param ex
	 * @return
	 */
	protected String getStatusInfo(int status, Exception ex) {
		String message = ex.getMessage();

		return message;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex == null) {
			return null;
		}

		ModelAndView mv = new ModelAndView();
		int status = 503;

		if (ex instanceof NoSuchRequestHandlingMethodException) {
			ex = new WebException(404, "Page Not Found");
		} else if (ex instanceof HttpRequestMethodNotSupportedException) {
			ex = new WebException(405, "Method Not Allowed");
		} else if (ex instanceof HttpMediaTypeNotSupportedException) {
			ex = new WebException(415, "Unsupported Media Type");
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			ex = new WebException(406, "Not Acceptable");
		} else if (ex instanceof MissingServletRequestParameterException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof ServletRequestBindingException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof ConversionNotSupportedException) {
			ex = new WebException(500, "Internal Server Error");
		} else if (ex instanceof TypeMismatchException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof HttpMessageNotReadableException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof HttpMessageNotWritableException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof MethodArgumentNotValidException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof MissingServletRequestPartException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof BindException) {
			ex = new WebException(400, "Bad Request");
		} else if (ex instanceof NoHandlerFoundException) {
			ex = new WebException(404, "Page Not Found");
		} else if (!(ex instanceof WebException)) {
			ex = new WebException(500, "Internal Server Error");
		}

		WebException he = (WebException) ex;
		status = he.getStatus();

		// 导出数据
		mv.addObject(ActionSupport.STATUS, status);
		mv.addObject(ActionSupport.STATUS_INFO, he.getStatusInfo());
		mv.addObject(ActionSupport.DATA, he.getData());

		if (WebUtils.hasAnnotation(handler) || handler == null) {
			mv.setView(new JsonView());
			if (statusCode > 0) {
				response.setStatus(status);
			}
		} else {
			mv.setViewName(errorView);
			response.setStatus(status);
		}
		return mv;
	}

	/**
	 * @param errorView the errorView to set
	 */
	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
