package org.ionnic.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.SecuritySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

public class UriExtensionFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(UriExtensionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (!SecuritySupport.checkExtension(req)) {
			AccessDeniedException exception = new AccessDeniedException("Not Acceptable Extension");
			logger.error("not acceptable extension. ", exception);
			throw exception;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
