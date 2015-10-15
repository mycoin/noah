package net.io.config.view;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.support.HttpInterceptor;
import net.io.config.util.JsonUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
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
		try {
			ErrorModel errorModel = (ErrorModel) request.getAttribute(ErrorModel.ERROR_MODEL_KEY);

			if (errorModel == null) {
				errorModel = new ErrorModel(500, "Internal Server Error");
				errorModel.setException(ex);
			}

			ResponseBody anno = null;

			try {
				Method method = ((HandlerMethod) obj).getMethod();
				anno = AnnotationUtils.findAnnotation(method, ResponseBody.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (anno != null || HttpInterceptor.isAjax(request)) {

				String json = JsonUtils.toJson(errorModel);
				respones.getOutputStream().write(json.getBytes());
				return new ModelAndView();
			} else {
				respones.setStatus(statusCode);
				return new ModelAndView(errorView, errorModel.getModelAsMap());
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