package net.io.config.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.io.config.Context;
import net.io.config.util.GsonUtils;
import net.io.config.util.ServletUtils;

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
		if (obj == null || ex == null) {
			return null;
		}

		Context context = ServletUtils.getContext(request);
		try {

			// 判断异常有无提交
			if (!context.isCommit()) {
				context.commit(500, "Internal Server Error", ex);
			}
			// 很据媒体类型响应
			Map<String, Object> data = context.get();
			if (context.getAccept() == null || context.getAccept() == Context.HTML) {
				respones.setStatus(statusCode);
				return new ModelAndView(errorView, data);
			} else {
				String json = GsonUtils.toJson(data);

				respones.getOutputStream().write(json.getBytes());
				return new ModelAndView();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
