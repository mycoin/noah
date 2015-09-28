package org.ionnic.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionControlFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(PermissionControlFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//
//		if (!SecuritySupport.checkExtension(req)) {
//			AccessDeniedException exception = new AccessDeniedException("Not Acceptable Extension");
//			request.setAttribute("exception", exception);
//
//			throw exception;
//		}
//
//		if (RequestUtils.isAjax(req)) {
//
//			if (!SecuritySupport.checkRefererDomain(req)) {
//				AccessDeniedException exception = new AccessDeniedException("Not Acceptable Referer");
//				logger.error("not acceptable referer. ", exception);
//
//				throw exception;
//			}
//		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
