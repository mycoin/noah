package org.ionnic.core.view.tools;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.view.ViewContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

public class PageTool {

	private Logger logger = LoggerFactory.getLogger(PageTool.class);

	private ViewContext context;

	private CsrfToken csrfToken;

	private HttpServletRequest request;

	/**
	 * @return
	 */
	public String getToken() {
		return csrfToken.getToken();
	}

	public String getTokenName() {
		return csrfToken.getParameterName();
	}

	/**
	 * 请求级别的初始化函数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void init(Object object) {
		if (object instanceof ViewContext) {
			context = (ViewContext) object;
			request = context.getRequest();
			csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

		} else {
			logger.error("org.ionnic.core.view.tools.PageTool must be in request scope.");
		}
	}
}
