package net.io.config.support;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.Context;
import net.io.config.Context.ResponseData;
import net.io.config.Security;
import net.io.config.util.ServletUtils;

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

	private static Log logger = LogFactory.getLog(Context.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 获取上下文对象
		Context context = ServletUtils.getContext(request);
		ResponseBody anno = null;

		try {
			Method method = ((HandlerMethod) handler).getMethod();
			anno = AnnotationUtils.findAnnotation(method, ResponseBody.class);

			if (anno != null) {
				context.setAccept(Context.JSON);
			}
		} catch (Exception e) {
			logger.warn("HttpInterceptor when findAnnotation.", e);
		}

		// If it is an ajax request, a csrftoken is required.
		if (context.getAccept() == Context.JSON) {
			if (!Security.checkToken(request)) {

				ServletException exception = new ServletException("Unacceptable Token");
				context.setResponseData(new ResponseData(403, exception));

				throw exception;
			}
		}

		return true;
	}
}
