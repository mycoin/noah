package org.ionnic.core.view.tools;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.view.ViewContext;
import org.ionnic.core.SecuritySupport;

public class PageTool {

	private ViewContext context;

	/**
	 * @return
	 */
	public String getToken() {
		HttpServletRequest req = context.getRequest();
		return SecuritySupport.getToken(req);
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
		}
	}
}
