package net.io.config.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.ActionSupport;
import net.io.config.Security;
import net.io.config.view.ErrorModel;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// If it is an ajax request, a csrftoken is required.
		if (ActionSupport.isAjax(request) || ActionSupport.isJson((HandlerMethod) handler)) {
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
}
