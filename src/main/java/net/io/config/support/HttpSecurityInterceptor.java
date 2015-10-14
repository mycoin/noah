package net.io.config.support;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.Context;
import net.io.config.util.ServletUtils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author apple
 *
 */
public class HttpSecurityInterceptor extends HandlerInterceptorAdapter {

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
			e.printStackTrace();
		}

		// 如果是异步请求，需要验证跨域令牌
		if (context.getAccept() == Context.JSON) {
			if (!TokenRepository.checkToken(request)) {

				ServletException exception = new ServletException("Unacceptable Token");
				context.commit(403, "Unacceptable Token", exception);
				throw exception;
			}
		}
		return true;
	}
}
