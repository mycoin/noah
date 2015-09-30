package org.ionnic.core.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.utils.RequestUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * @author apple
 */
public class AccessDeniedHandler extends AccessDeniedHandlerImpl {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException,
	        ServletException {

		if (RequestUtils.isAjax(request)) {
			response.setStatus(403);
		} else {
			super.handle(request, response, accessDeniedException);
		}
	}
}
