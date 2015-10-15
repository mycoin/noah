package net.io.config.support;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.Security;
import net.io.config.view.ErrorModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

	private static Log logger = LogFactory.getLog(HttpInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 获取上下文对象
		ResponseBody anno = null;

		try {
			Method method = ((HandlerMethod) handler).getMethod();
			anno = AnnotationUtils.findAnnotation(method, ResponseBody.class);

		} catch (Exception e) {
			logger.warn("HttpInterceptor when findAnnotation.", e);
		}

		// If it is an ajax request, a csrftoken is required.
		if (isAjax(request) || anno != null) {
			if (!Security.checkToken(request)) {
				ServletException exception = new ServletException("Unacceptable Token");
				ErrorModel model = new ErrorModel(500, "Unacceptable Token");

				// remember the error
				request.setAttribute(ErrorModel.ERROR_MODEL_KEY, model);
				throw exception;
			}
		}

		return true;
	}

	/**
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
}
